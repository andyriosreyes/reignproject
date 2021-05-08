package com.prueba.andyrios.domain.model

import com.google.gson.annotations.SerializedName

data class ResultData(
    @SerializedName("story_title")
    var story_title : Title
)
