package hayah.hayah;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



         myIntent = new Intent(this, MainActivity.class);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(myIntent);
                finish();
            }
        }, 3000);


    }
}