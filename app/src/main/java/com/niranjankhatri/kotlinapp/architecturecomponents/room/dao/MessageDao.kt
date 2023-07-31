package com.niranjankhatri.kotlinapp.architecturecomponents.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.niranjankhatri.kotlinapp.architecturecomponents.room.entity.Message
import com.niranjankhatri.kotlinapp.architecturecomponents.room.entity.MessageWithUser
import com.niranjankhatri.kotlinapp.architecturecomponents.room.entity.TextWithTime
import java.util.concurrent.Flow

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessages(vararg messages: Message)
    @Update
    fun updateMessages(vararg messages: Message)
    @Delete
    fun deleteMessages(vararg messages: Message)
    @Query("SELECT * FROM messages")
    fun loadAllMessages(): LiveData<List<Message>>
    @Query("SELECT * FROM messages WHERE user=:userId AND time>=:time")
    fun loadMessagesFromUserAfterTime(
        userId: String,
        time: Long,
    ): List<Message>
    @Query("SELECT text, time FROM messages")
    fun loadTextsAndTimes(): List<TextWithTime>
    @Query("SELECT * FROM messages INNER JOIN users on users.user_id=messages.user")
    fun loadMessagesAndUsers(): List<MessageWithUser>
}
