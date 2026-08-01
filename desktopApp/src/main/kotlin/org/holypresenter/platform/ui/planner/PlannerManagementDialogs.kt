package org.holypresenter.platform.ui.planner

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import holypresenter.org.platform.api.planner.PlannerInfo

@Composable
internal fun OpenPlanDialog(
    plans: List<PlannerInfo>,
    currentPlanId: String?,
    onOpen: (PlannerInfo) -> Unit,
    onRename: (PlannerInfo) -> Unit,
    onDelete: (PlannerInfo) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Открыть план")
        },
        text = {
            if (plans.isEmpty()) {
                Text("Сохранённых планов пока нет.")
            } else {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 360.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    items(
                        items = plans,
                        key = { plan ->
                            plan.id
                        }
                    ) { plan ->
                        PlanSelectionRow(
                            plan = plan,
                            selected = plan.id == currentPlanId,
                            onOpen = {
                                onOpen(plan)
                            },
                            onRename = {
                                onRename(plan)
                            },
                            onDelete = {
                                onDelete(plan)
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Закрыть")
            }
        }
    )
}

@Composable
private fun PlanSelectionRow(
    plan: PlannerInfo,
    selected: Boolean,
    onOpen: () -> Unit,
    onRename: () -> Unit,
    onDelete: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color =
            if (selected) {
                MaterialTheme.colorScheme.secondaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = plan.name,
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        onClick = onOpen
                    )
                    .padding(
                        horizontal = 12.dp,
                        vertical = 10.dp
                    ),
                color =
                    if (selected) {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
            )

            TextButton(
                onClick = onRename
            ) {
                Text("Изменить")
            }

            TextButton(
                onClick = onDelete
            ) {
                Text(
                    text = "Удалить",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
internal fun RenamePlanDialog(
    name: String,
    error: String?,
    onNameChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Переименовать план")
        },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Название плана")
                },
                singleLine = true,
                isError = error != null,
                supportingText = {
                    error?.let { message ->
                        Text(message)
                    }
                }
            )
        },
        confirmButton = {
            Button(
                enabled = name.isNotBlank(),
                onClick = onConfirm
            ) {
                Text("Сохранить")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Отмена")
            }
        }
    )
}

@Composable
internal fun DeletePlanDialog(
    planName: String,
    error: String?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Удалить план?")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    "План «$planName» будет удалён без возможности восстановления."
                )

                error?.let { message ->
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm
            ) {
                Text("Удалить")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Отмена")
            }
        }
    )
}