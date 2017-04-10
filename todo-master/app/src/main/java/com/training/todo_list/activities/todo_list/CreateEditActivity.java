package com.training.todo_list.activities.todo_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.training.todo_list.R;
import com.training.todo_list.activities.todo_list.adapter.TodoListAdapter;
import com.training.todo_list.model.managers.TodoManager;
import com.training.todo_list.model.models.Todo;
import com.training.todo_list.model.models.TodoType;
import com.training.todo_list.utils.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

public class CreateEditActivity extends AppCompatActivity {

    private EditText mDescriptionEdittext;
    private Button mCreateEditButton;

    private TodoManager mTodoManger;
    public Todo mTodo = null;
    private TodoType mTodoType = null;
    private TodoListAdapter mAdapter;
    private List<Todo> mTodoList = new ArrayList<Todo>();
    private String mDescription = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        overridePendingTransition(R.anim.slide_in_bottom, R.anim.hold);

        setContentView(R.layout.create_edit_layout);
        mDescriptionEdittext = (EditText) findViewById(R.id.description_edittext);
        mCreateEditButton = (Button) findViewById(R.id.create_edit_button);

        Intent tIntent = getIntent();
        final int tType = tIntent.getIntExtra("type", 0);
        final int tPosition = tIntent.getIntExtra("position", 0);

        if(tType == 1){
            mTodo = tIntent.getExtras().getParcelable("toDo");
            if(mTodo != null)
                mDescriptionEdittext.setText(mTodo.getmSDescription());
        }

        mTodoManger = new TodoManager();

        mCreateEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StringUtils.isNullOrEmpty(mDescriptionEdittext.getText().toString())){
                    Toast.makeText(CreateEditActivity.this, "Description field is empty !", Toast.LENGTH_SHORT).show();
                }else{
                    if(tType == 0)
                        createTodo();
                    else
                        editTodo(tPosition);
                }

            }
        });
    }

    public void createTodo(){
        UUID id = UUID.randomUUID();
        if(!StringUtils.isNullOrEmpty(mDescriptionEdittext.getText().toString())){
            Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
            Date currentTime = localCalendar.getTime();
            mTodoType = new TodoType(mDescriptionEdittext.getText().toString(),
                    "#3a87ad", UUID.randomUUID());
            mTodo = new Todo(mDescriptionEdittext.getText().toString(),
                    currentTime, mTodoType.id(), true, id);
            mTodoManger.save(mTodo);
        }

        finish();

    }

    public void editTodo(int pPosition){
        mTodoList = mTodoManger.all();
        mTodo = mTodoList.get(pPosition);
        mTodo.setmSDescription(mDescriptionEdittext.getText().toString());
        mTodoList.set(pPosition, mTodo);

        finish();
    }


    public Todo getmTodo() {
        return mTodo;
    }

    public void setmTodo(Todo mTodo) {
        this.mTodo = mTodo;
    }

    public String getmDescription() {
        mDescription = mDescriptionEdittext.getText().toString();
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
