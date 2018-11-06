package hayah.donation.view.donator_profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hayah.donation.R;
import hayah.donation.helper.Utilities;

public class UpdateSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_success);


        Utilities.clearUserInfo(UpdateSuccessActivity.this);

    }
}
