package com.luongvany.k12tt

import tornadofx.App
import tornadofx.importStylesheet
import com.luongvany.k12tt.view.Main

class CRMApplication : App(Main::class) {

    init {
        importStylesheet("/styles.css")
    }
}
