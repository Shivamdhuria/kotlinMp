package com.surrus.common.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogDto(

    @SerialName("message")
    var message: List<String>,

    @SerialName("status")
    var status: String,
)

