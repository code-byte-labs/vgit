package bits.code.vgit.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.eclipse.jgit.revwalk.RevCommit

@Suppress("FunctionName")
@Composable
fun CommitsPanel(commits: List<RevCommit>, onItemClick: (RevCommit?, RevCommit) -> Unit) {
    Column {
        SelectionContainer {
            LazyColumn {
                items(commits.size, key = { commits[it].id }) {
                    Text(
                        text = commits[it].shortMessage,
                        modifier = Modifier.clickable {
                            val sourceRevCommit = if (it + 1 == commits.size) {
                                null
                            } else {
                                commits[it + 1]
                            }
                            onItemClick(sourceRevCommit, commits[it])
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}