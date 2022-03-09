package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonClear: Button

    private lateinit var listView: ListView
    private lateinit var todoAdapter: ArrayAdapter<String>

    private lateinit var todoList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        buttonAdd = findViewById(R.id.button_add)
        buttonDelete = findViewById(R.id.button_delete)
        buttonClear = findViewById(R.id.button_clear)

        listView = findViewById(R.id.todo_listview)
        todoList = mutableListOf()

        // set up recyclerview
        todoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, todoList)
        listView.adapter = todoAdapter
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE;


        buttonAdd.setOnClickListener {
            // get editText
            val todo = editText.text.toString()

            // add to todoList
            todoList.add(todo)

            // submit list to adapter
            todoAdapter.notifyDataSetChanged()

            // reset editText
            editText.text = null
        }

        buttonClear.setOnClickListener {
            // remove all items in todoList
            todoList.clear()

            // submit list to adapter
            todoAdapter.notifyDataSetChanged()
        }

        buttonDelete.setOnClickListener {
            val position = listView.checkedItemPositions
            val count = listView.count

            // count using downTo to avoid NPE
            for (index in count-1 downTo 0) {
                if (position[index]) todoList.removeAt(index)
            }

            position.clear()
            todoAdapter.notifyDataSetChanged()
        }
    }
}