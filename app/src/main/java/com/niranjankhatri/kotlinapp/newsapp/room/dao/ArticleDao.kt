package com.niranjankhatri.kotlinapp.newsapp.room.dao

import androidx.room.*
import com.niranjankhatri.kotlinapp.newsapp.room.entity.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)
    @Update
    fun updateArticle(article: Article)
    @Delete
    fun deleteArticle(article: Article)
    @Query("SELECT * FROM article")
    fun loadAllArticles(): List<Article>
    @Query("SELECT * FROM article INNER JOIN joined_article_journalist ON" +
            " article.id=joined_article_journalist.article_id" +
            " WHERE joined_article_journalist.journalist_id=:journalistId")
    fun loadArticlesFromAuthor(journalistId: Long): List<Article>
}