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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.models.search.SearchResponse;
import hayah.donation.view.places.PlacesActivity;
import hayah.donation.view.search.adapter.SearchAdapter;

public class PatientActivity extends AppCompatActivity implements searchView {


    private EditText cityEdit , bloodEdit;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);


        cityEdit = findViewById(R.id.edit_search_city);
        bloodEdit = findViewById(R.id.edit_search_blood);
        searchBtn = findViewById(R.id.button_search);
        progressBarSearch = findViewById(R.id.progress_search);
        recyclerViewSearch = findViewById(R.id.recycleView_search);
        checkBoxAll = findViewById(R.id.checkbox_all);
        txtNoResult = findViewById(R.id.text_no_result);


        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        searchPresenter.onAttach(this);


        RecyclerView.LayoutManager mLayoutManager =  new LinearLayoutManager(PatientActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewSearch.setLayoutManager(mLayoutManager);
        recyclerViewSearch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSearch.setLayoutManager(mLayoutManager);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cityEdit.getText().toString().equals(""))
                {
                    cityEdit.setError("لابد من اختيار المدينة القريبة منك");
                    return;
                }
              else  if(bloodEdit.getText().toString().equals("") && flagChecked == false) {
                    bloodEdit.setError("لابد من اختيار فصيلة الدم");

                    return;
                }

                else
                {
                    searchPresenter.searchPresenter();

                }

                }
        });




        cityEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Intent intent = new Intent(PatientActivity.this , PlacesActivity.class);
                    startActivityForResult(intent , 2040);
                }


                return false;
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

        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {

        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCityError(String cityError) {
        cityEdit.setError(cityError);
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
    public String getCity() {
        return cityEdit.getText().toString();
    }

    @Override
    public String getBloodType() {
        return bloodEdit.getText().toString();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2040) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                cityEdit.setText(result);
                cityEdit.setError(null);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }



    }
}
