package com.sample.todoapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.todoapp.ui.theme.Orange40
import com.sample.todoapp.ui.theme.Orange80
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(viewModel : TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember{
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(start=4.dp,end = 4.dp,bottom=8.dp, top = 32.dp)
    ){
        Row(modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            OutlinedTextField(
                value = inputText,
                onValueChange = {inputText = it},
                placeholder = {Text(text = "Type Your To Do Here.")},
                singleLine = true,
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                viewModel.addTodo(inputText)
                inputText = ""
            },
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(Color.Yellow)
                    ){
                Icon(painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "Add",
                    tint = Color.Black,)
            }
        }

        todoList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it){
                            index : Int , item ->
                        TodoItem(item = item, onDelete = {
                            viewModel.deleteTodo(item.id)
                        })
                    }
                }
            )
        }
    }
}

@Composable
fun TodoItem(item: ToDoData, onDelete : () -> Unit ){
    var checked by remember {
        mutableStateOf(false)
    }

    val todoColor by animateColorAsState(
        targetValue = if (!checked) Orange40 else Color.Cyan
    )

    Row(modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(todoColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(6.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = SimpleDateFormat("dd/MM • EEEE" , Locale.ENGLISH).format(item.createdAt),
                    color= MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.title,
                    color= MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 19.sp)
            }
            Checkbox(checked = checked, onCheckedChange = {checked = it})
            IconButton (onClick = onDelete){
                Icon(
                    painter = painterResource(R.drawable.baseline_delete_24),
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
}