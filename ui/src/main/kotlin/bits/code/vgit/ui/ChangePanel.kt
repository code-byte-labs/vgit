package bits.code.vgit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import bits.code.vgit.ui.style.Fonts

@Suppress("FunctionName")
@Composable
fun ChangePanel(diffs: List<String>, close: () -> Unit) {

    val lines by remember(diffs) {
        derivedStateOf {
            diffs.map { it.split('\n') }.flatten()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier.size(width = 20.dp, height = 20.dp).clickable { close() })
        }

        SelectionContainer {
            LazyColumn {
                items(lines.size, key = { it }) {
                    Text(text = buildAnnotatedString {
                        val line = lines[it]
                        if (line.startsWith('+')) {
                            withStyle(SpanStyle(color = Color.Green)) {
                                append(line)
                            }
                        } else if (line.startsWith('-')) {
                            withStyle(SpanStyle(color = Color.Red)) {
                                append(line)
                            }
                        } else if (line.startsWith('@')) {
                            withStyle(SpanStyle(color = Color.Yellow)) {
                                append(line)
                            }
                        } else {
                            append(line)
                        }

                    }, style = TextStyle(fontFamily = Fonts.monaco, fontSize = 12.sp))
                }
            }
        }
    }
}