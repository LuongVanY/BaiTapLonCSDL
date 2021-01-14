package com.luongvany.k12tt.controller

import com.luongvany.k12tt.model.CurrentDatabase
import com.luongvany.k12tt.util.createConnect
import com.luongvany.k12tt.util.disconnectCurrentDbs
import com.luongvany.k12tt.util.execute
import com.luongvany.k12tt.view.Main
import com.luongvany.k12tt.view.ShowProgress
import javafx.stage.StageStyle
import javafx.stage.Window

import luongvany.k12tt.util.*
import org.jetbrains.exposed.sql.SchemaUtils
import tornadofx.Controller

class LoadingController : Controller() {
    val mainController: MainController by inject()
    private val showProcess: ShowProgress by inject()
    private val mainView: Main by inject()
    fun create(current: Window?) {
        val size = mainController.listOfObject.size.toDouble()-1
        var index = 0.0
       createConnect(CurrentDatabase.User.databaseName)
        runAsync {
            for(i in mainController.listOfObject){
                updateProgress((index), size)
                updateMessage("Check ${i.tableName}...")
                execute {
                    SchemaUtils.create(i)
                }
                index+=1
            }
            updateMessage("Complete!")
            Thread.sleep(1000)
        }ui{
            try {
                current?.hide()
            }finally {
                disconnectCurrentDbs()
                createConnect(CurrentDatabase.User.databaseName)
                showProcess.currentStage?.close()
                mainView.openWindow(StageStyle.DECORATED)
            }
        }
    }
}