package com.surrus.peopleinspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.surrus.peopleinspace.presentation.navigation.Screen
import com.surrus.peopleinspace.presentation.theme.PawsTheme
import dagger.hilt.android.AndroidEntryPoint
import com.surrus.peopleinspace.presentation.ui.dog_list.DogListScreen
import com.surrus.peopleinspace.presentation.ui.dog_list.DogListViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PawsTheme {
                setContent {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DogList.route
                    ) {
                        composable(
                            route = Screen.DogList.route
                        ) { navBackStackEntry ->
                            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                            val viewModel: DogListViewModel = viewModel(factory =factory,
                               key = "DogListViewModel" )
                            DogListScreen(
                                viewModel = viewModel,
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }
    }
}
