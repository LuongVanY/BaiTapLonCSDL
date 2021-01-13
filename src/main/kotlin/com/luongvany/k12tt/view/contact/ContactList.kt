package com.luongvany.k12tt.view.contact

import com.luongvany.k12tt.controller.ContactController
import com.luongvany.k12tt.model.StaffEntryModel
import javafx.scene.Node
import javafx.scene.image.ImageView
import model.User
import tornadofx.*

class ContactList : View() {
    val controller: ContactController by inject()
    init {
        title = "Contacts"

        runAsync{
            controller.listOfItems
        }ui {
            root.items = it
            root.selectFirst()
        }
    }
    override val root  = tableview(controller.listOfItems) {
        column("Name", StaffEntryModel::name){
            prefWidth = 200.0
            cellFormat {
                with (tableRow.item) {
                    if(this!=null){
                        val thumb = thumbnail()
                        graphic = thumb
                        text = name.value
                    }

                }
            }
        }
        column("Date of Birth", StaffEntryModel::birthDay)

        column("Sex", StaffEntryModel::sex)

        column("Home Town", StaffEntryModel::homeTown)
    }

}
