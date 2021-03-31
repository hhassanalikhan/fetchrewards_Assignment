package com.example.fetchrewards_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//This method is used so that your splash activity

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color.holo_orange_light));
        }
        setContentView(R.layout.activity_splash_screen);

        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
        httpCall(url);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);

       // RequestQueue queue = Volley.newRequestQueue(this);

    }

    public void httpCall(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                        SharedPreferences sp = getSharedPreferences("FILE_NAME", MODE_PRIVATE);


                        int prevHash = sp.getInt("Hash",-1);
                        int thisHash = response.hashCode();
                        if (prevHash != thisHash){
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putInt("Hash",thisHash);
                            edit.apply();

                            writeFile(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);
    }
    private void  writeFile(String response)
    {

        File file = new File(SplashScreen.this.getFilesDir(), "text");
        if (!file.exists()) {
            file.mkdir();
        }
        try
        {
            String d1 = "Hassan is here3";
            File gpxfile = new File(file, "webResponse2");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(response);
            writer.flush();
            writer.close();
        }
        catch (Exception e) { }
    }
}
