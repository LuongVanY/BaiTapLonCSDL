package com.luongvany.k12tt.view.contact

import com.luongvany.k12tt.controller.ContactController
import com.luongvany.k12tt.model.Sex
import com.luongvany.k12tt.model.StaffEntryModel
import com.luongvany.k12tt.model.StaffEntryTbl.name
import javafx.beans.property.SimpleStringProperty
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class Form(val model: StaffEntryModel): View("Nhân viên form") {
    private val itemController: ContactController by inject()

    override val root = form {
        fieldset {
            field("Mã nhân viên") {
                textfield(model.id) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it == "0" -> error("Số phải khác không")
                            itemController.items.any { itemId -> itemId.id.value == it.toInt() } -> error("Id đã trùng")
                            !it.isLong() -> error("Mã nhân viên cần là số")
                            else -> null
                        }
                    }
                }
            }
            field("Tên nhân viên") {

                textfield(model.name) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it.length > 30 -> error("Quá dài")
                            else -> null
                        }

                    }
              }
            }
            field("Quê quán") {
                textfield(model.homeTown) {
                    this.required()
                    validator {
                        when {
                            it.isNullOrEmpty() || it == " " -> error("Không được để trống")
                            it.length > 30 -> error("Quá dài")
                            else -> null
                        }
                    }
                }
            }
            field("Giới tính") {
                combobox<Sex>(model.sex) {
                    items = itemController.gioiTinh
                }
            }
            field("Ngày sinh") {
                datepicker(model.birthDay) {
                    this.required()
                    validator {
                        when {
                            it?.dayOfYear.toString().isEmpty() -> error("Tuổi không hợp lệ")
                            else -> null
                        }
                    }
                }
            }
            field("Mã phòng ban") {
                textfield(model.departmentId)
            }

            field("Ma Luong"){
                textfield(model.salaryId)
            }

            field("Hình Ảnh") {
                textfield (model.img)
                var filter = listOf(FileChooser.ExtensionFilter("File anh png", "*.png"), FileChooser.ExtensionFilter("File anh jpg", "*.jpg"))
                button("Choose"){
                    action {
                        var dir = chooseFile("", filters = filter.toTypedArray(), mode = FileChooserMode.Single).toString()
                       model.img.value = dir.substring(1, dir.length-1)
                    }
                }
            }
        }
    }
}
