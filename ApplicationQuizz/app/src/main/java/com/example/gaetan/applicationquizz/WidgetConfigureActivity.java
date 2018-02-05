package com.example.gaetan.applicationquizz;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.gaetan.applicationquizz.SQLite.DataBaseManager;
import com.example.gaetan.applicationquizz.models.Score;

import java.util.Collections;
import java.util.List;

/**
 * Created by gilian on 02/02/2018.
 */

public class WidgetConfigureActivity extends AppCompatActivity {
    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private DataBaseManager dbm;
    private ListView imgV;
    private List<Score> scores;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_app_widget);

        setResult(RESULT_CANCELED);
        this.dbm = new DataBaseManager(this);
        scores = dbm.selectAllScore();
        Collections.sort(scores);
        ArrayAdapter<Score> adapter = new ArrayAdapter<Score>(imgV.getContext(),android.R.layout.simple_list_item_1,scores);
        imgV.setAdapter(adapter);
        for( Score score  : scores ){
            Log.i("Fragment", " "+ score.getScore());
        }
        if(null!=getIntent()){
            appWidgetId = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        }
    }

    public void okClicked (View view){
        //EditText editText = (EditText) findViewById(R.id.editText);
        //MyWidget.updateOneWidget(this, AppWidgetManager.getInstance(this), appWidgetId, editText.getText().toString());

        Intent returnIntent = new Intent();
        returnIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
