package org.holypresenter.platform.ui.planner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import holypresenter.org.platform.api.planner.PlannerInfo

@Stable
internal class PlannerDialogState {
    var showNewPlanDialog by mutableStateOf(false)
        private set

    var showSaveAsDialog by mutableStateOf(false)
        private set

    var showOpenDialog by mutableStateOf(false)
        private set

    var saveAsName by mutableStateOf("")
        private set

    var saveAsError by mutableStateOf<String?>(null)
        private set

    var planToRename by mutableStateOf<PlannerInfo?>(null)
        private set

    var renameName by mutableStateOf("")
        private set

    var renameError by mutableStateOf<String?>(null)
        private set

    var planToDelete by mutableStateOf<PlannerInfo?>(null)
        private set

    var deleteError by mutableStateOf<String?>(null)
        private set

    fun openNewPlanDialog() {
        showNewPlanDialog = true
    }

    fun closeNewPlanDialog() {
        showNewPlanDialog = false
    }

    fun openSaveAsDialog() {
        saveAsName = ""
        saveAsError = null
        showSaveAsDialog = true
    }

    fun changeSaveAsName(value: String) {
        saveAsName = value
        saveAsError = null
    }

    fun showSaveAsError(message: String) {
        saveAsError = message
    }

    fun completeSaveAs() {
        showSaveAsDialog = false
        saveAsName = ""
        saveAsError = null
    }

    fun closeSaveAsDialog() {
        showSaveAsDialog = false
        saveAsError = null
    }

    fun openPlansDialog() {
        showOpenDialog = true
    }

    fun closePlansDialog() {
        showOpenDialog = false
    }

    fun beginRename(plan: PlannerInfo) {
        planToRename = plan
        renameName = plan.name
        renameError = null
        showOpenDialog = false
    }

    fun changeRenameName(value: String) {
        renameName = value
        renameError = null
    }

    fun showRenameError(message: String) {
        renameError = message
    }

    fun completeRename() {
        planToRename = null
        renameName = ""
        renameError = null
        showOpenDialog = true
    }

    fun closeRenameDialog() {
        planToRename = null
        renameName = ""
        renameError = null
        showOpenDialog = true
    }

    fun beginDelete(plan: PlannerInfo) {
        planToDelete = plan
        deleteError = null
        showOpenDialog = false
    }

    fun showDeleteError(message: String) {
        deleteError = message
    }

    fun completeDelete() {
        planToDelete = null
        deleteError = null
        showOpenDialog = true
    }

    fun closeDeleteDialog() {
        planToDelete = null
        deleteError = null
        showOpenDialog = true
    }
}

@Composable
internal fun rememberPlannerDialogState(): PlannerDialogState =
    remember {
        PlannerDialogState()
    }