package bits.code.vgit.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.application
import java.awt.Dimension
import java.awt.Point
import java.awt.Toolkit
import java.io.File

@Composable
fun WindowScope.App() {
    LaunchedEffect(Unit) {
        val size = Toolkit.getDefaultToolkit().screenSize
        window.location = Point((size.width - 800) / 2, (size.height - 600) / 2)
        window.minimumSize = Dimension(800, 600)
    }
    MaterialTheme {
        RepositoryPanel(File("C:\\Users\\Admin\\Workspace\\javascript\\Recoil\\.git"))
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        LaunchedEffect(Unit) {
            window.minimumSize = Dimension(800, 600)
        }
        App()
    }
}
