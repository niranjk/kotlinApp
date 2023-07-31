package com.niranjankhatri.kotlinapp.architecturecomponents.room.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.niranjankhatri.kotlinapp.architecturecomponents.room.DateConverter
import com.niranjankhatri.kotlinapp.architecturecomponents.room.dao.MessageDao
import com.niranjankhatri.kotlinapp.architecturecomponents.room.dao.UserDao
import com.niranjankhatri.kotlinapp.architecturecomponents.room.entity.Message
import com.niranjankhatri.kotlinapp.architecturecomponents.room.entity.User

@Database(entities = [User::class, Message::class], version = 2) // after migration
@TypeConverters(DateConverter::class)
abstract class ChatMessageDatabase : RoomDatabase() {
    companion object {
        private lateinit var chatMessageDatabase: ChatMessageDatabase
        private val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "AFTER TABLE messages ADD COLUMN status INTEGER"
                )
            }
        }
        fun getDatabase(context: Context): ChatMessageDatabase {
            if (!(::chatMessageDatabase.isInitialized)){
                chatMessageDatabase =
                    Room.databaseBuilder(context,
                    chatMessageDatabase::class.java, "chat-db")
                        .addMigrations(MIGRATION_1_2) // after the migration
                        .build()
            }
            return chatMessageDatabase
        }
    }
    abstract fun userDao(): UserDao
    abstract fun messagesDao(): MessageDao
}