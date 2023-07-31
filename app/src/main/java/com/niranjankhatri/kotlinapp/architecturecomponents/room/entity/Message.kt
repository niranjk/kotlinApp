package com.niranjankhatri.kotlinapp.architecturecomponents.room.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "messages", primaryKeys = ["message_id", "time"],
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["user_id"],
        childColumns = ["user"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Message(
    // @PrimaryKey(autoGenerate = true) // if you define multiple primary key use the entity primary keys
    @ColumnInfo(name = "message_id")
    val id: Long,
    @ColumnInfo(name = "text", defaultValue = "")
    val text: String,
    @ColumnInfo(name = "time")
    val time: Long,
    @ColumnInfo(name = "user")
    val userId: Long,
    @ColumnInfo(name = "status")  // new changes added
    val status : Int, // new changes added
    // @Embedded
    // val location: Location?
)

/*
@Entity(tableName = "location")
data class Location(
    @ColumnInfo(name = "lat")
    val lat : Double,
    @ColumnInfo(name = "long")
    val long : Double,
    @ColumnInfo(name = "location_name")
    val name : String
)

 */

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val id: Long,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "last_online")
    val lastOnline: Date
)

@Entity(tableName = "text_time")
data class TextWithTime(
    @ColumnInfo(name = "text")
    val text :String,
    @ColumnInfo(name = "time")
    val time : Long,
)

data class MessageWithUser(
    @Embedded val message: Message,
    @Embedded val user: User
)