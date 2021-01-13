package com.luongvany.k12tt.view.project

import javafx.scene.layout.HBox
import tornadofx.View
import tornadofx.addClass

class ProjectList : View() {
    override val root = HBox()

    init {
        title = "Projects"
        root.addClass("content")

        root += ProjectChart::class
    }
}