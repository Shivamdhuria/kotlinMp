package com.surrus.common.network.models

import com.surrus.common.domain.Dog
import com.surrus.common.domain.util.DomainMapper
import com.surrus.common.timestamp

class DogDtoMapper : DomainMapper<String, Dog> {

    fun toDomainList(dogUrls: List<String>): List<Dog> {
        return dogUrls.map { mapToDomainModel(it) }
    }

    override fun mapToDomainModel(url: String): Dog {
        return Dog(
            breed = extractBreedName(url = url),
            rating = (10..20).random().toString() + "/10",
            imageUrl = url,
            timestamp = timestamp()
        )
    }

    private fun extractBreedName(url: String): String {
        val breedName = url.substringAfter("breeds/").substringBefore("/")
        return breedName.replace(Regex("-"), " ").capitalize()
    }


    override fun mapFromDomainModel(domainModel: Dog): String {
        TODO("Not yet implemented")
    }
}
