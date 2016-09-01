package com.example.reschp2.c341assignment8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by reschp2 on 11/12/2015.
 */
public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz.db";
    private static final int VERSION = 1;

    public QuizDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + QuizDBSchema.QuestionTable.NAME + "(" +
        "id integer primary key autoincrement, " +
        QuizDBSchema.QuestionTable.Cols.QUESTION + ", " +
        QuizDBSchema.QuestionTable.Cols.CHOICE_A + ", " +
        QuizDBSchema.QuestionTable.Cols.CHOICE_B + ", " +
        QuizDBSchema.QuestionTable.Cols.CHOICE_C + ", " +
        QuizDBSchema.QuestionTable.Cols.CHOICE_D + ", " +
        QuizDBSchema.QuestionTable.Cols.CORRECT_CHOICE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

    }

}
