package com.luongvany.k12tt.controller

import com.luongvany.k12tt.model.DepartmentEntryTbl
import com.luongvany.k12tt.model.StaffEntryTbl
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.Table
import tornadofx.Controller

class MainController: Controller(){
    val listOfObject = listOf<Table>(StaffEntryTbl, DepartmentEntryTbl)

    fun convertToId(name: SimpleStringProperty, keyWord: String) = name.value.substring(4, name.value.indexOf(keyWord)).trim().toInt()
}