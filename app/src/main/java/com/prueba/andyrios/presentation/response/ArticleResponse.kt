package com.prueba.andyrios.presentation.response
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime
import java.util.*

@Entity(tableName = "ArticleTable")
data class ArticleResponse(
    var create_at : String,
    var url: String?,
    var author: String,
    @PrimaryKey var story_id : Int,
    var story_title : String?,
    var create_id : Long,
    var state : Int,
    var objectID : String
    //var _highlightResult : ResultDataResponse
)
