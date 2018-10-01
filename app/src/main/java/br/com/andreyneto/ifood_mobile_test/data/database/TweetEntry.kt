package br.com.andreyneto.ifood_mobile_test.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName = "tweets", indices = [Index(value= ["tweetID"], unique = true)])
data class TweetEntry(@PrimaryKey(autoGenerate = true) var id: Long?,
                      @ColumnInfo(name= "tweetID") var tweetID: Long,
                      @ColumnInfo(name = "username") var username: String,
                      @ColumnInfo(name = "text") var text: String,
                      @ColumnInfo(name = "score") var score: Double,
                      @ColumnInfo(name= "sentimentChecked") var sentimentChecked: Boolean){
    constructor(text: String, username: String, tweetID: Long) :
            this(null,tweetID,username,text,0.0, false)
}