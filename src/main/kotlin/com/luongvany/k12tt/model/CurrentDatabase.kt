package com.luongvany.k12tt.model

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javafx.beans.property.SimpleStringProperty
import org.jetbrains.exposed.sql.Database

object CurrentDatabase{
    var currentConnect: Database? = null
    object User{
        const val driver = "com.mysql.cj.jdbc.Driver"
        const val url = "jdbc:mysql://localhost/"
        var databaseName = "BaiTapLon"
        var userName = SimpleStringProperty()
            get() = field.value?.let { SimpleStringProperty(it.trim()) }?:field
        var password = SimpleStringProperty()
    }
    fun createDataSrc(nameOfDatabase: String) = HikariDataSource(HikariConfig().apply {
        jdbcUrl = "${User.url}${nameOfDatabase}"
        driverClassName = User.driver
        username        = User.userName.value
        password        = User.password.value
        maximumPoolSize = 10
    })
}