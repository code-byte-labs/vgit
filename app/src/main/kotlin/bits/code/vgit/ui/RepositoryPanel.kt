package bits.code.vgit.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import bits.code.vgit.git.Repository
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit
import java.io.File

@Composable
fun RepositoryPanel(repo: File) {
    var commits by remember { mutableStateOf<List<RevCommit>>(listOf()) }
    var branches by remember { mutableStateOf<List<Ref>>(listOf()) }

    val repository = remember(repo) { Repository(repo) }

    LaunchedEffect(repo) {
        commits = repository.commits()
        branches = repository.branches()
    }

    MaterialTheme {
        Row {
            BranchesPanel(branches)
            Spacer(modifier = Modifier.width(8.dp))
            CommitsPanel(commits)
        }
    }
}