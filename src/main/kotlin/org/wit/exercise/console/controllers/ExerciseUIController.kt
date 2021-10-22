package org.wit.exercise.console.controllers

import mu.KotlinLogging
import org.wit.exercise.console.models.ExerciseJSONStore
import org.wit.exercise.console.models.exerciseModel
import org.wit.exercise.console.views.AddexerciseScreen
import org.wit.exercise.console.views.ListexerciseScreen
import org.wit.exercise.console.views.MenuScreen
import tornadofx.Controller
import tornadofx.runLater
import java.nio.file.Files
import java.sql.RowId

class exerciseUIController : Controller() {

    val exercises = ExerciseJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Exercise App" }
    }
    fun add(weekNo: String, weight: String, caloriesConsumed: String, caloriesLost: String){

        var aexercise = exerciseModel(weekNo = weekNo ,weight = weight, caloriesConsumed = caloriesConsumed, caloriesLost = caloriesLost)
            exercises.create(aexercise)
            logger.info("Entry Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListexerciseScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        exercises.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddexerciseScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddexerciseScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListexerciseScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun markNewWeek(){

        var aexercise = exerciseModel(weekNo = "NEW WEEK", weight = "NEW WEEK", caloriesConsumed = "NEW WEEK", caloriesLost = "NEW WEEK")
        exercises.create(aexercise)
        logger.info("New Week Added")
    }

    fun delete(){
        exercises.dee()
        var aexercise = exerciseModel(weekNo = "NEW WEEK", weight = "NEW WEEK", caloriesConsumed = "NEW WEEK", caloriesLost = "NEW WEEK")
        exercises.create(aexercise)
    }
}