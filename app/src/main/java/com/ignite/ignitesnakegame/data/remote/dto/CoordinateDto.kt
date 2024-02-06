package com.ignite.ignitesnakegame.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.ignite.ignitesnakegame.domain.model.Coordinate

data class CoordinateDto(
    @SerializedName("x")
    val x: Int? = null,
    @SerializedName("y")
    val y: Int? = null
)

fun List<CoordinateDto>.toCoordinateList(): List<Coordinate> = this.map {
    Coordinate(
        x = it.x,
        y = it.y,
    )
}