package com.example.a127106.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class anotherActivity extends AppCompatActivity {

   private int _mSecondNumber;
   private TextView _mNumberText;
   private Button _mButtonThing;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_another);

      _mButtonThing = (Button) findViewById(R.id.button3);
      _mSecondNumber = getIntent().getIntExtra("ValueSentByMain", -1);

      _mNumberText = (TextView) findViewById(R.id.myOtherText);

      _mNumberText.setText("Second Number is" + _mSecondNumber);


      _mButtonThing.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            _mSecondNumber++;
            _mNumberText.setText("Second Number is" + _mSecondNumber);

            // Have a method for this
            // Do not set activity that will be loaded
            Intent data = new Intent();
            data.putExtra("MyResult", _mSecondNumber);
            setResult(RESULT_OK, data);
         }
      });
   }
}
