package com.luongvany.k12tt.view.contact

import com.luongvany.k12tt.controller.ContactController
import com.luongvany.k12tt.controller.MainController
import com.luongvany.k12tt.model.StaffEntryModel
import luongvany.k12tt.util.toStaffEntry
import org.controlsfx.control.Notifications
import tornadofx.*

class AddStaff: View("Thêm nhân viên"){
    private val model = StaffEntryModel()
    private val itemController: ContactController by inject()
    private val formView = Form(model)

    override val root = borderpane(){
            center = formView.root
            bottom = button("Save"){
                enableWhen(model.valid)
                action{
                    model.commit{
                        itemController.add(model.toStaffEntry())
                        model.rollback()
                    }
                }
            }
        }
    }
