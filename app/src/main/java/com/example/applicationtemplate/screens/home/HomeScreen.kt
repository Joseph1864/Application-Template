package com.example.applicationtemplate.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel(),
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val tasks = viewState.tasks

    if (viewState.isCreateDialogVisible) {
        CreateTaskDialog(
            title = viewState.newTaskTitle,
            description = viewState.newTaskDescription,
            onTitleChanged = viewModel::onTitleChanged,
            onDescriptionChanged = viewModel::onDescriptionChange,
            onDismiss = viewModel::onDismissCreateDialog,
            onSubmit = viewModel::onSubmitCreateDialog
        )
    }

    Column {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            title = { Text(text = "ToDo") },
            actions = {
                IconButton(
                    onClick = { viewModel.onShowCreateDialog() },

                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(tasks.size) { index ->
                val task = tasks[index]

                TaskCard(
                    task = task,
                    onCompleteTaskClick = { viewModel.onCompleteTaskClick(it) },
                    onEditTaskClick =  { viewModel.onEditTaskClick(it) },
                    onDeleteTaskClick = { viewModel.onDeleteTaskClick(it) },
                )
            }
        }
    }

}