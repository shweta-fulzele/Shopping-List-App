package com.base.shoppinglist.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.base.shoppinglist.userinterfaceutils.CustomBasicTextField
import com.base.shoppinglist.userinterfaceutils.TitleBold
import com.base.shoppinglist.userinterfaceutils.TitleMedium
import com.base.shoppinglist.userinterfaceutils.TitleSmall

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Double,
    var isEditing: Boolean = false
)

@Composable
fun ShoppingListApp() {
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TitleMedium(
                text = "My Shopping List",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {


                LazyColumn(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    items(sItems) { item ->
                        if (item.isEditing) {
                            ShoppingItemEditor(item, onEditComplete = { editedName, editedQty ->

                                sItems = sItems.map { it.copy(isEditing = false) }
                                val editedItem = sItems.find { it.id == item.id }
                                editedItem?.let {
                                    it.name = editedName
                                    it.quantity = editedQty
                                }
                            })
                        }
                        ShoppingListItem(item, onEditClick = {
                            sItems = sItems.map { it.copy(isEditing = it.id == item.id) }
                        }, onDeleteClick = {
                            sItems = sItems - item
                        })
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.wrapContentSize()

                    ) {
                        Text("+ Add Item", modifier = Modifier.padding(6.dp))
                    }
                }

            }

            if (showDialog) {
                AlertDialog(onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(onClick = {
                                if (itemName.isNotBlank()) {
                                    val newItem = ShoppingItem(
                                        id = sItems.size + 1,
                                        name = itemName,
                                        quantity = itemQuantity.toDouble()
                                    )
                                    sItems = sItems + newItem
                                    showDialog = false
                                    itemName = ""
                                    itemQuantity = ""
                                }
                            }) {
                                TitleSmall(text = "Add", color = Color.White)
                            }
                            Button(onClick = { showDialog = false }) {
                                TitleSmall(text = "Cancel", color = Color.White)
                            }
                        }

                    },
                    title = {
                        TitleMedium(text = "Add Item")
                    },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = itemName,
                                onValueChange = { itemName = it },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )

                            OutlinedTextField(
                                value = itemQuantity,
                                onValueChange = { itemQuantity = it },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    }
                )

            }
        }

    }


}


@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Double) -> Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }


        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically) {
                TitleSmall(text = "Item Name: ")
                BasicTextField(
                    value = editedName,
                    onValueChange = { newText -> editedName = newText },
                    singleLine = true,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically) {
                TitleSmall(text = "Item Quantity: ")
                BasicTextField(
                    value = editedQuantity,
                    onValueChange = { newText -> editedQuantity = newText },
                    singleLine = true,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(8.dp)
                )
            }


        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                isEditing = false
                onEditComplete(editedName, editedQuantity.toDoubleOrNull() ?: 1.0)
            }
        ) {
            TitleSmall(text = "Save", Color.White)
        }
    }


}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(20)
            ), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
        Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))

        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}











