package com.niranjankhatri.kotlinapp.newsapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.niranjankhatri.kotlinapp.newsapp.room.entity.JoinedArticleJournalist

@Dao
interface JoinedArticleJournalistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticleJournalist(
        joinedArticleJournalist: JoinedArticleJournalist
    )
    @Delete
    fun deleteArticleJournalist(
        joinedArticleJournalist: JoinedArticleJournalist
    )
}