package org.holypresenter.platform.ui.demo

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.holypresenter.platform.ui.panel.HolyPanel
import org.holypresenter.platform.ui.workspace.HolySplitPane
import org.holypresenter.platform.ui.workspace.HolyWorkspace

@Composable
fun PanelDemo() {
    HolyWorkspace(
        toolbar = {
            Text("HolyPresenter PlatformUI Demo")
        },
        content = {
            HolySplitPane(
                ratio = 0.35f,
                left = {
                    HolyPanel(
                        title = "Navigation"
                    ) {
                        Text("Songs")
                        Text("Bible")
                        Text("Media")
                    }
                },
                right = {
                    HolyPanel(
                        title = "Preview"
                    ) {
                        Text("Projector Preview")
                    }
                }
            )
        },
        statusBar = {
            Text("PlatformUI v0.1")
        }
    )
}