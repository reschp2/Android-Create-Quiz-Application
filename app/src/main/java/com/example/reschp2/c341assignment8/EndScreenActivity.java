package com.example.reschp2.c341assignment8;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class EndScreenActivity extends Activity {
    //data from previous screen
    //private int finalScore = getIntent().getIntExtra(MainActivity.SCORE, 0);
    //private int[] finalScoreDetail = getIntent().getIntArrayExtra(MainActivity.SCORE_DETAILS);
    //class variables
    //private QuestionSet questions = new QuestionSet();

    private Cursor cursor;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);
        populateLayout();
    }

    private void populateLayout()
    {
        String path = getIntent().getStringExtra(MainActivity.DB_PATH);
        database = SQLiteDatabase.openDatabase(path, null, 0);
        String selectQuery = "SELECT  * FROM " + QuizDBSchema.QuestionTable.NAME + " order by id";
        cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        //load results from quiz
        int finalScore = getIntent().getIntExtra(MainActivity.SCORE, 0);
        int[] finalScoreDetail = getIntent().getIntArrayExtra(MainActivity.SCORE_DETAILS);
        //initialize variables
        TextView currentTextView;
        ImageView currentImageView;
        int questionNum = 0;
        String correctAnswer;
        //populate final score textview
        currentTextView = (TextView)findViewById(R.id.tv_final_score);
        currentTextView.setText(" " + finalScore);
        //populate first result textview and imageview
        currentTextView = (TextView)findViewById(R.id.tv_resultScore1);
        currentImageView = (ImageView)findViewById(R.id.iv_result1);
        correctAnswer = cursor.getString(6);
        //set textview
        if(correctAnswer.compareTo("A") == 0)
        {
            currentTextView.setText(cursor.getString(2));
        }
        else if(correctAnswer.compareTo("B") == 0)
        {
            currentTextView.setText(cursor.getString(3));
        }
        else if(correctAnswer.compareTo("C") == 0)
        {
            currentTextView.setText(cursor.getString(4));
        }
        else if(correctAnswer.compareTo("D") == 0)
        {
            currentTextView.setText(cursor.getString(5));
        }
        //set image view
        if(finalScoreDetail[questionNum] == 1)
        {
            currentImageView.setImageResource(R.drawable.img_right);
        }
        else
        {
            currentImageView.setImageResource(R.drawable.img_wrong);
        }
        questionNum++;

        //populate second result textview and imageview
        cursor.moveToNext();
        currentTextView = (TextView)findViewById(R.id.tv_resultScore2);
        currentImageView = (ImageView)findViewById(R.id.iv_result2);
        correctAnswer = cursor.getString(6);
        //set textview
        if(correctAnswer.compareTo("A") == 0)
        {
            currentTextView.setText(cursor.getString(2));
        }
        else if(correctAnswer.compareTo("B") == 0)
        {
            currentTextView.setText(cursor.getString(3));
        }
        else if(correctAnswer.compareTo("C") == 0)
        {
            currentTextView.setText(cursor.getString(4));
        }
        else if(correctAnswer.compareTo("D") == 0)
        {
            currentTextView.setText(cursor.getString(5));
        }
        //set image view
        if(finalScoreDetail[questionNum] == 1)
        {
            currentImageView.setImageResource(R.drawable.img_right);
        }
        else
        {
            currentImageView.setImageResource(R.drawable.img_wrong);
        }
        questionNum++;

        //populate third result textview and imageview
        cursor.moveToNext();
        currentTextView = (TextView)findViewById(R.id.tv_resultScore3);
        currentImageView = (ImageView)findViewById(R.id.iv_result3);
        correctAnswer = cursor.getString(6);
        //set textview
        if(correctAnswer.compareTo("A") == 0)
        {
            currentTextView.setText(cursor.getString(2));
        }
        else if(correctAnswer.compareTo("B") == 0)
        {
            currentTextView.setText(cursor.getString(3));
        }
        else if(correctAnswer.compareTo("C") == 0)
        {
            currentTextView.setText(cursor.getString(4));
        }
        else if(correctAnswer.compareTo("D") == 0)
        {
            currentTextView.setText(cursor.getString(5));
        }
        //set image view
        if(finalScoreDetail[questionNum] == 1)
        {
            currentImageView.setImageResource(R.drawable.img_right);
        }
        else
        {
            currentImageView.setImageResource(R.drawable.img_wrong);
        }
        questionNum++;

        //populate fourth result textview and imageview
        cursor.moveToNext();
        currentTextView = (TextView)findViewById(R.id.tv_resultScore4);
        currentImageView = (ImageView)findViewById(R.id.iv_result4);
        correctAnswer = cursor.getString(6);
        //set textview
        if(correctAnswer.compareTo("A") == 0)
        {
            currentTextView.setText(cursor.getString(2));
        }
        else if(correctAnswer.compareTo("B") == 0)
        {
            currentTextView.setText(cursor.getString(3));
        }
        else if(correctAnswer.compareTo("C") == 0)
        {
            currentTextView.setText(cursor.getString(4));
        }
        else if(correctAnswer.compareTo("D") == 0)
        {
            currentTextView.setText(cursor.getString(5));
        }
        //set image view
        if(finalScoreDetail[questionNum] == 1)
        {
            currentImageView.setImageResource(R.drawable.img_right);
        }
        else
        {
            currentImageView.setImageResource(R.drawable.img_wrong);
        }
        questionNum++;

        //populate fifth result textview and imageview
        cursor.moveToNext();
        currentTextView = (TextView)findViewById(R.id.tv_resultScore5);
        currentImageView = (ImageView)findViewById(R.id.iv_result5);
        correctAnswer = cursor.getString(6);
        //set textview
        if(correctAnswer.compareTo("A") == 0)
        {
            currentTextView.setText(cursor.getString(2));
        }
        else if(correctAnswer.compareTo("B") == 0)
        {
            currentTextView.setText(cursor.getString(3));
        }
        else if(correctAnswer.compareTo("C") == 0)
        {
            currentTextView.setText(cursor.getString(4));
        }
        else if(correctAnswer.compareTo("D") == 0)
        {
            currentTextView.setText(cursor.getString(5));
        }
        if(finalScoreDetail[questionNum] == 1)
        {
            currentImageView.setImageResource(R.drawable.img_right);
        }
        else
        {
            currentImageView.setImageResource(R.drawable.img_wrong);
        }
    }
}