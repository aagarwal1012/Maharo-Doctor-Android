package in.ashutoshchaubey.maharodoctor.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import in.ashutoshchaubey.maharodoctor.R;

import static in.ashutoshchaubey.maharodoctor.Constants.IS_LOGGED_IN;
import static in.ashutoshchaubey.maharodoctor.Constants.SHARED_PREFERENCES;
import static in.ashutoshchaubey.maharodoctor.Constants.SPLASH_DISPLAY_LENGTH;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;

    SharedPreferences sharedPreferences;

    Boolean isUserLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.circular_progress);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        isUserLoggedIn = sharedPreferences.getBoolean(IS_LOGGED_IN, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUserLoggedIn) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
