package bits.code.vgit.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import bits.code.vgit.git.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit
import java.awt.Dimension

@Composable
@Preview
fun App() {

    var commits by remember { mutableStateOf<List<RevCommit>>(listOf()) }
    var branches by remember { mutableStateOf<List<Ref>>(listOf()) }

    LaunchedEffect(Unit) {
        val deffered = async(Dispatchers.IO) {
            println(Thread.currentThread())
            Client.commits()
        }

        println(Thread.currentThread())
        commits = deffered.await()
        println(Thread.currentThread())
    }

    LaunchedEffect(Unit) {
        val deffered = async(Dispatchers.IO) {
            println(Thread.currentThread())
            Client.branches()
        }
        println(Thread.currentThread())
        branches = deffered.await()
        println(branches[0].javaClass)
        println(Thread.currentThread())
    }

    MaterialTheme {
        Row {
            Column {
                LazyColumn {
                    items(branches.size, key = { branches[it].name }) {
                        Text("" + branches[it].name)
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Icon(Icons.Default.KeyboardArrowDown, "")
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, "")
                SelectionContainer {
                    LazyColumn {
                        items(commits.size, key = { commits[it].id }) {
                            Text(
                                "" + commits[it].id.name.substring(
                                    0,
                                    8
                                ) + " " + commits[it].shortMessage + " " + commits[it].authorIdent.emailAddress
                            )
                        }
                    }
                }
            }
        }

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
