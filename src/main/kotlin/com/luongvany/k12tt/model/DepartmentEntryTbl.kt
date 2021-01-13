package com.luongvany.k12tt.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import org.jetbrains.exposed.sql.*

fun ResultRow.toDepartmentEntry() = this[DepartmentEntryTbl.managerId]?.let {
        DepartmentEntry(
                this[DepartmentEntryTbl.departmentId],
                this[DepartmentEntryTbl.departmentName],
                it
        )
    }

object DepartmentEntryTbl: Table(){
    val departmentId = integer("id").autoIncrement().primaryKey()
    val departmentName = varchar("name", 30)
    val managerId = integer("manager id").references(StaffEntryTbl.id).nullable()
}

class DepartmentEntry(departmentId: Int, departmentName: String, managerId: Int){

    val idProperty = SimpleIntegerProperty(departmentId)
    var id by idProperty

    val departmentNameProperty = SimpleStringProperty(departmentName)
    var departmentName by departmentNameProperty

    val managerIdProperty = SimpleIntegerProperty(managerId)
    var managertId by managerIdProperty


}

class DepartmentEntryModel : ItemViewModel<DepartmentEntry>() {
    val id = bind(DepartmentEntry::idProperty)
    val departmentName = bind(DepartmentEntry::departmentNameProperty)
    val managerId = bind(DepartmentEntry::managerIdProperty)
}


