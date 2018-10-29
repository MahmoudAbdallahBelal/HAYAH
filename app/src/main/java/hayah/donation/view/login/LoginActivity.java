package hayah.donation.view.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.models.login.LoginResponse;
import hayah.donation.view.donator_profile.DonstorProfileActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText emailEdit, passwordEdit;
    private Button loginBtn;
    private ProgressBar progressBar;

    @Inject
     LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        loginPresenter.onAttach(this);

        MobileAds.initialize(this,
                Utilities.ADMOB_INTIALIZE);

        AdView mAdView = findViewById(R.id.adView_donator_bottom_login);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        loginBtn = findViewById(R.id.button_login);
        emailEdit = findViewById(R.id.edit_email_login);
        passwordEdit = findViewById(R.id.edit_password_login);
        progressBar = findViewById(R.id.progress_login);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loginPresenter.loginPresenter(emailEdit.getText().toString(), passwordEdit.getText().toString());
                }catch (Exception e)
                {
                    Toast.makeText(LoginActivity.this, getString(R.string.invalid_email_password), Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmailError() {

        emailEdit.setError(getString(R.string.email_error));
    }

    @Override
    public void showPasswordError() {
        passwordEdit.setError(getString(R.string.password_error));

    }

    @Override
    public void showInternetError(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {

       // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this , DonstorProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(String error) {

        Toast.makeText(this, getString(R.string.general_error), Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }
}
