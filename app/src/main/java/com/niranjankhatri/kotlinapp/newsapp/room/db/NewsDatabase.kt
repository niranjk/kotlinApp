package com.niranjankhatri.kotlinapp.newsapp.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.niranjankhatri.kotlinapp.newsapp.room.dao.ArticleDao
import com.niranjankhatri.kotlinapp.newsapp.room.dao.JoinedArticleJournalistDao
import com.niranjankhatri.kotlinapp.newsapp.room.dao.JournalistDao
import com.niranjankhatri.kotlinapp.newsapp.room.entity.Article
import com.niranjankhatri.kotlinapp.newsapp.room.entity.JoinedArticleJournalist
import com.niranjankhatri.kotlinapp.newsapp.room.entity.Journalist

@Database(
    entities = [Article::class, Journalist::class, JoinedArticleJournalist::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun articleDao(): ArticleDao
    abstract fun journalistDao(): JournalistDao
    abstract fun joinedArticleJournalistDao(): JoinedArticleJournalistDao
}