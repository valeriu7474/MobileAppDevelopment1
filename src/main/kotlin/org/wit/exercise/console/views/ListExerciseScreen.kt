package org.wit.exercise.console.views


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
            tab("Screen 1", VBox()) {
                tableview(data){

                    readonlyColumn("ID", exerciseModel::id)
                    readonlyColumn("Week Number", exerciseModel::weekNo)
                    readonlyColumn("Weight", exerciseModel::weight)
                    readonlyColumn("Calories Consumed", exerciseModel::caloriesConsumed)
                    readonlyColumn("Calories Lost", exerciseModel::caloriesLost)
                }
            }
            tab("Screen 2", HBox()) {
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
//
//
//
//
//
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