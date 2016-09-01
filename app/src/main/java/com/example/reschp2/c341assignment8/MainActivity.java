package com.example.reschp2.c341assignment8;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    //labels for data to be passed to final score screen
    public static final String SCORE = "score";
    public static final String SCORE_DETAILS = "scoreDetails";
    public static final String DB_PATH = "database";
    //constants
    private static final String ANS_CORRECT = "Correct";
    private static final String ANS_INCORRECT = "Incorrect";
    //class variables
    private int score;
    private int[] scoreDetails;
    private boolean answered;
    private int qNum;
    private long numRows;
    private Cursor cursor;
    private TextView responseView;
    private RadioGroup radioGroup;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String path = getIntent().getStringExtra(CreateQuizActivity.DB_PATH);
        database = SQLiteDatabase.openDatabase(path, null, 0);
        numRows = DatabaseUtils.queryNumEntries(database, QuizDBSchema.QuestionTable.NAME);
        String selectQuery = "SELECT  * FROM " + QuizDBSchema.QuestionTable.NAME + " order by id";
        cursor = database.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        responseView = (TextView)findViewById(R.id.tv_result);
        radioGroup = (RadioGroup)findViewById(R.id.radgrp_choices);
        answered = false;
        score = 0;
        qNum = 1;
        scoreDetails = new int[5];
        showCurrentQuestionIndicator();
        showNextQuestion();
        showChoices();
        initSubmitButton();
        initNextButton();
    }


    //display current question number
    private void showCurrentQuestionIndicator()
    {
        TextView currQuestionIndicator = (TextView)findViewById(R.id.tv_questionNum);
        String currQuestion = String.valueOf(qNum);
        currQuestionIndicator.setText(currQuestion);
    }

    //display next question
    private void showNextQuestion()
    {
        TextView questionView = (TextView)findViewById(R.id.tv_currentQuestion);
        //get next question from database
        questionView.setText(cursor.getString(1));
    }

    //display choice labels
    private void showChoices()
    {
        //get choices from database
        RadioButton choiceA = (RadioButton)findViewById(R.id.rad_ChoiceA);
        choiceA.setText(cursor.getString(2));
        RadioButton choiceB = (RadioButton)findViewById(R.id.rad_ChoiceB);
        choiceB.setText(cursor.getString(3));
        RadioButton choiceC = (RadioButton)findViewById(R.id.rad_ChoiceC);
        choiceC.setText(cursor.getString(4));
        RadioButton choiceD = (RadioButton)findViewById(R.id.rad_ChoiceD);
        choiceD.setText(cursor.getString(5));
    }

    //next button logic
    private void initNextButton()
    {
        Button nextButton = (Button) findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(answered == true)
                {
                    if (qNum == numRows)
                    {
                        //got to end screen to get final score
                        //Toast.makeText(MainActivity.this, "The end", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, EndScreenActivity.class);
                        //pass name value pair
                        intent.putExtra(SCORE, score);
                        intent.putExtra(SCORE_DETAILS, scoreDetails);
                        intent.putExtra(DB_PATH, database.getPath());
                        startActivity(intent);
                    }
                    else
                    {
                        cursor.moveToNext();
                        qNum++;
                        showNextQuestion();
                        showCurrentQuestionIndicator();
                        showChoices();
                        radioGroup.clearCheck();
                        radioGroup.setEnabled(true);
                        answered = false;
                        responseView.setText("");
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Make a selection and press submit", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //submit button logic
    private void initSubmitButton()
    {
        Button submitButton = (Button)findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (answered == false)
                {
                    String answer = cursor.getString(6);
                    Toast.makeText(MainActivity.this, answer, Toast.LENGTH_LONG).show();
                    int choice = radioGroup.getCheckedRadioButtonId();
                    TextView responseView = (TextView) findViewById(R.id.tv_result);
                    switch (choice)
                    {
                        case R.id.rad_ChoiceA:
                            radioGroup.setEnabled(false);
                            answered = true;
                            if (answer.compareTo("A") == 0)
                            {
                                responseView.setText(ANS_CORRECT);
                                scoreDetails[qNum - 1] = 1;
                                score++;
                            }
                            else
                            {
                                responseView.setText(ANS_INCORRECT);
                                scoreDetails[qNum - 1] = 0;
                            }
                            break;
                        case R.id.rad_ChoiceB:
                            radioGroup.setEnabled(false);
                            answered = true;
                            if (answer.compareTo("B") == 0)
                            {
                                responseView.setText(ANS_CORRECT);
                                scoreDetails[qNum - 1] = 1;
                                score++;
                            }
                            else
                            {
                                responseView.setText(ANS_INCORRECT);
                                scoreDetails[qNum - 1] = 0;
                            }
                            break;
                        case R.id.rad_ChoiceC:
                            radioGroup.setEnabled(false);
                            answered = true;
                            if (answer.compareTo("C") == 0)
                            {
                                responseView.setText(ANS_CORRECT);
                                scoreDetails[qNum - 1] = 1;
                                score++;
                            }
                            else
                            {
                                responseView.setText(ANS_INCORRECT);
                                scoreDetails[qNum - 1] = 0;
                            }
                            break;
                        case R.id.rad_ChoiceD:
                            radioGroup.setEnabled(false);
                            answered = true;
                            if (answer.compareTo("D") == 0)
                            {
                                responseView.setText(ANS_CORRECT);
                                scoreDetails[qNum - 1] = 1;
                                score++;
                            }
                            else
                            {
                                responseView.setText(ANS_INCORRECT);
                                scoreDetails[qNum - 1] = 0;
                            }
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "You haven't made a selection", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "You have already submitted your answer for this question", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}