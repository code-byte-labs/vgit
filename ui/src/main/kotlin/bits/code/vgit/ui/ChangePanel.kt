package bits.code.vgit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("FunctionName")
@Composable
fun ChangePanel(diffs: List<String>, close: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Icon(
            Icons.Filled.Close,
            contentDescription = "",
            modifier = Modifier.size(width = 20.dp, height = 20.dp).clickable { close() })
        LazyColumn {
            items(diffs.size, key = { diffs[it].hashCode() }) {
                Text(diffs[it])
            }
        }
    }
}