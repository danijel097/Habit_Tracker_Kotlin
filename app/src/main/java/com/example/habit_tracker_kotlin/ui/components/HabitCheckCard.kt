package com.example.habit_tracker_kotlin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habit_tracker_kotlin.model.Habit

@Composable
fun HabitCheckCard(
    habit: Habit,
    onToggleDone: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HabitIcon(habit.iconId)
                Spacer(Modifier.width(12.dp))

                Column {
                    Text(habit.name, style = MaterialTheme.typography.titleMedium)
                    Text("Streak: ${habit.streak} days", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Checkbox(
                checked = habit.doneToday,
                onCheckedChange = { onToggleDone() }
            )
        }
    }
}

