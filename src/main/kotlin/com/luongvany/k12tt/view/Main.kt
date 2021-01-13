package com.luongvany.k12tt.view

import com.luongvany.k12tt.model.DepartmentEntryTbl
import com.luongvany.k12tt.model.LuongEntryTbl
import com.luongvany.k12tt.model.StaffEntryTbl
import com.luongvany.k12tt.util.createConnect
import com.luongvany.k12tt.util.disconnectCurrentDbs
import com.luongvany.k12tt.util.enableConsoleLogger
import com.luongvany.k12tt.util.execute
import com.luongvany.k12tt.view.contact.ContactView
import com.luongvany.k12tt.view.project.ProjectList
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import tornadofx.View
import tornadofx.control.ListMenu

class Main : View() {
    override val root: BorderPane by fxml("/view/Main.fxml")

    val contactView: ContactView by inject()
    val projectList: ProjectList by inject()
    val settingsView: SettingsView by inject()

    val menu: ListMenu by fxid("menu")
    val headerText: Label by fxid("headerText")

    init {
        title = "Application"
        createConnect("BaiTapLon")
        enableConsoleLogger()

//        execute {
//            SchemaUtils.create(LuongEntryTbl, StaffEntryTbl, DepartmentEntryTbl)
//            LuongEntryTbl.insert {
//                it[maLuong] = 1
//                it[bacLuong] = "2"
//                it[luongCoBan] = 10000
//                it[heSoLuong] = "1,2"
//            }
//            StaffEntryTbl.insert {
//                it[id] = 1
//                it[name] = "Stacey Ferguson"
//                it[sex] = "NAM"
//                it[homeTown] = "Viet nam"
//                it[dob] = DateTime.now()
//                it[departmentId] = null
//                it[salaryId] = 1
//                it[img] = "person-male.png"
//            }
//            DepartmentEntryTbl.insert {
//                it[departmentId] = 1
//                it[departmentName] = "HanhChinh"
//                it[managerId] = 1
//            }
//            StaffEntryTbl.update ({
//                StaffEntryTbl.id eq 1
//            }){
//                it[departmentId] = 1
//            }
//        }
        // Listen to menu selection and show the corresponding view in the editor area
        menu.activeProperty().addListener { _, _, newItem ->
            when (newItem.text) {
                "Contacts" -> show(contactView)
                "Settings" -> show(settingsView)
                "Projects" -> show(projectList)
            }
        }
    }

    private fun show(view: View) {
        root.center = view.root
        headerText.textProperty().bind(view.titleProperty)
    }
}