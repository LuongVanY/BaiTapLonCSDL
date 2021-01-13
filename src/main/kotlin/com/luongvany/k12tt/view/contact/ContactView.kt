package com.luongvany.k12tt.view.contact

import com.luongvany.k12tt.model.StaffEntryModel
import javafx.scene.layout.BorderPane
import model.User
import tornadofx.View
import tornadofx.addClass
import tornadofx.onSelectionChange

class ContactView : View() {
    override val root = BorderPane()

    private val contactList: ContactList by inject()

    init {
        title = "Contacts"
        root.addClass("content")

        with (contactList.root) {
            root.center = this

            onSelectionChange {
                editUser(it)
            }
        }

        editUser(null)
    }

    private fun editUser(selectedUser: StaffEntryModel?) {
        val detail = find(ContactEditor::class)
        if (selectedUser != null)
            detail.edit(selectedUser)
        root.right = detail.root
    }
}