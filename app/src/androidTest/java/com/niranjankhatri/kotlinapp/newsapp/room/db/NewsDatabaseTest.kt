package com.niranjankhatri.kotlinapp.newsapp.room.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.niranjankhatri.kotlinapp.newsapp.room.dao.ArticleDao
import com.niranjankhatri.kotlinapp.newsapp.room.dao.JoinedArticleJournalistDao
import com.niranjankhatri.kotlinapp.newsapp.room.dao.JournalistDao
import com.niranjankhatri.kotlinapp.newsapp.room.entity.Article
import com.niranjankhatri.kotlinapp.newsapp.room.entity.JoinedArticleJournalist
import com.niranjankhatri.kotlinapp.newsapp.room.entity.Journalist
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class NewsDatabaseTest{

    private lateinit var db: NewsDatabase
    private lateinit var articleDao: ArticleDao
    private lateinit var journalistDao: JournalistDao
    private lateinit var joinedArticleJournalistDao: JoinedArticleJournalistDao

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        articleDao = db.articleDao()
        journalistDao = db.journalistDao()
        joinedArticleJournalistDao = db.joinedArticleJournalistDao()
        initData()
    }

    private fun initData(){
        val articleNumber = 5
        for (i in 0 until articleNumber){
            val article = Article(
                title = "title$i",
                content = "content$i",
                time = System.currentTimeMillis()
            )
            articleDao.insertArticle(article)
        }
        val articles = articleDao.loadAllArticles()
        val journalistNumber = 10
        for (i in 0 until journalistNumber){
            val journalist = Journalist(
                firstName = "firstName$i",
                lastName = "lastName$i",
                jobTitle = "jobTitle$i"
            )
            journalistDao.insertJournalist(journalist)
        }
        val journalists = journalistDao.loadAllJournalists()
        val joinedArticleJournalist1 = JoinedArticleJournalist(
            articleId = articles[0].id,
            journalistId = journalists[0].id
        )
        joinedArticleJournalistDao.insertArticleJournalist(joinedArticleJournalist1)
        val joinedArticleJournalist2 = JoinedArticleJournalist(
            articleId = articles[0].id,
            journalistId = journalists[1].id
        )
        joinedArticleJournalistDao.insertArticleJournalist(joinedArticleJournalist2)
        val joinedArticleJournalist3 = JoinedArticleJournalist(
            articleId = articles[1].id,
            journalistId = journalists[0].id
        )
        joinedArticleJournalistDao.insertArticleJournalist(joinedArticleJournalist3)
    }


    @Test
    fun updateArticle(){
        val article = articleDao.loadAllArticles()[0]
        articleDao.updateArticle(article.copy(title = "new title"))
        Assert.assertEquals("new title", articleDao.loadAllArticles()[0].title)
    }


    @After
    @Throws(IOException::class)
    fun tearDown(){
        db.close()
    }
}