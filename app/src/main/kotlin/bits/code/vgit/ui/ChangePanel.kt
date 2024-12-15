package bits.code.vgit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ChangePanel(diffs: List<String>, close: () -> Unit) {

    Column(modifier = Modifier.background(Color.White).fillMaxSize()) {
        Icon(Icons.Filled.Close, contentDescription = "", modifier = Modifier.clickable { close() })
        LazyColumn {
            items(diffs.size, key = { diffs[it].hashCode() }) {
                Text(diffs[it])
            }
        }
    }
}