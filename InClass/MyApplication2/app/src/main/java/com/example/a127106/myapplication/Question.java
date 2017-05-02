package com.example.a127106.myapplication;

/**
 * Created by 127106 on 9/28/2016.
 */
public class Question
{
   private int _mQuestionResourceId;
   private boolean _mAnswerCorrect;

   public Question(int questionResourceId, boolean answerCorrect)
   {
      _mQuestionResourceId = questionResourceId;
      _mAnswerCorrect = answerCorrect;
   }

   public boolean isAnswerCorrect()
   {
      return _mAnswerCorrect;
   }

   public void setAnswerCorrect(boolean answerCorrect) {
      _mAnswerCorrect = answerCorrect;
   }

   public int getQuestionResourceId() {
      return _mQuestionResourceId;
   }

   public void setQuestionResourceId(int questionResourceId) {
      _mQuestionResourceId = questionResourceId;
   }
}
