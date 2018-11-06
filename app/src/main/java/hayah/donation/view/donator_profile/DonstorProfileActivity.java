package hayah.donation.view.donator_profile;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.models.login.LoginResponse;
import hayah.donation.view.Country.countryList.city.CityListActivity;
import hayah.donation.view.Country.countryList.country.CountryListActivity;
import hayah.donation.view.Country.countryList.state.StateActivity;
import hayah.donation.view.login.LoginActivity;
import hayah.donation.view.register.DonatorActivity;

public class DonstorProfileActivity extends AppCompatActivity implements ProfileView {


    private EditText nameEdit, ageEdit,mobileEdit  , bloodType , emailEdit ,addressEdit ;
    private Button updateBtn;
    private ProgressBar progressBarProfile;

    private LinearLayout linearLayoutAddress2 , linearLayoutAddress1 , linearLayoutMobile2;
    private ImageButton addAddress , removeAddress , addMobile , removeMobile;

    private LinearLayout countrySpinner, stateSpinner , citySpinner;
    private TextView textCountryName , textStateName , textCityName ;


    private Switch switchAvailability;
    String available;

    @Inject
    ProfilePresenter profilePresenter;

    String bloodTypeArray[] = {"A+" ,"A-" ,"B+" , "B-" , "O+" , "O-","AB+","AB-" , "لا أعلم"};

