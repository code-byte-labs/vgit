package bits.code.vgit.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.eclipse.jgit.revwalk.RevCommit

@Composable
fun CommitsPanel(commits: List<RevCommit>, showChangePanel: (RevCommit) -> Unit) {
    Column {
        SelectionContainer {
            LazyColumn {
                items(commits.size, key = { commits[it].id }) {
                    Text(
                        commits[it].shortMessage,
                        modifier = Modifier.clickable { showChangePanel(commits[it]) },
                    )
                }
            }
        }
    }
}