package com.example.gaetan.applicationquizz.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gaetan.applicationquizz.models.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gilian on 27/01/2018.
 */

public class DataBaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "question";

    public DataBaseManager( Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "Create table "+ TABLE +" ( id integer primary key autoincrement, title text, responseOne text, responseTwo text, responseThree text, responseFour text, theme text, goodResponse int)";
        db.execSQL(query);
        Log.i("DATABASE","Base de donnée crée");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table " + TABLE;
        db.execSQL(query);
        Log.i("DATABASE", "table mis a jour");

    }
    public void purgeTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "delete from " + TABLE;
        db.execSQL(query);
        Log.i("DATABASE", "table purgée");
    }

    public void insertData(String title, String responseOne,String responseTwo, String responseThree, String responseFour, String theme, int goodResponse){
        String query = "insert into "+TABLE+"(title,responseOne,responseTwo,responseThree,responseFour,theme,goodResponse) VALUES ('"+title+"','"+responseOne+"','"+responseTwo+"','"+responseThree+"','"+responseFour+"','"+theme+"',"+goodResponse+")";
        this.getWritableDatabase().execSQL(query);
        Log.i("DATABASE", "insert data");
    }

    public List<Question> selectAll(){
        List<Question> questions = new ArrayList<>();
        String query = "Select * from " + TABLE;
        Cursor cursor = this.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        Log.i("DATABASE", "Select all");
        while(!cursor.isAfterLast()){
            Question question = new Question(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5),cursor.getString(6),cursor.getInt(7));
            questions.add(question);
            cursor.moveToNext();

        }
        return questions;
    }
    public void insertDatasets(){
        //this.onReset(db);
        this.insertData("test","test","test","test","test","test",1);
        this.insertData("test2","test2","test2","test2","test2","test2",1);
        this.insertData("test3","test3","test3","test3","test3","test3",1);
        this.insertData("test4","test4","test4","test4","test4","test4",1);


    }



}
