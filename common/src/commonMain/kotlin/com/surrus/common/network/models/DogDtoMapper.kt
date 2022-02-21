package com.surrus.common.network.models

import com.surrus.common.domain.Dog
import com.surrus.common.domain.util.DomainMapper

class DogDtoMapper : DomainMapper<String, Dog> {

    override fun mapToDomainModel(url: String): Dog {
        return Dog(
            breed = extractBreedName(url = url),
            rating = (10..20).random().toString() + "/10",
            imageUrl = url
        )
    }

    private fun extractBreedName(url: String): String {
        val breedName = url.substringAfter("breeds/").substringBefore("/")
        return breedName.replace(Regex("-"), " ").capitalize()
    }

    fun toDomainList(initial: List<String>): List<Dog> {
        return initial.map { mapToDomainModel(it) }
    }


    override fun mapFromDomainModel(domainModel: Dog): String {
        TODO("Not yet implemented")
    }
}
