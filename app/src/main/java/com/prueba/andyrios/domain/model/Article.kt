package com.prueba.andyrios.domain.model

import com.google.gson.annotations.SerializedName
data class Article(
    @SerializedName("created_at")
    var create_at : String,
    @SerializedName("story_url")
    var url: String?,
    var author: String,
    @SerializedName("story_id")
    var story_id : Int,
    @SerializedName("story_title")
    var story_title : String?,
    @SerializedName("objectID")
    var objectID : String,
//    var story_title : List<String>
//    @SerializedName("_highlightResult")
//    var _highlightResult : ResultData

)
