package com.example.reschp2.c341assignment8;

/**
 * Created by reschp2 on 11/12/2015.
 */
public class QuizDBSchema
{
    public static final class QuestionTable
    {
        public static final String NAME = "questions";

        public static final class Cols
        {
            public static final String QUESTION =  "question";
            public static final String CHOICE_A = "choice_a";
            public static final String CHOICE_B = "choice_b";
            public static final String CHOICE_C = "choice_c";
            public static final String CHOICE_D = "choice_d";
            public static final String CORRECT_CHOICE = "correct_choice";
        }
    }
}
