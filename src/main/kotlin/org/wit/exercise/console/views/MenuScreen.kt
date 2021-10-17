package org.wit.exercise.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.exercise.console.controllers.exerciseUIController
import tornadofx.*

class MenuScreen : View("Exercise App - Created by Valeriu Blascu") {

    val exerciseUIController: exerciseUIController by inject()

    override val root = form {
        setPrefSize(800.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add an entry") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        exerciseUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List all entries") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        exerciseUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}