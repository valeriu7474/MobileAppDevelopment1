package org.wit.exercise.console.controllers

import mu.KotlinLogging
import org.wit.exercise.console.models.ExerciseJSONStore
import org.wit.exercise.console.models.exerciseModel
import org.wit.exercise.console.views.exerciseView

class exerciseController {

     val exercises = ExerciseJSONStore()
    val exerciseView = exerciseView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Exercise App - CONSOLE VERSION" }
        println("Exercise App - Kotlin App Version 4.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Exercise App - Console" }
    }

    fun menu() :Int { return exerciseView.menu() }

    fun add(){
        var aexercise = exerciseModel()

        if (exerciseView.addexerciseData(aexercise))
            exercises.create(aexercise)
        else
            logger.info("Exercise Not Added")
    }

    fun list() {
        exerciseView.listexercises(exercises)
    }

    fun update() {
        exerciseView.listexercises(exercises)
        var searchId = exerciseView.getId()
        val aexercise = search(searchId)

        if(aexercise != null) {
            if(exerciseView.updateexerciseData(aexercise)) {
                exercises.update(aexercise)
                exerciseView.showexercise(aexercise)
                logger.info("Entry Updated : [ $aexercise ]")
            }
            else
                logger.info("Entry Not Updated")
        }
        else
            println("Entry Not Updated...")
    }

    fun delete() {
        exerciseView.listexercises(exercises)
        var searchId = exerciseView.getId()
        val aexercise = search(searchId)

        if(aexercise != null) {
            exercises.delete(aexercise)
            println("Entry Deleted...")
            exerciseView.listexercises(exercises)
        }
        else
            println("Entry Not Deleted...")
    }

    fun search() {
        val aexercise = search(exerciseView.getId())!!
        exerciseView.showexercise(aexercise)
    }


    fun search(id: Long) : exerciseModel? {
        var foundexercise = exercises.findOne(id)
        return foundexercise
    }


}