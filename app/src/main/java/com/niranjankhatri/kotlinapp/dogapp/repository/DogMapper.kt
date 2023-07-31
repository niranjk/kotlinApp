package com.niranjankhatri.kotlinapp.dogapp.repository

import com.niranjankhatri.kotlinapp.dogapp.api.Dog
import com.niranjankhatri.kotlinapp.dogapp.storage.room.DogEntity

class DogMapper {

    fun mapServiceToEntity(dog: Dog): List<DogEntity> = dog.urls.map {
        DogEntity(0,it)
    }

    fun mapEntityToUi(dogEntity: DogEntity): DogUi = DogUi(dogEntity.url)
}