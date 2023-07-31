package com.niranjankhatri.kotlinapp.newsapp.room.dao

import androidx.room.*
import com.niranjankhatri.kotlinapp.newsapp.room.entity.Journalist

@Dao
interface JournalistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJournalist(journalist: Journalist)
    @Update
    fun updateJournalist(journalist: Journalist)
    @Delete
    fun deleteJournalist(journalist: Journalist)
    @Query("SELECT * FROM journalist")
    fun loadAllJournalists(): List<Journalist>
    @Query("SELECT * FROM journalist INNER JOIN " +
            "joined_article_journalist ON journalist.id=joined_article_journalist.journalist_id " +
            "WHERE joined_article_journalist.article_id=:articleId")
    fun getAuthorsForArticle(articleId: Long): List<Journalist>
}