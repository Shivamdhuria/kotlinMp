@file:OptIn(ExperimentalFoundationApi::class)

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.surrus.common.domain.DataState
import com.surrus.common.domain.Dog
import com.surrus.common.interactors.GetDogs
import com.surrus.common.network.DogServiceImpl
import com.surrus.common.network.models.DogDtoMapper
import theme.PawsTheme
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO


fun main() = application {
    val windowState = rememberWindowState()

    val scope = rememberCoroutineScope()
    var dogs by remember { mutableStateOf<List<Dog>>(emptyList()) }
    val getDogs = GetDogs(DogServiceImpl(), dtoMapper = DogDtoMapper())
    var state by remember { mutableStateOf<DataState<List<Dog>>>(DataState.loading()) }


    scope.launch {
        val newDogs = getDogs.getDogsFromNetwork()
        dogs = dogs + newDogs
    }

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "Updawg"
    ) {
        PawsTheme {

            Box() {
                Column(modifier = Modifier.padding(bottom = 10.dp)) {
                    DogList(dogs = dogs)
                }

                Column() {
                    Spacer(Modifier.weight(1f))
                    Button(onClick = {
                        scope.launch {
                            val newDogs = getDogs.getDogsFromNetwork()
                            dogs = dogs + newDogs
                        }

                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Load More")
                    }
                }
            }

        }
    }
}

@Composable
fun fetchImage(url: String): ImageBitmap? {
    var image by remember(url) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(url) {
        loadFullImage(url)?.let {
            image =  org.jetbrains.skia.Image.makeFromEncoded(toByteArray(it)).asImageBitmap()
        }
    }

    return image
}

suspend fun loadFullImage(source: String): BufferedImage? = withContext(Dispatchers.IO) {
    runCatching {
        val url = URL(source)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.connectTimeout = 5000
        connection.connect()

        val input: InputStream = connection.inputStream
        val bitmap: BufferedImage? = ImageIO.read(input)
        bitmap
    }.getOrNull()
}


fun toByteArray(bitmap: BufferedImage): ByteArray {
    val baos = ByteArrayOutputStream()
    ImageIO.write(bitmap, "png", baos)
    return baos.toByteArray()
}

@ExperimentalCoroutinesApi
@Composable
fun DogList(dogs: List<Dog>) {

    LazyVerticalGrid(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                cells = GridCells.Adaptive(300.dp)
    ) {
        items(
            items = dogs,
            itemContent = {
                PuppyListItem(dog = it)
            })
    }
}

@Composable
fun PuppyListItem(dog: Dog) {
    PawsTheme {
        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
            elevation = 2.dp,

            shape = RoundedCornerShape(corner = CornerSize(16.dp))

        ) {
            Column (    horizontalAlignment = Alignment.CenterHorizontally){
                val image = fetchImage(dog.imageUrl)

                image?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                }

                    Text(text = dog.breed, style = MaterialTheme.typography.h6)
                    Text(text = dog.rating, style = MaterialTheme.typography.caption)

                }
            }

        }

}
