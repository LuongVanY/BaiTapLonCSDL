package com.luongvany.k12tt.view

import com.luongvany.k12tt.controller.LoadingController
import com.luongvany.k12tt.style.InitStyle.Companion.progressBarStyle
import tornadofx.*

class ShowProgress : View("Init application") {
    private val loadingController: LoadingController by inject()
    override fun onDock() {
        currentStage?.height = 700.0
        currentStage?.width = 751.0
        currentStage?.centerOnScreen()
        loadingController.create(currentWindow)
    }

    private val status: TaskStatus by inject()
    override val root = borderpane{
        center = imageview("BackgroundLoading.png")
        bottom = borderpane{
            top = label(status.message)
            center = progressbar(status.progress) {
                style{
                    progressBarStyle
                    prefHeight = 20.px
                    prefWidth = 751.px
                    accentColor = c("red")
                }
            }
        }
    }
}



