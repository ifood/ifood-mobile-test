package br.com.tweetanalyzer.models

import com.google.gson.annotations.SerializedName

/**
 * Created by gabrielsamorim
 * on 16/05/18.
 */
data class TwitterModel(@SerializedName("create_at") val createAt: String,
                        @SerializedName("id") val id: Long,
                        @SerializedName("text") val description: String,
                        @SerializedName("in_reply_to_status_id") val replyStatusId: String,
                        @SerializedName("in_reply_to_user_id") val replyUserId: String,
                        @SerializedName("in_reply_to_screen_name") val replyScreenName: String)