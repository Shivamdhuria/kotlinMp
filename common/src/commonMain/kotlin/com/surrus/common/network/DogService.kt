package com.surrus.common.network

import com.surrus.common.network.models.DogDto

interface DogService {

    suspend fun getDogs(): DogDto


}
