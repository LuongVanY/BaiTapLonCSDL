package com.luongvany.k12tt.controller

import com.luongvany.k12tt.model.*
import com.luongvany.k12tt.util.enableConsoleLogger
import com.luongvany.k12tt.util.execute
import com.luongvany.k12tt.view.contact.thumbnail
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import luongvany.k12tt.util.toDate
import luongvany.k12tt.util.toStaffEntryModel
import model.User
import org.controlsfx.control.Notifications
import org.jetbrains.exposed.sql.*
import tornadofx.*
import java.io.File
import java.lang.Exception
import java.nio.file.Path

class ContactController : Controller() {
    val gioiTinh = FXCollections.observableArrayList(Sex.NAM, Sex.NU, Sex.GIOITINHTHUBA)
    var listOfItems: ObservableList<StaffEntryModel> = execute {
        StaffEntryTbl.selectAll().map {
            StaffEntryModel().apply {
                item = it.toStaffEntry()
            }
        }.asObservable()
    }

    var items: ObservableList<StaffEntryModel> by singleAssign()

    init {
        items = listOfItems
    }

    fun add(addItem: StaffEntry): StaffEntry {
        var medium = File("${System.getProperty("user.dir")}/src/main/resources/img/users/medium-${addItem.id.hashCode()}.jpg")
        var thumbnail = File("${System.getProperty("user.dir")}/src/main/resources/img/users/thumbnail-${addItem.id.hashCode()}.jpg")

        File(addItem.img).let {
            it.copyTo(medium, true)
            it.copyTo(thumbnail, true)
        }

        try {
            execute{
                StaffEntryTbl.insert {
                    it[id] = addItem.id
                    it[name] = addItem.name
                    it[sex] = addItem.sex.toString()
                    it[homeTown] = addItem.homeTown
                    it[dob] = addItem.birthDay.toDate()
                    it[departmentId] = addItem.departmentId
                    it[salaryId] = addItem.salaryId
                    it[img] = addItem.img
                }
            }

            listOfItems.add(
                StaffEntryModel().apply {
                    item = addItem
                }
            )

            Notifications.create()
                .title("The contact was added")
                .text("${addItem.name} was added")
                .graphic(addItem.toStaffEntryModel().thumbnail())
                .show()
            information("Success")
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }

        return addItem
    }

    fun delete(model: StaffEntryModel){
        execute {
            StaffEntryTbl.deleteWhere {
                StaffEntryTbl.id eq (model.id.value.toInt())
            }
        }

        File("${System.getProperty("user.dir")}/src/main/resources/img/users/medium-${model.id.hashCode()+model.name.hashCode()}.jpg").apply {
            this.delete()
        }

        listOfItems.remove(model)

        Notifications.create()
            .title("The contact was deleted")
            .text("${model.name.value} was deleted")
            .graphic(model.thumbnail())
            .darkStyle()
            .show()
    }


    fun edit(content: StaffEntry){
        enableConsoleLogger()
        try {
            execute {
                StaffEntryTbl.update({
                    StaffEntryTbl.id eq content.id
                }) {
                    it[name] = content.name
                    it[homeTown] = content.homeTown
                    it[sex] = content.sex.toString()
                    it[dob] = content.birthDay.toDate()
                }
            }
        }catch (ex: Exception){
            error(ex.stackTraceToString())
        }
    }
}