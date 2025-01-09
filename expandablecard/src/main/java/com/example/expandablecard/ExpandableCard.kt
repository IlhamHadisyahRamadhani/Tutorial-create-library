package com.example.expandablecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expandablecard.ui.theme.CreateLibraryTutorialTheme

class ExpandableCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreateLibraryTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun ExpandableCard(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    title: @Composable () -> Unit,
    body: @Composable () -> Unit = {},
    onExpandedChange: (Boolean) -> Unit = {},
    trailingIconResId: Pair<Int, Int> = Pair(
        R.drawable.baseline_keyboard_arrow_up_24,
        R.drawable.baseline_keyboard_arrow_down_24
    )
) {
    var isExpanded by remember { mutableStateOf(expanded) }
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, color = Color(0xFF4E4E4E)),
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = White,
            disabledContainerColor = Color(0xFFD5D5D5),
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        isExpanded = !isExpanded
                        onExpandedChange.invoke(isExpanded)
                    }
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                title.invoke()

                Icon(
                    painter = if (isExpanded) painterResource(id = trailingIconResId.first) else painterResource(
                        id = trailingIconResId.second
                    ),
                    contentDescription = ""
                )
            }

        }
        AnimatedVisibility(visible = isExpanded) {
            body.invoke()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CreateLibraryTutorialTheme {
        Scaffold(modifier = Modifier.padding(16.dp)) {
            ExpandableCard(
                modifier = Modifier.padding(it),
                title = {
                    Text(
                        text = "Example",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                },
                body = {
                    Box(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")
                    }

                })
        }
    }
}