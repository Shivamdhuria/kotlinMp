package com.surrus.peopleinspace.presentation.ui.dog_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.surrus.common.domain.Dog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.surrus.peopleinspace.presentation.components.PuppyListItem
import com.surrus.peopleinspace.presentation.theme.PawsTheme

@Composable
fun DogListScreen(viewModel: DogListViewModel, navController: NavController) {

    val dogs = viewModel.recipes.value

    PawsTheme() {
        Box() {
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
                DogList(dogs = dogs)
            }

            Column() {
                Spacer(Modifier.weight(1f))
                Button(onClick = { viewModel.loadMore() }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Load More")
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun DogList(dogs: List<Dog>) {
//    Column {
//        dogs.forEach { DogItemCard(it, onClick = {}) }
//    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = dogs,
            itemContent = {
                PuppyListItem(dog = it)
            })
    }
}
