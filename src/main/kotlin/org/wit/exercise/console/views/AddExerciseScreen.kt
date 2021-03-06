package org.wit.exercise.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.exercise.console.controllers.exerciseUIController
import tornadofx.*

class AddexerciseScreen : View("Add exercise") {
    val model = ViewModel()
    val weekNo = model.bind { SimpleStringProperty() }
    val weight = model.bind { SimpleStringProperty() }
    val caloriesConsumed = model.bind { SimpleStringProperty() }
    val caloriesLost = model.bind { SimpleStringProperty() }
    val exerciseUIController: exerciseUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Enter your week no") {
                textfield(weekNo).required()
            }
            field("Enter your weight") {
                textfield(weight).required()
            }
            field("Enter calories consumed") {
                textfield(caloriesConsumed).required()
            }
            field("Enter calories lost") {
                textfield(caloriesLost).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        exerciseUIController.add(weekNo.toString(),weight.toString(),caloriesConsumed.toString(),caloriesLost.toString())
                    }

                }
            }

            button("Back to home screen") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        exerciseUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        weight.value = ""
        caloriesConsumed.value = ""
        caloriesLost.value = ""
        model.clearDecorators()
    }
}