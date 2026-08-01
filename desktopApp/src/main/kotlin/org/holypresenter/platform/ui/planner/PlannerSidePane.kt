package org.holypresenter.platform.ui.planner

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import holypresenter.org.platform.api.planner.PlannerItem
import holypresenter.org.platform.api.planner.PlannerService
import org.holypresenter.platform.ui.workspace.HolySidePane

@Composable
fun PlannerSidePane(
    plannerService: PlannerService?,
    modifier: Modifier = Modifier,
    emptyStateText: String =
        "Добавьте элемент в план",
    onItemClick: (
        item: PlannerItem,
        index: Int
    ) -> Unit = { _, _ -> }
) {
    val items =
        plannerService
            ?.state
            ?.items
            .orEmpty()

    val activeItemIndex =
        plannerService
            ?.state
            ?.activeItemIndex

    val currentPlanName =
        plannerService?.currentPlanName

    val availablePlans =
        plannerService?.plans.orEmpty()

    val dialogs =
        rememberPlannerDialogState()

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
                    dialogs.openNewPlanDialog()
                }
            },
            onOpenPlan = {
                dialogs.openPlansDialog()
            },
            onSaveAs = {
                dialogs.openSaveAsDialog()
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        HorizontalDivider()

        Spacer(
            modifier = Modifier.height(12.dp)
        )

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

    if (dialogs.showNewPlanDialog) {
        NewPlanDialog(
            onConfirm = {
                plannerService?.newPlan()
                dialogs.closeNewPlanDialog()
            },
            onDismiss = {
                dialogs.closeNewPlanDialog()
            }
        )
    }

    if (dialogs.showSaveAsDialog) {
        SavePlanAsDialog(
            name = dialogs.saveAsName,
            error = dialogs.saveAsError,
            onNameChange = { value ->
                dialogs.changeSaveAsName(value)
            },
            onConfirm = {
                val saved =
                    plannerService?.saveAs(
                        dialogs.saveAsName
                    ) == true

                if (saved) {
                    dialogs.completeSaveAs()
                } else {
                    dialogs.showSaveAsError(
                        "Введите уникальное название"
                    )
                }
            },
            onDismiss = {
                dialogs.closeSaveAsDialog()
            }
        )
    }

    if (dialogs.showOpenDialog) {
        OpenPlanDialog(
            plans = availablePlans,
            currentPlanId =
                plannerService?.currentPlanId,
            onOpen = { plan ->
                val opened =
                    plannerService?.openPlan(
                        plan.id
                    ) == true

                if (opened) {
                    dialogs.closePlansDialog()
                }
            },
            onRename = { plan ->
                dialogs.beginRename(plan)
            },
            onDelete = { plan ->
                dialogs.beginDelete(plan)
            },
            onDismiss = {
                dialogs.closePlansDialog()
            }
        )
    }

    dialogs.planToRename?.let { plan ->
        RenamePlanDialog(
            name = dialogs.renameName,
            error = dialogs.renameError,
            onNameChange = { value ->
                dialogs.changeRenameName(value)
            },
            onConfirm = {
                val renamed =
                    plannerService?.renamePlan(
                        planId = plan.id,
                        newName = dialogs.renameName
                    ) == true

                if (renamed) {
                    dialogs.completeRename()
                } else {
                    dialogs.showRenameError(
                        "Введите уникальное название"
                    )
                }
            },
            onDismiss = {
                dialogs.closeRenameDialog()
            }
        )
    }

    dialogs.planToDelete?.let { plan ->
        DeletePlanDialog(
            planName = plan.name,
            error = dialogs.deleteError,
            onConfirm = {
                val deleted =
                    plannerService?.deletePlan(
                        plan.id
                    ) == true

                if (deleted) {
                    dialogs.completeDelete()
                } else {
                    dialogs.showDeleteError(
                        "Не удалось удалить план"
                    )
                }
            },
            onDismiss = {
                dialogs.closeDeleteDialog()
            }
        )
    }
}