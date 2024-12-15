package bits.code.vgit.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RefTreeNode(node: RefNode) {
    var expanded by remember { mutableStateOf(false) }
    if (node is RefNodeParent) {
        Column {
            Row(modifier = Modifier.clickable { expanded = !expanded }) {
                Icon(
                    if (expanded) Icons.Default.KeyboardArrowDown
                    else Icons.AutoMirrored.Filled.KeyboardArrowRight, ""
                )
                Text(node.name)
            }

            Column(
                modifier = if (expanded) Modifier.padding(start = 24.dp) else Modifier.height(0.dp)
                    .padding(start = 24.dp)
            ) {
                node.children.forEach { child ->
                    RefTreeNode(child)
                }
            }
        }
    } else {
        Row {
            Spacer(Modifier.width(24.dp))
            Text(node.name)
        }
    }
}