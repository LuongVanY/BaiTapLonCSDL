package com.luongvany.k12tt.view.contact

import com.luongvany.k12tt.controller.ContactController
import com.luongvany.k12tt.model.StaffEntryModel
import com.luongvany.k12tt.view.Main
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox
import javafx.scene.shape.Circle
import luongvany.k12tt.util.toStaffEntry
import model.User
import org.controlsfx.control.Notifications
import tornadofx.*
import java.io.FileInputStream

class ContactEditor : Fragment() {
    override val root = VBox()
    private val controller: ContactController by inject()
    private val addContact: AddStaff by inject()

    init {

        root.id = "contact-editor"

        // Configure styling programmatically
        with (root) {
            prefWidth = 300.0
            padding = Insets(20.0)
            spacing = 10.0
            alignment = Pos.TOP_CENTER
        }
    }


    fun edit(contact: StaffEntryModel) {

        root += contact.image()

        root += Label(contact.name.value).apply { addClass("name") }

        root += GridPane().apply {
            vgap = 10.0
            hgap = 10.0

            row {
                label("Name")
                textfield{
                    textProperty().bindBidirectional(contact.name)
                }

            }

            row {
                label("Home town")
                textfield {
                    textProperty().bindBidirectional(contact.homeTown)
                }
            }

            row {
                label("Dob")
                datepicker {
                    valueProperty().bindBidirectional(contact.birthDay)
                }
            }
            row{
               button("Save").apply {
                   GridPane.setColumnSpan(this, 2)

                   setOnAction {
                       controller.edit(contact.toStaffEntry())
                       Notifications.create()
                           .title("The contact was saved")
                           .text("${contact.name.value} was saved")
                           .graphic(contact.thumbnail())
                           .owner(root)
                           .show()
                   }
               }

               button("Delete").apply {
                   GridPane.setColumnSpan(this, 2)

                   setOnAction {
                       controller.delete(contact)
                   }
               }

            }
            row {
                GridPane.setColumnSpan(this, 2)
                button("Add new").apply {
                    GridPane.setColumnSpan(this, 2)

                    setOnAction {
                        addContact.openWindow()
                    }
                }
            }
        }
    }
}
//Su dung ten cua nhan vien de luu tumbail
fun StaffEntryModel.thumbnail() = CircleImage("${System.getProperty("user.dir")}/src/main/resources/img/users/thumbnail-${id.value.hashCode()}.jpg", 40.0)
fun StaffEntryModel.image() = CircleImage("${System.getProperty("user.dir")}/src/main/resources/img/users/medium-${id.value.hashCode()}.jpg", 150.0)

class CircleImage(url: String, size: Double) : ImageView() {
    init {
        fitWidth = size
        isPreserveRatio = true
        isSmooth = true
        isCache = true
        val inputStream = FileInputStream(url)
        this.image = Image(inputStream)
        val glass = Circle(size / 2, size / 2, size / 2)
        clip = glass
    }
}