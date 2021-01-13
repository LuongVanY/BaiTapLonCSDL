package com.luongvany.k12tt.util

import com.luongvany.k12tt.model.CurrentDatabase
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

private var LOG_TO_CONSOLE: Boolean = false

fun enableConsoleLogger(){
    LOG_TO_CONSOLE = true
}

fun <T> execute(command: () -> T): T{
    return transaction(CurrentDatabase.currentConnect){
        command().apply {
            commit()
        }
    }
}

fun isInit(): Boolean{
    try {
        execute {
            TransactionManager.current().exec("use ${CurrentDatabase.User.databaseName};")
        }
    }catch (ex: Exception){
        return false
    }
    return true
}

fun isConnected(): Boolean{
    try {
        createConnect("")
        execute {
            TransactionManager.current().exec("Show databases;")
        }
    }catch (ex: Exception) {
        return false
    }
    return true
}

fun createConnect(nameOfDatabase: String) {
    CurrentDatabase.currentConnect = Database.connect(CurrentDatabase.createDataSrc(nameOfDatabase))
    TransactionManager.manager.newTransaction(Connection.TRANSACTION_SERIALIZABLE)
    enableConsoleLogger()
}

fun disconnectCurrentDbs(){
    transaction {
        close()
    }
}

fun createDatabase(nameOfDatabase: String){
    TransactionManager.currentOrNew(Connection.TRANSACTION_SERIALIZABLE).exec("create database ${nameOfDatabase};")
}