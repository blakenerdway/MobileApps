package com.example.a127106.myapplication;

/**
 * Created by 127106 on 9/28/2016.
 */
public class QuestionBank
{
   private Question[] _mQuestions;

   private int _mCurrentQuestion;

   public QuestionBank()
   {
      _mQuestions = new Question[]
      {
         new Question(R.string.myQuestion1, true),
         new Question(R.string.myQuestion2, true),
         new Question(R.string.myQuestion3, false)
      };
      _mCurrentQuestion = 0;
   }

   public QuestionBank(int startQuestion)
   {
      _mQuestions = new Question[]
      {
             new Question(R.string.myQuestion1, true),
             new Question(R.string.myQuestion2, true),
             new Question(R.string.myQuestion3, false)
      };
      _mCurrentQuestion = startQuestion;
   }


   public Question getCurrentQuestion()
   {
      return _mQuestions[_mCurrentQuestion];
   }

   public void goToNext()
   {
      _mCurrentQuestion = (_mCurrentQuestion + 1) % _mQuestions.length;
   }

   public int getQuestionNumber(){
      return _mCurrentQuestion;
   }
}
