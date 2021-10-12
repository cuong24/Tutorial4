package com.example.tutorial4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {

    private final String baseURL = "https://my-json-server.typicode.com/cristalngo/demo/students";
    private String message;
    private int status;
    private EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        nameText = (EditText) findViewById(R.id.editTextTextPersonName);
    }

    public void addStudent(View view){
        new PostStudent().execute();
    }

    public void updateStudent(View view){
        new UpdateStudent().execute();
    }

    public void deleteStudent(View view){
        new DeleteStudent().execute();
    }

    private class PostStudent extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground (Void... params){
            message = HttpHandler.postStudent(nameText.getText().toString(), baseURL);
            return null;
        }
        
        @Override
        protected void onPostExecute(Void nothing){
            super.onPostExecute(nothing);
            Toast.makeText(AddStudent.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private class UpdateStudent extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground (Void... params){
            message = HttpHandler.updateStudent(1,nameText.getText().toString(), baseURL);
            return null;
        }

        @Override
        protected void onPostExecute(Void nothing){
            super.onPostExecute(nothing);
            Toast.makeText(AddStudent.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private class DeleteStudent extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground (Void... params){
            message = HttpHandler.deleteStudent(1,baseURL);
            return null;
        }

        @Override
        protected void onPostExecute(Void nothing){
            super.onPostExecute(nothing);
            Toast.makeText(AddStudent.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}