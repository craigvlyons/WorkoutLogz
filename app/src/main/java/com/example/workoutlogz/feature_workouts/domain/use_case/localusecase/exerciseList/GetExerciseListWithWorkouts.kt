package com.example.workoutlogz.feature_workouts.domain.use_case.localusecase.exerciseList

import com.example.workoutlogz.feature_workouts.data.models.ExerciseList
import com.example.workoutlogz.feature_workouts.data.models.ExerciseListWithWorkouts
import com.example.workoutlogz.feature_workouts.data.models.Workout
import com.example.workoutlogz.feature_workouts.domain.repository.ExerciseListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetExerciseListWithWorkouts @Inject constructor(
    private val repository: ExerciseListRepository
) {
    suspend operator fun invoke(exerciseListId: Int): Flow<ExerciseListWithWorkouts>? {
        // Exercise List by Id
        var exerciseList: Flow<ExerciseList> = repository.getExerciseListById(exerciseListId)

        if(exerciseList == null) return null

        // all workouts associated with that Exercise List
        val workoutList: Flow<List<Workout>> = repository.getAllWorkoutsInExerciseList(exerciseListId)
        // convert to return object.
        return getExerciseListWithWorkouts(exerciseList, workoutList)
    }
}

// Conversion function for getting the ExerciseListWithWorkouts object, from the 2 separate objects.
fun getExerciseListWithWorkouts(
    exerciseListFlow: Flow<ExerciseList>, // Single ExerciseList object
    workoutsFlow: Flow<List<Workout>> // Flow of List<Workout>
): Flow<ExerciseListWithWorkouts> {
    return combine(exerciseListFlow, workoutsFlow) { exerciseList, workouts ->
        ExerciseListWithWorkouts(exerciseList = exerciseList, workouts = workouts)
    }
}