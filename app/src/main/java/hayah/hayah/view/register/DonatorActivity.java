package hayah.hayah.view.register;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import hayah.hayah.R;
import hayah.hayah.dagger.DaggerApplication;
import hayah.hayah.models.country.CountryResponse;
import hayah.hayah.view.Country.countryList.CountryListActivity;
import hayah.hayah.view.Country.countryList.city.CityListActivity;
import hayah.hayah.view.Country.countryList.state.StateActivity;
import hayah.hayah.view.places.PlacesActivity;

public class DonatorActivity extends AppCompatActivity implements RegisterView  , View.OnClickListener{


    @Inject
    RegisterPresenter registerPresenter;

    private EditText nameEdit, ageEdit,mobileEdit , mobileEdit2 , bloodType ;
    private AutoCompleteTextView addressEdit,addressEdit2;
    private Button registerBtn;
    private ProgressBar progressBarRegister;

    private LinearLayout linearLayoutAddress2 , linearLayoutAddress1 , linearLayoutMobile2;
    private ImageButton addAddress , removeAddress , addMobile , removeMobile;

    private LinearLayout countrySpinner, stateSpinner , citySpinner;
    private TextView textCountryName , textStateName , textCityName ;



    String bloodTypeArray[] = {"A+" ,"A-" ,"B+" , "B-" , "O+" , "O-","AB+","AB-" , "لا أعلم"};

    String countryId , stateId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator_register);



        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        registerPresenter.onAttach(this);


        bloodType = findViewById(R.id.edit_blood_type);
        countrySpinner = findViewById(R.id.spinner_country);
        stateSpinner = findViewById(R.id.spinner_state);
        textCountryName = findViewById(R.id.text_country_name);
        textStateName = findViewById(R.id.text_state_name);

        textCityName = findViewById(R.id.text_city_name);
        textStateName = findViewById(R.id.text_state_name);
        citySpinner = findViewById(R.id.spinner_city);

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

    }

    @Override
    public void showAgeError() {

    }

    @Override
    public void showAgeLimitError() {

    }

    @Override
    public void showAddressError() {

    }

    @Override
    public void showMobileError() {

    }

    @Override
    public void showBloodTypeError() {

    }



    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getAge() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getAddress2() {
        return null;
    }

    @Override
    public String getMobile() {
        return null;
    }

    @Override
    public String getMobile2() {
        return null;
    }

    @Override
    public String getBloodType() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_register :

               // registerPresenter.registerPresenter();
                break;



        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String countryEnglish=data.getStringExtra("country_en");
                textCountryName .setText(countryEnglish);
                countryId = data.getStringExtra("country_id");


                textStateName .setText(getString(R.string.state));
                textCityName .setText(getString(R.string.city));


            }

        }

       else  if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String countryEnglish=data.getStringExtra("state_en");
                textStateName .setText(countryEnglish);
                stateId = data.getStringExtra("state_id");


                textCityName .setText(getString(R.string.city));


            }

        }

        else  if (requestCode == 3) {
            if(resultCode == Activity.RESULT_OK){
                String cityEnglish=data.getStringExtra("city_en");
                textCityName .setText(cityEnglish);


            }

        }


    }//onActivityResult






}
