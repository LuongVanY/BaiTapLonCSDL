package com.luongvany.k12tt.controller

import com.luongvany.k12tt.model.CurrentDatabase
import com.luongvany.k12tt.util.*
import com.luongvany.k12tt.view.ShowProgress
import com.luongvany.k12tt.view.login.LoginView
import javafx.stage.StageStyle
import tornadofx.*

class LoginController : Controller(){

    private val loginView: LoginView by inject()
    private val showProgress: ShowProgress by inject()
    fun login(){
        if (isConnected()){
            disconnectCurrentDbs()
            information("Chào mừng đã trở lại chương trình")
            createConnect(CurrentDatabase.User.databaseName)
            loginView.currentStage?.close()
            showProgress.openWindow(StageStyle.UNDECORATED)
        }
        else{
            error("Sai tên đăng nhập hoặc mật khẩu\nVui lòng liên hệ admin")
        }
    }
}