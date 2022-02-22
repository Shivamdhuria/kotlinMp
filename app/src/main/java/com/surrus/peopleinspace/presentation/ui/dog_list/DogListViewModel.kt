package com.surrus.peopleinspace.presentation.ui.dog_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surrus.common.domain.Dog
import com.surrus.common.interactors.GetDogs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(private val getDogs: GetDogs) : ViewModel() {

    val dogs: MutableState<List<Dog>> = mutableStateOf(ArrayList())
    private val loading = mutableStateOf(false)

    init {
        loadMore()
    }

    internal fun loadMore() {
        // prevent duplicate event due to recompose happening to quickly
        loading.value = true
        getDogs.execute().onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                appendDogs(list)
            }

            dataState.error?.let { error ->
                Log.e("get", "get dogs: ${error}")
            }

        }.launchIn(viewModelScope)
    }

    /**
     * Append new dogs to the current list of recipes
     */
    private fun appendDogs(recipes: List<Dog>) {
        val current = ArrayList(this.dogs.value)
        current.addAll(recipes)
        this.dogs.value = current
    }
}





















