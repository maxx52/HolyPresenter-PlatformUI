package org.holypresenter.platform.ui.planner

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import holypresenter.org.platform.api.planner.PlannerInfo
import holypresenter.org.platform.api.planner.PlannerItem
import holypresenter.org.platform.api.planner.PlannerService
import org.holypresenter.platform.ui.workspace.HolySidePane

@Composable
fun PlannerSidePane(
    plannerService: PlannerService?,
    modifier: Modifier = Modifier,
    emptyStateText: String = "Добавьте элемент в план",
    onItemClick: (
        item: PlannerItem,
        index: Int
    ) -> Unit = { _, _ -> }
) {
    val items = plannerService
        ?.state
        ?.items
        .orEmpty()

    val activeItemIndex = plannerService
        ?.state
        ?.activeItemIndex

    val currentPlanName = plannerService?.currentPlanName
    val availablePlans = plannerService?.plans.orEmpty()

    var showSaveAsDialog by remember {
        mutableStateOf(false)
    }

    var showOpenDialog by remember {
        mutableStateOf(false)
    }

    var showNewPlanDialog by remember {
        mutableStateOf(false)
    }

    var saveAsName by remember {
        mutableStateOf("")
    }

    var saveAsError by remember {
        mutableStateOf<String?>(null)
    }

    var planToRename by remember {
        mutableStateOf<PlannerInfo?>(null)
    }

    var renameName by remember {
        mutableStateOf("")
    }

    var renameError by remember {
        mutableStateOf<String?>(null)
    }

    var planToDelete by remember {
        mutableStateOf<PlannerInfo?>(null)
    }

    var deleteError by remember {
        mutableStateOf<String?>(null)
    }

    HolySidePane(
        title = "План служения",
        modifier = modifier
    ) {
        PlannerFileControls(
            currentPlanName = currentPlanName,
            enabled = plannerService != null,
            onNewPlan = {
                if (items.isEmpty()) {
                    plannerService?.newPlan()
                } else {
                    showNewPlanDialog = true
                }
            },
            onOpenPlan = {
                showOpenDialog = true
            },
            onSaveAs = {
                saveAsName = ""
                saveAsError = null
                showSaveAsDialog = true
            }
        )

        Spacer(Modifier.height(12.dp))

        HorizontalDivider()

        Spacer(Modifier.height(12.dp))

        PlannerItemsList(
            items = items,
            activeItemIndex = activeItemIndex,
            emptyStateText = emptyStateText,
            modifier = Modifier.weight(1f),
            onMove = { fromIndex, toIndex ->
                plannerService?.move(
                    fromIndex = fromIndex,
                    toIndex = toIndex
                )
            },
            onRemove = { item ->
                plannerService?.remove(item)
            },
            onItemClick = onItemClick
        )
    }

    if (showNewPlanDialog) {
        NewPlanDialog(
            onConfirm = {
                plannerService?.newPlan()
                showNewPlanDialog = false
            },
            onDismiss = {
                showNewPlanDialog = false
            }
        )
    }

    if (showSaveAsDialog) {
        SavePlanAsDialog(
            name = saveAsName,
            error = saveAsError,
            onNameChange = { value ->
                saveAsName = value
                saveAsError = null
            },
            onConfirm = {
                val saved = plannerService?.saveAs(saveAsName) == true

                if (saved) {
                    showSaveAsDialog = false
                    saveAsName = ""
                    saveAsError = null
                } else {
                    saveAsError = "Введите уникальное название"
                }
            },
            onDismiss = {
                showSaveAsDialog = false
                saveAsError = null
            }
        )
    }

    if (showOpenDialog) {
        OpenPlanDialog(
            plans = availablePlans,
            currentPlanId = plannerService?.currentPlanId,
            onOpen = { plan ->
                val opened = plannerService?.openPlan(plan.id) == true

                if (opened) {
                    showOpenDialog = false
                }
            },
            onRename = { plan ->
                planToRename = plan
                renameName = plan.name
                renameError = null
                showOpenDialog = false
            },
            onDelete = { plan ->
                planToDelete = plan
                deleteError = null
                showOpenDialog = false
            },
            onDismiss = {
                showOpenDialog = false
            }
        )
    }

    planToRename?.let { plan ->
        RenamePlanDialog(
            name = renameName,
            error = renameError,
            onNameChange = { value ->
                renameName = value
                renameError = null
            },
            onConfirm = {
                val renamed =
                    plannerService?.renamePlan(
                        planId = plan.id,
                        newName = renameName
                    ) == true

                if (renamed) {
                    planToRename = null
                    renameName = ""
                    renameError = null
                    showOpenDialog = true
                } else {
                    renameError = "Введите уникальное название"
                }
            },
            onDismiss = {
                planToRename = null
                renameError = null
                showOpenDialog = true
            }
        )
    }

    planToDelete?.let { plan ->
        DeletePlanDialog(
            planName = plan.name,
            error = deleteError,
            onConfirm = {
                val deleted = plannerService?.deletePlan(plan.id) == true

                if (deleted) {
                    planToDelete = null
                    deleteError = null
                    showOpenDialog = true
                } else {
                    deleteError = "Не удалось удалить план"
                }
            },
            onDismiss = {
                planToDelete = null
                deleteError = null
                showOpenDialog = true
            }
        )
    }
}