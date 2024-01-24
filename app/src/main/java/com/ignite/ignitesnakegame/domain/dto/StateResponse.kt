package com.ignite.ignitesnakegame.domain.dto

import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("fruit")
    val fruit: List<Fruit?>? = null,
    @SerializedName("snakes")
    val snakes: List<Snake?>?= null,
    @SerializedName("status")
    val status: Boolean? = null
)