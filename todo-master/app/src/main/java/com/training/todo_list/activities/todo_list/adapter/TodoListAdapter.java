package com.training.todo_list.activities.todo_list.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.training.todo_list.R;
import com.training.todo_list.model.models.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asma.achour on 10/04/2017.
 */

public class TodoListAdapter extends BaseAdapter {

    private class ViewHolder {
        public TextView title;
        public TextView date;
        public TextView description;
    }

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Todo> mTodoList;


    public TodoListAdapter(Context context, List<Todo> todoList) {
        mContext = context;
        mTodoList = todoList;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mTodoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTodoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        Todo mTodo = mTodoList.get(position);

        final ViewHolder viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.todo_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if(mTodo != null){
            viewHolder.title.setText(mTodo.getmSDescription());
        }
        return view;
    }

    public List<Todo> getmTodoList() {
        return mTodoList;
    }

    public void setmTodoList(List<Todo> mTodoList) {
        this.mTodoList = mTodoList;
    }
}