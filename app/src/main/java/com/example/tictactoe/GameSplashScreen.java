package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class GameSplashScreen extends AppCompatActivity {

    //Duration for splash screen
    private static int SPLASH_SCREEN_TIME_OUT=3000;

    Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_splash_screen);

        start_button = findViewById(R.id.start_button);

        //Set Duration for splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start_button.setVisibility(View.VISIBLE);

                start_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(GameSplashScreen.this, MainActivity.class);
                        startActivity(i);
                    }
                });
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}