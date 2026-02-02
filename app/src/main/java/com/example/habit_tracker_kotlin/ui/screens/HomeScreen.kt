package com.example.habit_tracker_kotlin.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habit_tracker_kotlin.model.Habit
import com.example.habit_tracker_kotlin.model.HabitType
import com.example.habit_tracker_kotlin.ui.components.HabitCheckCard
import com.example.habit_tracker_kotlin.ui.components.HabitCounterCard

@Composable
fun HomeScreen() {

    var habits by remember {
        mutableStateOf(
            listOf(
                Habit(
                    id = 1,
                    name = "Drink water",
                    iconId = "water",
                    type = HabitType.COUNTER,
                    countToday = 3,
                    targetPerDay = 8
                ),
                Habit(
                    id = 2,
                    name = "Gym",
                    iconId = "gym",
                    type = HabitType.CHECK,
                    streak = 4,
                    doneToday = false
                ),
                Habit(
                    id = 3,
                    name = "Read 20 min",
                    iconId = "book",
                    type = HabitType.CHECK,
                    streak = 10,
                    doneToday = true
                )
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Habits", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(habits, key = { it.id }) { habit ->
                when (habit.type) {

                    HabitType.CHECK -> HabitCheckCard(
                        habit = habit,
                        onToggleDone = {
                            habits = habits.map {
                                if (it.id == habit.id) it.copy(doneToday = !it.doneToday) else it
                            }
                        }
                    )

                    HabitType.COUNTER -> HabitCounterCard(
                        habit = habit,
                        onMinus = {
                            habits = habits.map {
                                if (it.id == habit.id) {
                                    val newValue = (it.countToday - 1).coerceAtLeast(0)
                                    it.copy(countToday = newValue)
                                } else it
                            }
                        },
                        onPlus = {
                            habits = habits.map {
                                if (it.id == habit.id) {
                                    val newValue = (it.countToday + 1).coerceAtMost(it.targetPerDay)
                                    it.copy(countToday = newValue)
                                } else it
                            }
                        }
                    )
                }
            }
        }
    }
}
