package org.wit.exercise.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.exercise.console.controllers.exerciseUIController
import org.wit.exercise.console.models.exerciseModel
import tornadofx.*

class MenuScreen : View("Exercise App - Created by Valeriu Blascu") {

    val exerciseUIController: exerciseUIController by inject()
    val tableContent = exerciseUIController.exercises.findAll()
    val data = tableContent.observable()


    override val root = form {
        setPrefSize(1500.0, 800.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("Exercise App - Track and manage your weight loss")
            text("")
            text("Add an entry everyday to keep on top and manage your weight")
            text("")

                button("Add an entry") {

                    isDefaultButton = true
                    useMaxWidth = false
                    action {
                        runAsyncWithProgress {
                            exerciseUIController.loadAddScreen()
                        }
                    }
                }
                text("")
//                button("List all entries") {
//
//                    isDefaultButton = true
//                    useMaxWidth = false
//                    action {
//                        runAsyncWithProgress {
//                            exerciseUIController.loadListScreen()
//                        }
//                    }
//                }



            runAsyncWithProgress {
                tableview(data) {
                    readonlyColumn("ID", exerciseModel::id)
                    readonlyColumn("Weight", exerciseModel::weight)
                    readonlyColumn("Calories Consumed", exerciseModel::caloriesConsumed)
                    readonlyColumn("Calories Lost", exerciseModel::caloriesLost)
                }
            }







            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = false
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