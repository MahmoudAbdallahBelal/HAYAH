package hayah.donation.view.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import hayah.donation.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEdit, passwordEdit;
    private Button loginBtn;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.button_login);
        emailEdit = findViewById(R.id.edit_email_login);
        passwordEdit = findViewById(R.id.edit_password_login);
        progressBar = findViewById(R.id.progress_login);





    }
}
