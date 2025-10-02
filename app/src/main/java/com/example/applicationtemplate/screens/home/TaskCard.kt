package com.example.applicationtemplate.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applicationtemplate.domain.Task

@Composable
fun TaskCard(
    task: Task,
    onCompleteTaskClick: (Task) -> Unit,
) {

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {
                Checkbox(
                    checked = task.status == Task.Status.COMPLETE,
                    onCheckedChange = { onCompleteTaskClick(task) },
                )

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium
                )
            }


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}