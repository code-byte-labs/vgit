package bits.code.vgit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bits.code.vgit.git.Repository
import jdk.internal.org.jline.utils.DiffHelper
import kotlinx.coroutines.launch
import org.eclipse.jgit.diff.DiffFormatter
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit
import java.io.ByteArrayOutputStream
import java.io.File

@Composable
fun RepositoryPanel(repo: File) {

    var changePanelVisible by remember { mutableStateOf(false) }
    var commits by remember { mutableStateOf<List<RevCommit>>(listOf()) }
    var branches by remember { mutableStateOf<List<Ref>>(listOf()) }

    val repository = remember(repo) { Repository(repo) }

    val coroutineScope = rememberCoroutineScope()

    var diffs by remember { mutableStateOf<List<String>>(listOf()) }

    LaunchedEffect(repo) {
        commits = repository.commits()
        branches = repository.branches()
    }

    Box {
        if (changePanelVisible) {
            ChangePanel(diffs, close = { changePanelVisible = false })
        }

        Row {
            BranchesPanel(branches)
            Spacer(modifier = Modifier.width(8.dp))
            CommitsPanel(commits) { sourceRevCommit, destinationRevCommit ->
                coroutineScope.launch {
                    val res = mutableListOf<String>()
                    repository.diff(sourceRevCommit, destinationRevCommit).forEach {
                        val outputStream = ByteArrayOutputStream()
                        val formatter = DiffFormatter(outputStream)
                        formatter.setRepository(repository.repository())
                        formatter.format(it)
                        res.add(String(outputStream.toByteArray()))
                    }
                    diffs = res
                    changePanelVisible = true
                }
            }
        }

    }
}