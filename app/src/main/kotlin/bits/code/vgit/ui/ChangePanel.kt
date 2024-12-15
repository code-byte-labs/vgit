package bits.code.vgit.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.eclipse.jgit.diff.DiffEntry

@Composable
fun ChangePanel(diff: DiffEntry?, close: () -> Unit) {

    Column {
        Icon(Icons.Filled.Close, contentDescription = "", modifier = Modifier.clickable { close() })
        Text(diff?.toString() ?: "")
    }
}