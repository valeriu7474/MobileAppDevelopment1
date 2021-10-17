package org.wit.exercise.console.views

import org.wit.exercise.console.models.ExerciseJSONStore
import org.wit.exercise.console.models.exerciseMemStore
import org.wit.exercise.console.models.exerciseModel

class exerciseView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add an entry")
        println(" 2. Update an entry")
        println(" 3. List all entries")
        println(" 4. Search entries")
        println(" 5. Delete an entry")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listexercises(exercises : ExerciseJSONStore) {
        println("List all entries")
        println()
        exercises.logAll()
        println()
    }

    fun showexercise(exercise : exerciseModel) {
        if(exercise != null)
            println("Entry Details [ $exercise ]")
        else
            println("This entry was not found...")
    }

    fun addexerciseData(exercise : exerciseModel) : Boolean {

        println()
        print("Enter your weight : ")
        exercise.weight = readLine()!!
        print("Enter calories consumed : ")
        exercise.caloriesConsumed = readLine()!!
        print("Enter calories lost : ")
        exercise.caloriesLost = readLine()!!

        return exercise.weight.isNotEmpty() && exercise.caloriesConsumed.isNotEmpty() && exercise.caloriesLost.isNotEmpty()
    }

    fun updateexerciseData(exercise : exerciseModel) : Boolean {

        var tempweight: String?
        var tempcaloriesConsumed: String?
        var tempcaloriesLost: String?

        if (exercise != null) {
            print("Enter a new weight for [ " + exercise.weight + " ] : ")
            tempweight = readLine()!!
            print("Enter a new calories consumed amount [ " + exercise.caloriesConsumed + " ] : ")
            tempcaloriesConsumed = readLine()!!
            print("Enter a new calories lost amount [ " + exercise.caloriesLost + " ] : ")
            tempcaloriesLost = readLine()!!

            if (!tempweight.isNullOrEmpty() && !tempcaloriesConsumed.isNullOrEmpty() && !tempcaloriesLost.isNullOrEmpty()) {
                exercise.weight = tempweight
                exercise.caloriesConsumed = tempcaloriesConsumed
                exercise.caloriesLost = tempcaloriesLost
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}