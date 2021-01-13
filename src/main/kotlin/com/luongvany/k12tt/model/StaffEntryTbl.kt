package com.luongvany.k12tt.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import luongvany.k12tt.util.convertToSex
import luongvany.k12tt.util.toJavaLocalDate
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import tornadofx.*
import java.time.LocalDate

fun ResultRow.toStaffEntry() = this[StaffEntryTbl.departmentId]?.let {
    StaffEntry(
            this[StaffEntryTbl.id],
            this[StaffEntryTbl.name],
            this[StaffEntryTbl.homeTown],
            this[StaffEntryTbl.sex].convertToSex(),
            this[StaffEntryTbl.dob].toJavaLocalDate(),
            it,
            this[StaffEntryTbl.salaryId],
            this[StaffEntryTbl.img]
    )
}


object StaffEntryTbl: Table(){
    val id = integer("id").primaryKey()
    val name = varchar("name", 30)
    val homeTown = varchar("homeTown", 30)
    val sex = varchar("sex", 30)
    val dob = date("date")
    val departmentId = integer("departmentID").references(DepartmentEntryTbl.departmentId).nullable()
    val salaryId = integer("salaryId").references(LuongEntryTbl.maLuong)
    val img = varchar("imagine", 100)
}

class StaffEntry(id: Int, name: String, homeTown: String, sex: Sex,
                 birthDay: LocalDate, departmentId: Int, salaryId: Int, imgUrl: String){
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

    val homeTownProperty = SimpleStringProperty(homeTown)
    var homeTown by homeTownProperty

    val sexProperty = SimpleObjectProperty<Sex>(sex)
    var sex by sexProperty

    val birthDayProperty = SimpleObjectProperty<LocalDate>(birthDay)
    var birthDay by birthDayProperty

    val departmentIdProperty = SimpleIntegerProperty(departmentId)
    var departmentId by departmentIdProperty

    val salaryIdProperty = SimpleIntegerProperty(salaryId)
    var salaryId by salaryIdProperty

    val imgProperty = SimpleStringProperty(imgUrl)
    var img by imgProperty
}

class StaffEntryModel: ItemViewModel<StaffEntry>(){

    val id = bind{item?.idProperty}
    val name = bind{item?.nameProperty}
    val homeTown = bind{item?.homeTownProperty}
    val sex = bind{item?.sexProperty}
    val birthDay = bind{item?.birthDayProperty}
    val departmentId = bind{item?.departmentIdProperty}
    val salaryId = bind{item?.salaryIdProperty}
    val img = bind{item?.imgProperty}

    init{
        img.value = "person-male.png"
    }
}