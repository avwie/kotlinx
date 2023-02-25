package nl.avwie.kotlinx.demos.simulation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.ImageComposeScene
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class ScreenshotState {

    private val _trigger = MutableSharedFlow<Unit>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val trigger = _trigger.asSharedFlow()

    private val _latestScreenShot = MutableStateFlow<ImageBitmap?>(null)
    val latestScreenShot = _latestScreenShot.asStateFlow()

    fun takeScreenshot() {
        _trigger.tryEmit(Unit)
    }

    fun renderContent(
        width: Int,
        height: Int,
        density: Density,
        content: @Composable () -> Unit
    ) {
        val scene = ImageComposeScene(
            width = width,
            height = height,
            density = density,
            content = content
        )

        val bitmap = scene.render().toComposeImageBitmap()

        _latestScreenShot.update {
            bitmap
        }
    }
}

@Composable
fun ScreenshotContainer(
    screenshotState: ScreenshotState,
    targetDensity: Density = Density(1f),
    content: @Composable () -> Unit
) {
    val density: Density = LocalDensity.current
    var layoutCoordinates: LayoutCoordinates? by remember { mutableStateOf(null) }

    LaunchedEffect(screenshotState) {
        screenshotState.trigger.collect {
            if (layoutCoordinates != null) {
                val scale = targetDensity.density / density.density
                screenshotState.renderContent(
                    width = (layoutCoordinates!!.size.width * scale).toInt(),
                    height = (layoutCoordinates!!.size.height * scale).toInt(),
                    density = targetDensity,
                    content = content
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .onGloballyPositioned { actualLayoutCoordinates ->
                layoutCoordinates = actualLayoutCoordinates
            }
    ) {
        content()
    }
}