    String countryId , stateId , cityId;
    String token , userId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donstor_profile);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        profilePresenter.onAttach(this);

        updateBtn = findViewById(R.id.button_profile_update);
        nameEdit = findViewById(R.id.edit_profile_name);
        ageEdit = findViewById(R.id.edit_profile_age);
        mobileEdit = findViewById(R.id.edit_profile_mobile);
        bloodType = findViewById(R.id.edit_profile_blood_type);
        countrySpinner = findViewById(R.id.spinner_profile_country);
        stateSpinner = findViewById(R.id.spinner_profile_state);
        citySpinner = findViewById(R.id.spinner_profile_city);
        textCountryName = findViewById(R.id.text_profile_country_name);
        textStateName = findViewById(R.id.text_profile_state_name);
        textCityName = findViewById(R.id.text_profile_city_name);
        emailEdit = findViewById(R.id.edit_profile_email);
        progressBarProfile = findViewById(R.id.progress_profile_update);

        switchAvailability = findViewById(R.id.switch_profile_available);
        addressEdit = findViewById(R.id.edit_address_profile);



        getProfileInfo();


        MobileAds.initialize(this,
                Utilities.ADMOB_INTIALIZE);

        AdView mAdView = findViewById(R.id.adView_donator_profile_top);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        switchAvailability.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(isChecked == true)
                {
                    available = "1";
                }
                else
                {
                    available = "0";
                }
            }
        });

        countrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonstorProfileActivity.this ,CountryListActivity.class);
                startActivityForResult(intent , 1);

            }
        });

        stateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countryId!= null) {
                    Intent intent = new Intent(DonstorProfileActivity.this, StateActivity.class);
                    intent.putExtra("country_id", countryId);
                    startActivityForResult(intent, 2);
                }
                else
                {
                    Toast.makeText(DonstorProfileActivity.this, getString(R.string.select_country), Toast.LENGTH_LONG).show();
                }
            }
        });


        citySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateId!= null) {
                    Intent intent = new Intent(DonstorProfileActivity.this, CityListActivity.class);
                    intent.putExtra("state_id", stateId);
                    startActivityForResult(intent, 3);
                }
                else
                {
                    Toast.makeText(DonstorProfileActivity.this, getString(R.string.city), Toast.LENGTH_LONG).show();
                }
            }
        });




        bloodType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    final Dialog dialog = new Dialog(DonstorProfileActivity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                    dialog.setContentView(R.layout.dialog_blood_type);
                    ListView listViewBloodTypes = dialog.findViewById(R.id.listView_blood_types);

                    ArrayAdapter adapter = new ArrayAdapter(DonstorProfileActivity.this,android.R.layout.simple_spinner_dropdown_item , bloodTypeArray);
                    listViewBloodTypes.setAdapter(adapter);

                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                    dialog.getWindow().setLayout(400, ViewGroup.LayoutParams.WRAP_CONTENT);

                    dialog.setCancelable(true);
                    dialog.setCanceledOnTouchOutside(true);

                    dialog.show();

                    listViewBloodTypes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            bloodType.setText(bloodTypeArray[position]);
                            dialog.dismiss();
                        }
                    });




                }

                return false;
            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token != null && userId != null)
                profilePresenter.updatePresenter(token , userId);

                else
                    Toast.makeText(DonstorProfileActivity.this, getString(R.string.general_error), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void getProfileInfo(){

        LoginResponse loginResponse = Utilities.retrieveUserInfo(DonstorProfileActivity.this);
        nameEdit.setText(loginResponse.getData().getName());
        emailEdit.setText(loginResponse.getData().getEmail());
        mobileEdit.setText(loginResponse.getData().getPhone());
        bloodType.setText(loginResponse.getData().getBlood_type());
        ageEdit.setText(loginResponse.getData().getAge());
        addressEdit.setText(loginResponse.getData().getAddress());

        token = loginResponse.getData().getToken();
        userId = loginResponse.getData().getId();
        if(loginResponse.getData().getAvailable().equals("1")){
        switchAvailability.setChecked(true);}
        else {
            switchAvailability.setChecked(false);
        }
      countryId =   loginResponse.getData().getCountry_id();
       stateId =  loginResponse.getData().getState_id();
        cityId = loginResponse.getData().getCity_id();
        profilePresenter.getCountry(loginResponse.getData().getCountry_id());
        profilePresenter.getState(loginResponse.getData().getCountry_id(),loginResponse.getData().getState_id());
        profilePresenter.getCity(loginResponse.getData().getState_id() , loginResponse.getData().getCity_id());

    }


    @Override
    public void showLoading() {

        progressBarProfile.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBarProfile.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent i  = new Intent(DonstorProfileActivity.this , LoginActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
    }

    @Override
    public void showErrorMessage(String message) {

        Toast.makeText(this, getString(R.string.general_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {

       // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(DonstorProfileActivity.this , UpdateSuccessActivity.class));
    }

    @Override
    public void showNameError() {

        nameEdit.setError(getString(R.string.name_error));
    }

    @Override
    public void showAgeError() {
        ageEdit.setError(getString(R.string.age_error));

    }

    @Override
    public void showAgeLimitError() {
        ageEdit.setError(getString(R.string.age_error_limit));

    }

    @Override
    public void showMobileError() {
        mobileEdit.setError(getString(R.string.phone_error));

    }

    @Override
    public void showBloodTypeError() {
        bloodType.setError(getString(R.string.blood_type_error));

    }

    @Override
    public void showCountryError() {
        Toast.makeText(this, getString(R.string.country_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showStateError() {
        Toast.makeText(this, getString(R.string.state_error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showCityError() {
        Toast.makeText(this, getString(R.string.city_error), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showEmailError() {
        emailEdit.setError(getString(R.string.email_error));
    }



    @Override
    public String getCountyId() {
        return countryId;
    }

    @Override
    public String getSateId() {
        return stateId;
    }

    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public String getName() {
        return nameEdit.getText().toString();
    }

    @Override
    public String getAge() {
        return ageEdit.getText().toString();
    }

    @Override
    public String getMobile() {
        return mobileEdit.getText().toString();
    }

    @Override
    public String getBloodType() {
        return bloodType.getText().toString();
    }

    @Override
    public String getEmail() {
        return emailEdit.getText().toString();
    }


    @Override
    public String getAvailability() {
        return available;
    }

    @Override
    public String getAddress() {
        return addressEdit.getText().toString();
    }

    @Override
    public void showCountry(String country) {
        textCountryName.setText(country);
    }

    @Override
    public void showState(String state) {

        textStateName.setText(state);
    }

    @Override
    public void showCity(String city) {

        textCityName.setText(city);
    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                String countryEnglish = data.getStringExtra("country_en");
                String countryArabic = data.getStringExtra("country_ar");


                if (Utilities.getLanguage().equals("en")) {
                    if (countryEnglish != null) {

                        textCountryName.setText(countryEnglish);
                        countryId = data.getStringExtra("country_id");
                        textStateName.setText(getString(R.string.state));
                        textCityName.setText(getString(R.string.city));
                    } else {
                        textCountryName.setText(getString(R.string.country));

                    }
                } else if (Utilities.getLanguage().equals("ar")) {
                    if (countryArabic != null) {

                        textCountryName.setText(countryArabic);
                        countryId = data.getStringExtra("country_id");
                        textStateName.setText(getString(R.string.state));
                        textCityName.setText(getString(R.string.city));
                    } else {
                        textCountryName.setText(getString(R.string.country));

                    }
                }

            }
        }
        else  if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String stateEnglish = data.getStringExtra("state_en");
                String stateArabic = data.getStringExtra("state_ar");


                if (Utilities.getLanguage().equals("en")) {
                    if (stateEnglish != null) {
                        textStateName.setText(stateEnglish);
                        stateId = data.getStringExtra("state_id");
                        textCityName.setText(getString(R.string.city));
                    } else {
                        textStateName.setText(getString(R.string.state));
                    }

                } else if (Utilities.getLanguage().equals("ar")) {
                    if (stateArabic != null) {
                        textStateName.setText(stateArabic);
                        stateId = data.getStringExtra("state_id");
                        textCityName.setText(getString(R.string.city));
                    } else {
                        textStateName.setText(getString(R.string.state));
                    }

                }
            }

        }

        else  if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                String cityEnglish = data.getStringExtra("city_en");
                String cityArabic = data.getStringExtra("city_ar");


                if (Utilities.getLanguage().equals("en")) {
                    if (cityEnglish != null) {
                        textCityName.setText(cityEnglish);
                        cityId = data.getStringExtra("city_id");
                    } else {
                        textCityName.setText(getString(R.string.city));
                    }
                } else if (Utilities.getLanguage().equals("ar")) {
                    if (cityArabic != null) {
                        textCityName.setText(cityArabic);
                        cityId = data.getStringExtra("city_id");
                    } else {
                        textCityName.setText(getString(R.string.city));
                    }
                }
            }

        }


    }//onActivityResult

}
