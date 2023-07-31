package com.niranjankhatri.kotlinapp.architecturecomponents.room.dao

import androidx.room.*
import com.niranjankhatri.kotlinapp.architecturecomponents.room.entity.User
import kotlinx.coroutines.flow.StateFlow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)
    @Update
    fun updateUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Query("SELECT * FROM users")
    fun loadAllUsers(): List<User>
}