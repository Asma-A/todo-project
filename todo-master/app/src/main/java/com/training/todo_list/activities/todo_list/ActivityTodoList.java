package com.training.todo_list.activities.todo_list;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.activities.todo_list.adapter.TodoListAdapter;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.models.Todo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ActivityTodoList extends ListActivity {

    private ListView mListview;
    private ImageButton mAddButton;

    private TodoManager mTodoManger;

    private List<Todo> mTodoList = new ArrayList<Todo>();
    private Set<Todo> mTodos = new HashSet<>();
    private TodoListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.lt_act_todo_list);
        mListview = (ListView) findViewById(android.R.id.list) ;
        mAddButton = (ImageButton) findViewById(R.id.bt_add);

        mTodoManger = new TodoManager();
        mTodoList = mTodoManger.all();
        mAdapter = new TodoListAdapter(this, mTodoList);
        mListview.setAdapter(mAdapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                askEditTodo(mTodoList.get(position), position);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        mTodoManger = new TodoManager();
        mTodoList = mTodoManger.all();
        mAdapter = new TodoListAdapter(this, mTodoList);
        mListview.setAdapter(mAdapter);
    }

    public void askAddTodo(View pView) throws ParseException {
        Intent createActivity = new Intent(this, CreateEditActivity.class);
        createActivity.putExtra("type", 0);
        startActivity(createActivity);
    }

    public void askEditTodo(Todo toDo, int position) {

        Intent createActivity = new Intent(this, CreateEditActivity.class);
        createActivity.putExtra("type", 1);
        createActivity.putExtra("toDo",toDo);
        createActivity.putExtra("position",position);
        startActivity(createActivity);
    }


    public void askSurprise(View pView) {
        AlertDialog.Builder tBuilder = new AlertDialog.Builder(this);
        tBuilder.setTitle(R.string.dialog_title_surprise);
        tBuilder.setMessage(R.string.dialog_message_surprise);
        tBuilder.setPositiveButton(R.string.confirm, null);
        tBuilder.show();
    }
}
