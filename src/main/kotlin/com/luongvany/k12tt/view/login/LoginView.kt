package com.luongvany.k12tt.view.login

import com.luongvany.k12tt.controller.LoginController
import com.luongvany.k12tt.model.CurrentDatabase
import com.luongvany.k12tt.style.LoginStyle.Companion.login
import javafx.geometry.Orientation
import javafx.scene.control.ProgressIndicator
import javafx.scene.image.Image
import tornadofx.*

class LoginView : View("Login") {
    override fun onDock() {
        currentStage?.isResizable = false
    }
    private val controller: LoginController by inject()
    override val root = borderpane(){

        left = imageview(){
            image = Image("LoginBackground.png")
        }

        center = form {
            addClass(login)
            fieldset {
                labelPosition = Orientation.VERTICAL
                field("Tên đăng nhập") {
                    textfield(CurrentDatabase.User.userName)
                }
                field("Mật khẩu") {
                    passwordfield(CurrentDatabase.User.password)
                }
            }
            button("Login"){
                shortcut("enter")
                action{
                    graphic = ProgressIndicator()
                    controller.login()
                }
            }
        }
    }
}
