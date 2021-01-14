package com.luongvany.k12tt

import com.luongvany.k12tt.style.LoginStyle
import tornadofx.App
import tornadofx.importStylesheet
import com.luongvany.k12tt.view.Main
import com.luongvany.k12tt.view.login.LoginView

class Application : App(LoginView::class, LoginStyle::class) {

    init {
        importStylesheet("/styles.css")
    }
}
