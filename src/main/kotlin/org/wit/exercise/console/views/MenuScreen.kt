package org.wit.exercise.console.views

import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import org.wit.exercise.console.controllers.exerciseUIController
import org.wit.exercise.console.models.exerciseModel
import tornadofx.*


class MenuScreen : View("Exercise App - Created by Valeriu Blascu") {

    val exerciseUIController: exerciseUIController by inject()
    val tableContent = exerciseUIController.exercises.findAll()
    val data = tableContent.observable()


    override val root = form {





        setPrefSize(600.0, 300.0)



        fieldset(labelPosition = Orientation.VERTICAL) {
            text("Exercise App - Track and manage your weight loss")
            {
                style {
                    font = Font.font(20.0)
                    fontWeight = FontWeight.EXTRA_BOLD
                }
            }
            text("")
            text("Add an entry everyday to keep on top and manage your weight")
            {
                style {
                    fontWeight = FontWeight.BOLD
                    borderRadius += box(40.0.px)
                }
            }
            text("")

            line {
                startX = 0.0
                startY = 50.0
                endX = 700.0
                endY = 50.0
            }



            text("")
            text("Add your daily entry")
            text("")

                button("Add Entry") {

                    isDefaultButton = true
                    useMaxWidth = false
                    action {
                        runAsyncWithProgress {
                            exerciseUIController.loadAddScreen()
                        }
                    }
                }
                text("")

                button("List All Entries") {

                    isDefaultButton = true
                    useMaxWidth = false
                    action {
                        runAsyncWithProgress {
                            exerciseUIController.loadListScreen()
                        }
                    }
                }

            text("")

            text("Start a new week")
            text("")
            button("New Week") {

                isDefaultButton = true
                useMaxWidth = false
                action {
                    runAsyncWithProgress {
                        exerciseUIController.markNewWeek()

                    }
                }
            }


            text("")

            line {
                startX = 0.0
                startY = 50.0
                endX = 700.0
                endY = 50.0
            }
            text("")

            text("Delete An Entry")
            text("")
            val aentries = FXCollections.observableArrayList("Austin",
                "Dallas","Midland","San Antonio","Fort Worth")

            combobox(values = aentries)


            text("")

            button("Delete") {

                isDefaultButton = true
                useMaxWidth = false
                action {

                    exerciseUIController.delete()

                }
            }

            text("")

            line {
                startX = 0.0
                startY = 50.0
                endX = 700.0
                endY = 50.0
            }






            text("")
            text("Update an entry")



            text("")

            button("Update") {

                isDefaultButton = true
                useMaxWidth = false
                action {

                    exerciseUIController.delete()

                }
            }

//            runAsyncWithProgress {
//                tableview(data) {
//
//                    column("ID", exerciseModel::id)
//                    readonlyColumn("Weight", exerciseModel::weight)
//                    readonlyColumn("Calories Consumed", exerciseModel::caloriesConsumed)
//                    readonlyColumn("Calories Lost", exerciseModel::caloriesLost)
//
//                }
//            }









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