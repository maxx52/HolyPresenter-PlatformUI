package org.holypresenter.platform.ui.planner

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun NewPlanDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Создать новый план?")
        },
        text = { Text("Текущий план будет закрыт. Именованный план уже сохранён автоматически.")
        },
        confirmButton = {
            Button(
                onClick = onConfirm
            ) {
                Text("Создать")
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
internal fun SavePlanAsDialog(
    name: String,
    error: String?,
    onNameChange: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Сохранить план как")
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