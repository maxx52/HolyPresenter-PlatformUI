package org.holypresenter.platform.ui.planner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun PlannerFileControls(
    currentPlanName: String?,
    enabled: Boolean,
    onNewPlan: () -> Unit,
    onOpenPlan: () -> Unit,
    onSaveAs: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = currentPlanName ?: "Новый план",
            style = MaterialTheme.typography.titleSmall,
            color =
                if (currentPlanName == null) {
                    MaterialTheme.colorScheme.outline
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
            maxLines = 1
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                enabled = enabled,
                onClick = onNewPlan
            ) {
                Text(
                    text = "Новый",
                    maxLines = 1
                )
            }

            OutlinedButton(
                modifier = Modifier.weight(1f),
                enabled = enabled,
                onClick = onOpenPlan
            ) {
                Text(
                    text = "Открыть",
                    maxLines = 1
                )
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            onClick = onSaveAs
        ) {
            Text(
                text = "Сохранить как",
                maxLines = 1
            )
        }
    }
}