package com.example.reschp2.c341assignment8;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CreateQuizActivity extends Activity {

    //labels for data to be passed to quiz preview
    public static final String DB_PATH = "database";
    //constants
    private static final String INCOMPLETE_FORM = "Please fill all the fields to continue";
    //class variables
    private int questionCount;
    private TextView currentQuestionNumber;
    private RadioGroup radioGroup;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Context context = this;
        context.deleteDatabase("quiz.db");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        currentQuestionNumber = (TextView)findViewById(R.id.tv_currentQuestionNumber);
        radioGroup = (RadioGroup)findViewById(R.id.rg_choice);
        questionCount = 1;
        database = new QuizDBHelper(this).getWritableDatabase();
        showCurrentQuestionNumber();
        initNextButton();
    }

    //display current question number
    private void showCurrentQuestionNumber()
    {
        currentQuestionNumber.setText(String.valueOf(questionCount));
    }

    //next button logic
    private void initNextButton()
    {
        Button nextButton = (Button) findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if ((((EditText) findViewById(R.id.et_question)).getText().toString() != "") &&
                        (((EditText) findViewById(R.id.et_choiceA)).getText().toString() != "") &&
                        (((EditText) findViewById(R.id.et_question)).getText().toString() != "") &&
                        (((EditText) findViewById(R.id.et_question)).getText().toString() != "") &&
                        (radioGroup.getCheckedRadioButtonId() != -1))
                {
                    ContentValues questionData = new ContentValues();
                    EditText currentData = (EditText) findViewById(R.id.et_question);
                    questionData.put(QuizDBSchema.QuestionTable.Cols.QUESTION, currentData.getText().toString());
                    currentData.setText("");
                    currentData = (EditText) findViewById(R.id.et_choiceA);
                    questionData.put(QuizDBSchema.QuestionTable.Cols.CHOICE_A, currentData.getText().toString());
                    currentData.setText("");
                    currentData = (EditText) findViewById(R.id.et_choiceB);
                    questionData.put(QuizDBSchema.QuestionTable.Cols.CHOICE_B, currentData.getText().toString());
                    currentData.setText("");
                    currentData = (EditText) findViewById(R.id.et_choiceC);
                    questionData.put(QuizDBSchema.QuestionTable.Cols.CHOICE_C, currentData.getText().toString());
                    currentData.setText("");
                    currentData = (EditText) findViewById(R.id.et_choiceD);
                    questionData.put(QuizDBSchema.QuestionTable.Cols.CHOICE_D, currentData.getText().toString());
                    currentData.setText("");
                    int currCorrectChoice = radioGroup.getCheckedRadioButtonId();
                    switch (currCorrectChoice)
                    {
                        case R.id.rad_ChoiceA:
                            radioGroup.setEnabled(false);
                            questionData.put(QuizDBSchema.QuestionTable.Cols.CORRECT_CHOICE, "A");
                            break;
                        case R.id.rad_ChoiceB:
                            radioGroup.setEnabled(false);
                            questionData.put(QuizDBSchema.QuestionTable.Cols.CORRECT_CHOICE, "B");
                            break;
                        case R.id.rad_ChoiceC:
                            radioGroup.setEnabled(false);
                            questionData.put(QuizDBSchema.QuestionTable.Cols.CORRECT_CHOICE, "C");
                            break;
                        case R.id.rad_ChoiceD:
                            radioGroup.setEnabled(false);
                            questionData.put(QuizDBSchema.QuestionTable.Cols.CORRECT_CHOICE, "D");
                            break;
                        default:
                            break;
                    }
                    radioGroup.clearCheck();
                    database.insert(QuizDBSchema.QuestionTable.NAME, null, questionData);
                    questionCount++;
                    if(questionCount == 6)
                    {
                        Intent intent = new Intent(CreateQuizActivity.this, MainActivity.class);
                        //pass name value pair
                        intent.putExtra(DB_PATH, database.getPath());
                        startActivity(intent);
                    }
                    showCurrentQuestionNumber();
                } else
                {
                    Toast.makeText(CreateQuizActivity.this, INCOMPLETE_FORM, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
