package com.luongvany.k12tt.style

import javafx.scene.text.FontWeight
import tornadofx.*

class LoginStyle: Stylesheet(){
    companion object{
        val login by cssclass()
        val loginWidth = 600.px
        val color = c("gray")
    }

    init{
        val boxSetup = mixin{
            backgroundColor += c("white", 0.9)
            borderWidth += box(5.px)
            borderColor += box(color)
            borderRadius += box(50.px)
        }
        login{
            +boxSetup
            prefWidth = loginWidth
            padding = box(150.px)
            fontSize = 25.px
            label{
                fontSize = 20.px
                fontWeight = FontWeight.LIGHT
            }
            (s(textField, button)){
                +boxSetup
            }
            button{
                and(hover){
                    backgroundColor += color
                    textFill = c("white")
                }
                indicator{
                    prefWidth = 10.px
                }
                prefWidth = loginWidth
            }
            progressIndicator{
                prefWidth = 16.px
            }
        }
    }
}