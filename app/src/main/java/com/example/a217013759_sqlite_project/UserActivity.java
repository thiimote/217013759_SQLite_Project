package com.example.a217013759_sqlite_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {
    Button SELECT;
    SQLiteDatabase db;
    SQLiteOpenHelper mydb;
    private EditText search;
    private ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        SELECT = (Button)findViewById(R.id.select);
        search  = (EditText)findViewById(R.id.search);
        searchButton = (ImageButton)findViewById(R.id.searchImage);

        mydb = new DatabaseHelper(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchUser = search.getText().toString();

                if (TextUtils.isEmpty(searchUser)) {
                    Toast.makeText(UserActivity.this, "Enter Username to Search all Detail", Toast.LENGTH_SHORT).show();
                }
              //  db = mydb.getReadableDatabase();
               // db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_2 , new  String[] {searchUser});


            }
        });

        SELECT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = mydb.getReadableDatabase();
                Cursor c = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME ,null);
                if (c.getCount() == 0){
                    showmessage("error" ,"nothing found");
                }
                StringBuffer buffer =  new StringBuffer();
                while (c.moveToNext()){
                    buffer.append(" Id :" + c.getString(0)+ "\n");
                    buffer.append("username : " + c.getString(1) + "\n");
                    buffer.append("Password : " + c.getString(2) + "\n\n");

                }
                showmessage("Users Table",buffer.toString());
            }
        });
    }
    public void showmessage( String title , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
