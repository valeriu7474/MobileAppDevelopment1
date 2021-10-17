package org.wit.exercise.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.wit.exercise.console.helpers.exists
import org.wit.exercise.console.helpers.read
import org.wit.exercise.console.helpers.write

import org.wit.exercise.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "exercises.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<exerciseModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ExerciseJSONStore : exerciseStore {

    var exercises = mutableListOf<exerciseModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<exerciseModel> {
        return exercises
    }

    override fun findOne(id: Long) : exerciseModel? {
        var foundexercise: exerciseModel? = exercises.find { p -> p.id == id }
        return foundexercise
    }

    override fun create(exercise: exerciseModel) {
        exercise.id = generateRandomId()
        exercises.add(exercise)
        serialize()
    }

    override fun update(exercise: exerciseModel) {
        var foundexercise = findOne(exercise.id!!)
        if (foundexercise != null) {
            foundexercise.weight = exercise.weight
            foundexercise.caloriesConsumed = exercise.caloriesConsumed
            foundexercise.caloriesLost = exercise.caloriesLost
        }
         serialize()
    }

    override fun delete(exercise: exerciseModel) {
        exercises.remove(exercise)
        serialize()
    }

    internal fun logAll() {
        exercises.forEach { logger.info("$it") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(exercises, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        exercises = Gson().fromJson(jsonString, listType)
    }
}