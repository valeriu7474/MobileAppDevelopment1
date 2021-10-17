package org.wit.exercise.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class exerciseMemStore : exerciseStore {

    val exercises = ArrayList<exerciseModel>()

    override fun findAll(): List<exerciseModel> {
        return exercises
    }

    override fun findOne(id: Long) : exerciseModel? {
        var foundexercise: exerciseModel? = exercises.find { p -> p.id == id }
        return foundexercise
    }

    override fun create(exercise: exerciseModel) {
        exercise.id = getId()
        exercises.add(exercise)
        logAll()
    }

    override fun update(exercise: exerciseModel) {
        var foundexercise = findOne(exercise.id!!)
        if (foundexercise != null) {
            foundexercise.weight = exercise.weight
            foundexercise.caloriesConsumed = exercise.caloriesConsumed
            foundexercise.caloriesLost = exercise.caloriesLost
        }
    }

    override fun delete(exercise: exerciseModel) {
        exercises.remove(exercise)
    }

    internal fun logAll() {
        exercises.forEach { logger.info("${it}") }
    }
}