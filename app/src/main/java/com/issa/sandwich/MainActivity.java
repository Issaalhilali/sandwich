package com.issa.sandwich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this is show list sandwich name from string 4
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, sandwiches);

        ListView listView = findViewById(R.id.list_View);
        listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                launchDetailsAcivity(i);

            }
        });

    }
    private void launchDetailsAcivity (int position){
        Intent intent = new Intent(this,DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}
