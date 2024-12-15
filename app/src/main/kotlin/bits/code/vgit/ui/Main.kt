package bits.code.vgit.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension
import java.io.File

@Composable
@Preview
fun App() {
    RepositoryPanel(File("C:\\Users\\Admin\\Workspace\\javascript\\Recoil\\.git"))
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        LaunchedEffect(Unit) {
            window.minimumSize = Dimension(800, 600)
        }
        App()
    }
}
