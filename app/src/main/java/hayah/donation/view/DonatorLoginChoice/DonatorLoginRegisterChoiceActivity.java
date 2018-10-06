package hayah.donation.view.DonatorLoginChoice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hayah.donation.R;
import hayah.donation.view.login.LoginActivity;
import hayah.donation.view.register.DonatorActivity;

public class DonatorLoginRegisterChoiceActivity extends AppCompatActivity implements View.OnClickListener{

    private Button signInBtn , signUpBtn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_login_register_choice);

        signInBtn = findViewById(R.id.button_sign_in);
        signUpBtn = findViewById(R.id.button_sign_up);

        signUpBtn.setOnClickListener(this);
        signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.button_sign_up :

                startDonatorRegisterActivity();
                break;

            case R.id.button_sign_in :
                startLoginActivityActivity();
                break;


        }


    }


    private void startDonatorRegisterActivity(){
        Intent intent = new Intent(DonatorLoginRegisterChoiceActivity.this , DonatorActivity.class);
        startActivity(intent);
    }


    private void startLoginActivityActivity(){
        Intent intent = new Intent(DonatorLoginRegisterChoiceActivity.this , LoginActivity.class);
        startActivity(intent);
    }
}
