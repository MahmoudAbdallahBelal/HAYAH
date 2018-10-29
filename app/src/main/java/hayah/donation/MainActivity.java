package hayah.donation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import hayah.donation.helper.Utilities;
import hayah.donation.view.DonatorLoginChoice.DonatorLoginRegisterChoiceActivity;
import hayah.donation.view.search.PatientActivity;


public class MainActivity extends AppCompatActivity {

    private Button donatorBtn , patientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donatorBtn= findViewById(R.id.button_donator);


        MobileAds.initialize(this,
                Utilities.ADMOB_INTIALIZE);

        AdView mAdViewTop = findViewById(R.id.adView_main_activity_top);
        AdView mAdViewBottom = findViewById(R.id.adView_main_activity_bottom);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdViewTop.loadAd(adRequest);
        mAdViewBottom.loadAd(adRequest);


        donatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DonatorLoginRegisterChoiceActivity.class));
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
