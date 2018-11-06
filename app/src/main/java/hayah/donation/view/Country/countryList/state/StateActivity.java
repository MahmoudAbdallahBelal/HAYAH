package hayah.donation.view.Country.countryList.state;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.models.States.StatesResponse;
import hayah.donation.models.country.CountryResponse;
import hayah.donation.view.Country.countryList.country.ChooseCountryAdapter;
import hayah.donation.view.Country.countryList.country.CountryListActivity;

public class StateActivity extends AppCompatActivity implements StateListView{


    @Inject
    StatesListPresenter statesListPresenter;
    private ListView statesListView;

    private TextView txtNoResult ;

    private ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        statesListView = findViewById(R.id.listView_states);

        progressDialog = new ProgressDialog(StateActivity.this);

        txtNoResult = findViewById(R.id.not_result_state);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
         statesListPresenter.onAttach(this);

        if(getIntent().getStringExtra("country_id") != null) {
            statesListPresenter.getStates(getIntent().getStringExtra("country_id"));
        }


    }

    @Override
    public void onAttache() {

    }

    @Override
    public void onDetach() {

    }

    @Override
    public void showLoading() {

        progressDialog.show();
    }

    @Override
    public void hideLoading() {

        progressDialog.dismiss();
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
    public void showStateList(final List<StatesResponse> responseList) {


        try {
            List<StatesResponse> responseListArabic = new ArrayList<>();


            if(responseList.size() != 0) {

                txtNoResult.setVisibility(View.INVISIBLE);

                if (Utilities.getLanguage().equals("ar")) {

                    for (int i = 0; i < responseList.size(); i++) {
                        if (!responseList.get(i).getName_ar().equals("غير مترجم")) {

                            responseListArabic.add(responseList.get(i));
                        }

                    }
                    if(responseListArabic.size() == 0 ){

                        txtNoResult.setVisibility(View.VISIBLE);
                    }
                    else {
                        stateListAdapter adapter = new stateListAdapter(StateActivity.this, responseListArabic);
                        statesListView.setAdapter(adapter);
                    }

                }
                else if (Utilities.getLanguage().equals("en")) {
                    stateListAdapter adapter = new stateListAdapter(StateActivity.this, responseList);
                    statesListView.setAdapter(adapter);
                }


                statesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("state_en", responseList.get(position).getName_en());
                        returnIntent.putExtra("state_ar", responseList.get(position).getName_ar());
                        returnIntent.putExtra("state_id", responseList.get(position).getId());
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();


                    }
                });

            }
            else
            {
                txtNoResult.setVisibility(View.VISIBLE);

            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
