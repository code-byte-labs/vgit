package bits.code.vgit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.eclipse.jgit.revwalk.RevCommit

@Composable
fun CommitsPanel(commits: List<RevCommit>) {
    Column {
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