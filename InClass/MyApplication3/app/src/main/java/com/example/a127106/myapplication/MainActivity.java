package com.example.a127106.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   private TextView _mMyText;
   private Button _mChangeBtn;
   private Button _mNextBtn;
   private ManyNumbers _mBank;
   private int hello = 0;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      _mNextBtn = (Button) findViewById(R.id.button);
      _mChangeBtn = (Button) findViewById(R.id.button2);
      _mMyText = (TextView) findViewById(R.id.myText);
      _mBank = new ManyNumbers();

      _mMyText.setText(_mBank.getCurrentNumber().displayNumber());

      _mNextBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            _mBank.moveToNext();
            _mMyText.setText(_mBank.getCurrentNumber().displayNumber());
         }
      });

      _mChangeBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent anotherActivity = new Intent(MainActivity.this, anotherActivity.class);
            anotherActivity.putExtra("ValueSentByMain", _mBank.getCurrentNumber().getSecondaryNumber());
            startActivityForResult(anotherActivity, 0);
         }
      });
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent returnedIntent){
      if(requestCode != RESULT_OK)
         return;
      else{
         _mBank.getCurrentNumber().setSecondaryNumber(returnedIntent.getIntExtra("MyResult", -99));
         _mMyText.setText(_mBank.getCurrentNumber().displayNumber());
      }
   }
}
