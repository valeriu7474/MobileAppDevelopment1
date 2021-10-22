package org.wit.exercise.console.models

interface exerciseStore {
    fun findAll(): List<exerciseModel>
    fun findOne(id: Long): exerciseModel?
    fun create(exercise: exerciseModel)
    fun update(exercise: exerciseModel)
    fun delete(exercise: exerciseModel)

}