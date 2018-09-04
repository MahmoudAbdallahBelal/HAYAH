package hayah.hayah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hayah.hayah.view.register.DonatorActivity;
import hayah.hayah.view.search.PatientActivity;

public class MainActivity extends AppCompatActivity {

    private Button donatorBtn , patientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donatorBtn= findViewById(R.id.button_donator);
        donatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DonatorActivity.class));
            }
        });

        patientBtn= findViewById(R.id.button_patient);
        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PatientActivity.class));
            }
        });


    }
}
