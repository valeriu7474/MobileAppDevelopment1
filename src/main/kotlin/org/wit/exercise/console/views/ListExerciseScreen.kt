package org.wit.exercise.console.views


import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.exercise.console.controllers.exerciseUIController
import org.wit.exercise.console.models.exerciseModel
import tornadofx.*

class ListexerciseScreen : View("List All Entries") {

    val exerciseUIController: exerciseUIController by inject()
    val tableContent = exerciseUIController.exercises.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", exerciseModel::id)
            readonlyColumn("Weight", exerciseModel::weight)
            readonlyColumn("Calories Consumed", exerciseModel::caloriesConsumed)
            readonlyColumn("Calories Lost", exerciseModel::caloriesLost)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    exerciseUIController.closeList()
                }
            }
        }
    }

}