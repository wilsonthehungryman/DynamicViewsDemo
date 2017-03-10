package com.wilson.dynamicviewsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout root;
    private LinkedList<String> items;
    private LinkedList<LinearLayout> subLayouts;
    private EditText input;
    private String item;
    private int colour;
    private LinearLayout.LayoutParams params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        root = (LinearLayout)findViewById(R.id.root);
        items = new LinkedList<>();
        subLayouts = new LinkedList<>();

        colour = Color.BLACK;

        // TODO Params
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        items.add("The last item");
        items.addLast("Not the last");
        loadViews();
    }

    private void loadViews(){
        // TODO todo
        // TODO Adding views
        root.removeAllViews();

        LinearLayout layout = new LinearLayout(this);

        input = new EditText(this);
        Button go = new Button(this);
        go.setText("Go");
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        layout.addView(input);
        layout.addView(go);
        root.addView(layout);

        layout = new LinearLayout(this);
        root.addView(layout, 0);

        // Create buttons
        Button black = new Button(this), blue = new Button(this), red = new Button(this), green = new Button(this);

        // Set their IDs and text
        black.setId(R.id.black);
        black.setText("Black");
        blue.setId(R.id.blue);
        blue.setText("Blue");
        red.setId(R.id.red);
        red.setText("Red");

        // Add buttons to layout
        layout.addView(black);
        layout.addView(blue);
        layout.addView(red);

        // Set onClick listeners
        for(int i = 0; i < layout.getChildCount(); i++){
            layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeColour(v);
                }
            });
        }

        // Add row for each item
        for(int i = 0; i < items.size(); i++){
            item = items.get(i);
            addRow();
        }
    }

    private void addRow(){
        // TODO Inserting views
        // Create views
        LinearLayout layout = new LinearLayout(this);
        Button button       = new Button(this);
        TextView textView   = new TextView(this);

        // Set orientation
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setHorizontalGravity(Gravity.CENTER);

        button.setText("remove");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(items.indexOf(item));
            }
        });
        textView.setText(item);
        textView.setTextColor(colour);

        layout.addView(button);
        layout.addView(textView);
        subLayouts.add(1, layout);
        root.addView(layout, 1, params);
    }

    private void remove(int position){
        // TODO Removing a view
        root.removeView(subLayouts.remove(position));
        items.remove(position);
    }

    private void add(){
        item = input.getText().toString();
        items.addFirst(item);
        input.setText("");
        addRow();
    }

    private void changeViewColour(ViewGroup group){
        // TODO iterating through view groups
        for(int i = 0; i < group.getChildCount(); i++){
            View v = group.getChildAt(i);
            if(v instanceof Button){

            }else if(v instanceof TextView){
                ((TextView) v).setTextColor(colour);
            }else if(v instanceof LinearLayout){
                changeViewColour((ViewGroup)v);
            }
        }
    }

    private void changeColour(View v){
        // Change colour accordingly
        switch (v.getId()){
            case R.id.black:
                colour = Color.BLACK;
                break;
            case R.id.blue:
                colour = Color.BLUE;
                break;
            case R.id.red:
                colour = Color.RED;
                break;
            default:
                colour = Color.GREEN;
                break;
        }

        changeViewColour(root);
        // or
        // changeViewColour(subLayouts)
    }


}
