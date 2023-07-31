package com.niranjankhatri.kotlinapp.postsapp.repository

import com.niranjankhatri.kotlinapp.postsapp.api.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provedPostRepository(postService: PostService): PostRepository {
        return PostRepositoryImpl(postService)
    }
}