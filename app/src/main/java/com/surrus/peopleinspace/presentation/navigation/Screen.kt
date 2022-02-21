package com.surrus.peopleinspace.presentation.navigation

sealed class Screen(val route: String, ){

    object DogList: Screen("dogList")

    object DogDetail: Screen("dogDetail")
}
