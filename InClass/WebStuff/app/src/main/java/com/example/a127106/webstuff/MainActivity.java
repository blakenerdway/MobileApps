package com.example.a127106.webstuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

   WebView x;
   String printValue;

   // Use http://mydevice.io/devices/ to make a page look a certain way for certain devices
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      String jsonString = "";
      try {
         InputStream stuff = getAssets().open("t.json");
         byte[] buffer = new byte[stuff.available()];
         stuff.read(buffer);
         stuff.close();
         jsonString = new String(buffer, "UTF-8");
      } catch (IOException e) {

      }
      printValue = "";
      JSONObject json;
      JSONArray jsonArray;
      try {
         json = new JSONObject(jsonString);
         jsonArray = json.getJSONObject("query").getJSONObject("results").getJSONObject("channel").getJSONObject("item").getJSONArray("forecast");
         jsonArray.getJSONObject(1).getString("text");
         for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject j = new JSONObject(jsonArray.getJSONObject(i).getString(""));
         }
      } catch (JSONException e) {
         printValue = "error";
      }

      x = (WebView) findViewById(R.id.myWebView);
      x.getSettings().setJavaScriptEnabled(true);
      x.addJavascriptInterface(new Object() {
         @android.webkit.JavascriptInterface
         public void displayMessage() {
            Toast.makeText(MainActivity.this, printValue, Toast.LENGTH_SHORT).show();
         }
      }, "myApp");
      x.loadUrl("http://www.google.com");
   }
}
