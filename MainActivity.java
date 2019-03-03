package com.example.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText ItemET;
    private Button btn;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    Button bt;
    Button b_save;
    Button b_load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bt=(Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      Intent myIntent=new Intent(Intent.ACTION_SEND);
                                      myIntent.setType("text/plain");
                                      String sharebody="Your body here";
                                      String shareSub="Your subject here";
                                      myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                                      myIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
                                      startActivity(Intent.createChooser(myIntent,"Share using"));
                                  }
                              });


        ItemET = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.items_list);

        items=FileHelper.readData(this);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        itemsList.setAdapter(adapter);
        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);




    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add_btn:
                String itemEntered=ItemET.getText().toString();
                adapter.add(itemEntered);
                ItemET.setText("");
                FileHelper.writeData(items,this);
                Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();

                break;
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        items.remove(i);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(items,this);
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();

    }






}
