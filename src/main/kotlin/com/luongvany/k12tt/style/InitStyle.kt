package com.luongvany.k12tt.style

import javafx.scene.paint.Paint
import tornadofx.*
class InitStyle : Stylesheet(){
    companion object{
        val progressBarStyle by cssclass()
        val progressBarColor by cssproperty<Paint>("-fx-accent")
    }
    init {
         progressBarStyle{

             progressBarColor.value = c("red")
             progressBar{
                 prefHeight = 20.px
                 prefWidth = 751.px
                 progressBarColor.value = c("red")
             }
        }

    }

}