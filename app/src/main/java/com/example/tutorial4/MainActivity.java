package com.example.tutorial4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String json_string = "";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        new GetStudent().execute();

        Button addForm = (Button) findViewById(R.id.button);
        addForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudent.class);
                startActivity(intent);
            }
        });
    }


    private class GetStudent extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void ...voids){
            json_string = HttpHandler.getStudents(
                    "https://my-json-server.typicode.com/cristalngo/demo/students"
            );
            return null;
        }

        @Override
        protected void onPostExecute(Void nothing){
            super.onPostExecute(nothing);
            List<String> items = new ArrayList<>();
            JSONObject root = null;

            try {
                JSONArray array = new JSONArray(json_string);
                for (int i = 0; i < array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    items.add(object.getString("name"));
                }
                String[] values = items.toArray(new String[0]);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, values);

                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

