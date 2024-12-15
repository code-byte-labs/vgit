package bits.code.vgit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.eclipse.jgit.lib.Ref

@Suppress("FunctionName")
@Composable
fun BranchesPanel(branches: List<Ref>) {
    Column {
        LazyColumn {
            items(branches.size, key = { branches[it].name }) {
                Text("" + branches[it].name)
            }
        }
    }
}