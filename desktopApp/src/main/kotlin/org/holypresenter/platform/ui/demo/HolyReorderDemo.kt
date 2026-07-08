package org.holypresenter.platform.ui.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.holypresenter.platform.ui.interaction.dragdrop.HolyReorderColumn

@Composable
fun HolyReorderDemo() {
    var items by remember {
        mutableStateOf(
            listOf(
                "Куплет 1",
                "Припев",
                "Куплет 2",
                "Бридж",
                "Финал"
            )
        )
    }

    HolyReorderColumn(
        items = items,
        onMove = { from, to ->
            val mutable = items.toMutableList()
            val item = mutable.removeAt(from)

            mutable.add(to, item)
            items = mutable
        }
    ) { item, _, dragging ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (dragging)
                            MaterialTheme.colorScheme.secondaryContainer
                        else
                            MaterialTheme.colorScheme.surface
                    )
                    .padding(16.dp)
            ) {
                Text("⋮⋮")

                Spacer(Modifier.width(16.dp))

                Text(item)
            }
        }
    }
}