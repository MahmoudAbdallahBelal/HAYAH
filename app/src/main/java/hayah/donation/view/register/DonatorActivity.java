package hayah.donation.view.register;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.view.Country.countryList.country.CountryListActivity;
import hayah.donation.view.Country.countryList.city.CityListActivity;
import hayah.donation.view.Country.countryList.state.StateActivity;
import okhttp3.internal.Util;

public class DonatorActivity extends AppCompatActivity implements RegisterView  , View.OnClickListener{


    @Inject
    RegisterPresenter registerPresenter;

    private EditText nameEdit, ageEdit,mobileEdit  , bloodType , emailEdit , passwordEdit;
    private AutoCompleteTextView addressEdit,addressEdit2;
    private Button registerBtn;
    private ProgressBar progressBarRegister;

    private LinearLayout linearLayoutAddress2 , linearLayoutAddress1 , linearLayoutMobile2;
    private ImageButton addAddress , removeAddress , addMobile , removeMobile;

    private LinearLayout countrySpinner, stateSpinner , citySpinner;
    private TextView textCountryName , textStateName , textCityName ;



    String bloodTypeArray[] = {"A+" ,"A-" ,"B+" , "B-" , "O+" , "O-","AB+","AB-" , "لا أعلم"};

    String countryId , stateId , cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_register);


        progressBarRegister = findViewById(R.id.progress_register);
        registerBtn = findViewById(R.id.button_register);
        nameEdit = findViewById(R.id.edit_name);
        ageEdit = findViewById(R.id.edit_age);
        mobileEdit = findViewById(R.id.edit_mobile);
        bloodType = findViewById(R.id.edit_blood_type);
        countrySpinner = findViewById(R.id.spinner_country);
        stateSpinner = findViewById(R.id.spinner_state);
        citySpinner = findViewById(R.id.spinner_city);
        textCountryName = findViewById(R.id.text_country_name);
        textStateName = findViewById(R.id.text_state_name);
        textCityName = findViewById(R.id.text_city_name);
        emailEdit = findViewById(R.id.edit_email);
        passwordEdit = findViewById(R.id.edit_password);

        MobileAds.initialize(this,
                Utilities.ADMOB_INTIALIZE);

        AdView mAdView = findViewById(R.id.adView_donator_bottom);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        registerPresenter.onAttach(this);


        registerBtn = findViewById(R.id.button_register);
        nameEdit = findViewById(R.id.edit_name);
        ageEdit = findViewById(R.id.edit_age);
        mobileEdit = findViewById(R.id.edit_mobile);
        bloodType = findViewById(R.id.edit_blood_type);
        countrySpinner = findViewById(R.id.spinner_country);
        stateSpinner = findViewById(R.id.spinner_state);
        citySpinner = findViewById(R.id.spinner_city);
        textCountryName = findViewById(R.id.text_country_name);
        textStateName = findViewById(R.id.text_state_name);
        textCityName = findViewById(R.id.text_city_name);
        emailEdit = findViewById(R.id.edit_email);
        passwordEdit = findViewById(R.id.edit_password);


        registerBtn.setOnClickListener(this);
        countrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonatorActivity.this ,CountryListActivity.class);
                startActivityForResult(intent , 1);

            }
        });


        stateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countryId!= null) {
                    Intent intent = new Intent(DonatorActivity.this, StateActivity.class);
                    intent.putExtra("country_id", countryId);
                    startActivityForResult(intent, 2);
                }
                else
                {
                    Toast.makeText(DonatorActivity.this, getString(R.string.select_country), Toast.LENGTH_LONG).show();
                }
            }
        });


       citySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateId!= null) {
                    Intent intent = new Intent(DonatorActivity.this, CityListActivity.class);
                    intent.putExtra("state_id", stateId);
                    startActivityForResult(intent, 3);
                }
                else
                {
                    Toast.makeText(DonatorActivity.this, getString(R.string.city), Toast.LENGTH_LONG).show();
                }
            }
        });




        bloodType.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    final Dialog dialog = new Dialog(DonatorActivity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                    dialog.setContentView(R.layout.dialog_blood_type);
                    ListView listViewBloodTypes = dialog.findViewById(R.id.listView_blood_types);

                    ArrayAdapter adapter = new ArrayAdapter(DonatorActivity.this,android.R.layout.simple_spinner_dropdown_item , bloodTypeArray);
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




    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void showLoading() {

        progressBarRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBarRegister.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {


        DonatorActivity.this.finish();
        startActivity(new Intent(DonatorActivity.this , SuccessRegisterActivity.class));
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
    public void showPasswordError() {
        passwordEdit.setError(getString(R.string.password_error));
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
    public String getPassword() {
        return passwordEdit.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_register :

                registerPresenter.registerPresenter(nameEdit.getText().toString() , ageEdit.getText().toString(),bloodType.getText().toString(),emailEdit.getText().toString(),passwordEdit.getText().toString()
                ,countryId,stateId,cityId,mobileEdit.getText().toString());
                break;



        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

                String countryEnglish=data.getStringExtra("country_en");
                String countryArabic=data.getStringExtra("country_ar");



                if(Utilities.getLanguage().equals("en")){
                if(countryEnglish != null ) {

                    textCountryName .setText(countryEnglish);
                    countryId = data.getStringExtra("country_id");
                    textStateName.setText(getString(R.string.state));
                    textCityName.setText(getString(R.string.city));
                }
                else
                {
                    textCountryName .setText(getString(R.string.country));

                }
                }
                else if (Utilities.getLanguage().equals("ar")){
                    if(countryArabic != null ) {

                        textCountryName .setText(countryArabic);
                        countryId = data.getStringExtra("country_id");
                        textStateName.setText(getString(R.string.state));
                        textCityName.setText(getString(R.string.city));
                    }
                    else
                    {
                        textCountryName .setText(getString(R.string.country));

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
            else if (requestCode == 3) {
                if (resultCode == Activity.RESULT_OK) {
                    String cityEnglish = data.getStringExtra("city_en");
                    String cityArabic = data.getStringExtra("city_en");


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
