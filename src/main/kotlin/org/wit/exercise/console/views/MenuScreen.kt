package org.wit.exercise.console.views

import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.control.ContextMenu
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import org.wit.exercise.console.controllers.exerciseUIController
import org.wit.exercise.console.models.exerciseModel
import tornadofx.*
import tornadofx.Stylesheet.Companion.selected


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

            hbox {
                button("Add Entry") {

                    hboxConstraints {
                        margin = Insets(15.0)
                    }

                    style {
                        fontWeight = FontWeight.BOLD
                    }

                    isDefaultButton = true
                    useMaxWidth = false
                    action {
                        runAsyncWithProgress {
                            exerciseUIController.loadAddScreen()
                        }
                    }
                }

                button("List All Entries") {
                    hboxConstraints {
                        margin = Insets(15.0)
                    }

                    isDefaultButton = true
                    useMaxWidth = false
                    action {
                        runAsyncWithProgress {
                            exerciseUIController.loadListScreen()
                        }
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

            text("Start a new week")

            hbox {
                hbox {
                    button("New Week") {

                        hboxConstraints {
                            margin = Insets(15.0)
                        }

                        isDefaultButton = true
                        useMaxWidth = false
                        action {
                            runAsyncWithProgress {
                                exerciseUIController.markNewWeek()
                            }
                        }
                    }
                }

                hbox {
                    button("Erase the week") {
                        hboxConstraints {
                            margin = Insets(15.0)
                        }

                        isDefaultButton = true
                        useMaxWidth = false
                        action {
                            runAsyncWithProgress {
                                exerciseUIController.delete()
                            }
                        }
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
            text("Right Click on selected option to delete")
            hbox {

                val aentries = exerciseUIController.exercises.findAll()
                combobox(values = aentries) {
                    hboxConstraints {
                        margin = Insets(15.0)
                    }

                    contextMenu = ContextMenu().apply{
                        menuitem("Delete"){
                            selectedItem?.let{ data.remove(it) }

                        }
                    }
                }
            }

            hbox{
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

            hbox {
                button("Update") {

                    hboxConstraints {
                        margin = Insets(15.0)
                    }

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

            }
            text("")

            line {
                startX = 0.0
                startY = 50.0
                endX = 700.0
                endY = 50.0
            }

            text("")

            hbox {
                button("Exit") {
                    hboxConstraints {
                        margin = Insets(15.0)
                    }
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
}