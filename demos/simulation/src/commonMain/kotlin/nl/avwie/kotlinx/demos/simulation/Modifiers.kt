package nl.avwie.kotlinx.demos.simulation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.nativeCanvas
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import org.jetbrains.skia.*

class ScreenshotState {

    private val _trigger = MutableSharedFlow<Unit>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val trigger = _trigger.asSharedFlow()

    private val _latestScreenShot = MutableStateFlow<Bitmap?>(null)
    val latestScreenShot = _latestScreenShot.asStateFlow()

    fun takeScreenshot() {
        _trigger.tryEmit(Unit)
    }

    fun save(bitmap: Bitmap) {
        _latestScreenShot.update { bitmap }
    }
}

@Composable
fun Modifier.takeScreenshot(
    state: ScreenshotState
): Modifier {

    var shouldWritePixels by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        state.trigger.collect {
            shouldWritePixels = true
        }
    }

    return this
        .drawWithContent {
            drawContent()
            if (shouldWritePixels) {
                val nativeCanvas = drawContext.canvas.nativeCanvas
                val imageInfo = ImageInfo(
                    width = 1280,
                    height = 800,
                    colorInfo = ColorInfo(
                        colorType = ColorType.RGBA_8888,
                        alphaType = ColorAlphaType.PREMUL,
                        colorSpace = ColorSpace.sRGB
                    ),
                )

                val bitmap = Bitmap()
                if (!bitmap.setImageInfo(imageInfo)) {
                    println("Couldn't set imageInfo")
                }

                if (!bitmap.allocPixels()) {
                    println("Couldn't allocate pixels")
                }

                if (!nativeCanvas.readPixels(bitmap, 0, 0)) {
                    println("Couldn't read pixels")
                }

                shouldWritePixels = false
            }
        }
}