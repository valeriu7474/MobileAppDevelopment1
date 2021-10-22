package org.wit.exercise.console.views


import javafx.collections.FXCollections
import javafx.scene.control.ContextMenu
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.wit.exercise.console.controllers.exerciseUIController
import org.wit.exercise.console.models.JSON_FILE
import org.wit.exercise.console.models.exerciseModel
import tornadofx.*
import java.time.LocalDate

class ListexerciseScreen : View("List All Entries") {

    val exerciseUIController: exerciseUIController by inject()
    val tableContent = exerciseUIController.exercises.findAll()
    val data = tableContent.observable()

    override val root = vbox {
        setPrefSize(1500.0, 800.0)

        text("")

        tabpane {
            tab("This Week", VBox()) {

                tableview(data){

                    readonlyColumn("ID", exerciseModel::id)
                    readonlyColumn("Week Number", exerciseModel::weekNo)
                    readonlyColumn("Weight", exerciseModel::weight)
                    readonlyColumn("Calories Consumed", exerciseModel::caloriesConsumed)
                    readonlyColumn("Calories Lost", exerciseModel::caloriesLost)

                    contextMenu = ContextMenu().apply {
                        menuitem("Delete") {
                            selectedItem?.let { data.remove(it) }
                        }
                    }
                }
            }

            tab("Last Week", HBox()) {
                tableview(data){
                    readonlyColumn("ID", exerciseModel::id)
                    readonlyColumn("Week Number", exerciseModel::weekNo)
                    readonlyColumn("Weight", exerciseModel::weight)
                    readonlyColumn("Calories Consumed", exerciseModel::caloriesConsumed)
                    readonlyColumn("Calories Lost", exerciseModel::caloriesLost)
                }
            }
        }
//
//        tableview(data) {
//            readonlyColumn("ID", exerciseModel::id)
//            readonlyColumn("Week Number", exerciseModel::weekNo)
//            readonlyColumn("Weight", exerciseModel::weight)
//            readonlyColumn("Calories Consumed", exerciseModel::caloriesConsumed)
//            readonlyColumn("Calories Lost", exerciseModel::caloriesLost)

//        }

        text("")

        button("Close") {
            useMaxWidth = false
            action {
                runAsyncWithProgress {
                    exerciseUIController.closeList()
                }
            }
        }
    }
}