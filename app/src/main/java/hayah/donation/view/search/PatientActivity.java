package hayah.donation.view.search;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import hayah.donation.models.search.SearchResponse;
import hayah.donation.view.Country.countryList.city.CityListActivity;
import hayah.donation.view.Country.countryList.country.CountryListActivity;
import hayah.donation.view.Country.countryList.state.StateActivity;
import hayah.donation.view.search.adapter.SearchAdapter;

public class PatientActivity extends AppCompatActivity implements searchView {


    private EditText  bloodEdit;
    private Button searchBtn ;
    private ProgressBar progressBarSearch;
    private RecyclerView recyclerViewSearch;


    SearchAdapter searchAdapter ;
    @Inject
    SearchPresenter searchPresenter;

    AppCompatCheckBox checkBoxAll;
    TextView txtNoResult;

    String bloodTypeArray[] = {"A+" ,"A-" ,"B+" , "B-" , "O+" , "O-","AB+","AB-"};

    boolean flagChecked = false;

    private LinearLayout countrySpinner, stateSpinner , citySpinner;
    private TextView textCountryName , textStateName , textCityName ;

    String countryId , stateId , cityId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        MobileAds.initialize(this,
                Utilities.ADMOB_INTIALIZE );

       AdView mAdView = findViewById(R.id.adView_search_top);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        bloodEdit = findViewById(R.id.edit_search_blood);
        searchBtn = findViewById(R.id.button_search);
        progressBarSearch = findViewById(R.id.progress_search);
        recyclerViewSearch = findViewById(R.id.recycleView_search);
        checkBoxAll = findViewById(R.id.checkbox_all);
        txtNoResult = findViewById(R.id.text_no_result);

        countrySpinner = findViewById(R.id.spinner_country);
        stateSpinner = findViewById(R.id.spinner_state);
        citySpinner = findViewById(R.id.spinner_city);
        textCountryName = findViewById(R.id.text_country_name);
        textStateName = findViewById(R.id.text_state_name);
        textCityName = findViewById(R.id.text_city_name);


        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        searchPresenter.onAttach(this);


        RecyclerView.LayoutManager mLayoutManager =  new LinearLayoutManager(PatientActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(mLayoutManager);
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearch.setLayoutManager(mLayoutManager);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(bloodEdit.getText().toString() .equals("") && checkBoxAll.isChecked()) {

                    searchPresenter.searchPresenter(countryId, stateId, cityId, bloodEdit.getText().toString());
                }
                else if(!bloodEdit.getText().toString() .equals("") && !checkBoxAll.isChecked())
                {
                    searchPresenter.searchPresenter(countryId, stateId, cityId, bloodEdit.getText().toString());
                }

                else if(bloodEdit.getText().toString() .equals("") && !checkBoxAll.isChecked())
                {
                    Toast.makeText(PatientActivity.this, getString(R.string.blood_type_error), Toast.LENGTH_SHORT).show();
                }



                }
        });


        countrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientActivity.this ,CountryListActivity.class);
                startActivityForResult(intent , 1);

            }
        });


        stateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countryId!= null) {
                    Intent intent = new Intent(PatientActivity.this, StateActivity.class);
                    intent.putExtra("country_id", countryId);
                    startActivityForResult(intent, 2);
                }
                else
                {
                    Toast.makeText(PatientActivity.this, getString(R.string.select_country), Toast.LENGTH_LONG).show();
                }
            }
        });


        citySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateId!= null) {
                    Intent intent = new Intent(PatientActivity.this, CityListActivity.class);
                    intent.putExtra("state_id", stateId);
                    startActivityForResult(intent, 3);
                }
                else
                {
                    Toast.makeText(PatientActivity.this, getString(R.string.state), Toast.LENGTH_LONG).show();
                }
            }
        });



        checkBoxAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(isChecked == true)
                {
                    bloodEdit.setText("");
                    bloodEdit.setEnabled(false);
                    flagChecked =true;
                }
                else
                {
                    bloodEdit.setEnabled(true);
                    flagChecked = false;
                }
            }
        });


        bloodEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    final Dialog dialog = new Dialog(PatientActivity.this);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                    dialog.setContentView(R.layout.dialog_blood_type);
                    ListView listViewBloodTypes = dialog.findViewById(R.id.listView_blood_types);

                    ArrayAdapter adapter = new ArrayAdapter(PatientActivity.this,android.R.layout.simple_spinner_dropdown_item , bloodTypeArray);
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
                            bloodEdit.setText(bloodTypeArray[position]);
                            bloodEdit.setError(null);
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

        progressBarSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBarSearch.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCityError() {
        Toast.makeText(this, getString(R.string.select_city), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCountryError() {
        Toast.makeText(this, getString(R.string.select_country), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showStateError() {
        Toast.makeText(this, getString(R.string.select_state), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showBloodTypeError() {

    }


    @Override
    public void showSearchResponse(SearchResponse searchResponse) {


        if(searchResponse.getData().getVolunteers().size() > 0) {
            searchAdapter = new SearchAdapter(searchResponse.getData().getVolunteers(), PatientActivity.this);
            recyclerViewSearch.setAdapter(searchAdapter);

            recyclerViewSearch.setVisibility(View.VISIBLE);
            txtNoResult.setVisibility(View.GONE);

        }

        else
        {
            recyclerViewSearch.setVisibility(View.GONE);
             txtNoResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public String getCountryId() {
        return countryId;
    }

    @Override
    public String getStateId() {
        return stateId;
    }

    @Override
    public String getCityId() {
        return cityId;
    }


    @Override
    public String getBloodType() {
        return bloodEdit.getText().toString();
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
