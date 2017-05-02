package com.example.a127106.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

   private Button _mTrueButton;
   private Button _mFalseButton;
   private Button _mNextButton;

   private TextView _mQuestionText;
   private QuestionBank _mMyQuestions;

   private int _mLastPressed;

   @Override
   public void onSaveInstanceState(Bundle savedInstanceState){
      super.onSaveInstanceState(savedInstanceState);
      savedInstanceState.putInt("myStartQuestion", _mMyQuestions.getQuestionNumber());
      savedInstanceState.putInt("myLastPress", _mLastPressed);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      int currentQuestion;

      if (savedInstanceState != null) {
         currentQuestion = savedInstanceState.getInt("myStartQuestion", 0);
         Toast.makeText(MainActivity.this,
                        savedInstanceState.getInt("myLastPress"),
                        Toast.LENGTH_SHORT).show();
      }

      else
         currentQuestion = 0;

      _mLastPressed = R.string.noButton;


      // Initialize the questionBank
      _mMyQuestions = new QuestionBank(currentQuestion);

      // Finds the questionText id in the activity.xml
      _mQuestionText = (TextView) findViewById(R.id.questionText);
      _mQuestionText.setText(_mMyQuestions.getCurrentQuestion().getQuestionResourceId());

      _mTrueButton  = (Button) findViewById(R.id.button1);
      _mFalseButton = (Button) findViewById(R.id.button2);
      _mNextButton  = (Button) findViewById(R.id.button3);

      _mFalseButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            checkAnswer(false);
            _mLastPressed = R.string.false_button;
         }
      });

      _mTrueButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            checkAnswer(true);
            _mLastPressed = R.string.true_button;
         }
      });

      _mNextButton.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v){
            Log.d("Testing", "Clicked next");
            _mMyQuestions.goToNext();
            _mQuestionText.setText((_mMyQuestions.getCurrentQuestion().getQuestionResourceId()));
            _mLastPressed = R.string.next_button;
         }
      });

   }

  public void checkAnswer(boolean answer){
      if (_mMyQuestions.getCurrentQuestion().isAnswerCorrect() == answer)
         Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
      else
         Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
   }


}
