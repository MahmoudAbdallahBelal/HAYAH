package hayah.donation.view.Country.countryList.city;

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

import java.util.List;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.models.city.CityResponse;
import hayah.donation.view.Country.countryList.country.CountryListActivity;

public class CityListActivity extends AppCompatActivity implements CityListView {


    @Inject
    CityListPresenter cityListPresenter;
    private ListView cityListView;


    private  ProgressDialog progressDialog;

    private TextView txtNoResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        cityListView = findViewById(R.id.listView_city);
        progressDialog = new ProgressDialog(CityListActivity.this);

        txtNoResult = findViewById(R.id.not_result_city);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
         cityListPresenter.onAttach(this);

        if(getIntent().getStringExtra("state_id") != null) {
            cityListPresenter.getCities(getIntent().getStringExtra("state_id"));
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
    public void showCityList(final List<CityResponse> responseList) {

        if(responseList.size() != 0) {
            txtNoResult.setVisibility(View.INVISIBLE);
            CityListAdapter adapter = new CityListAdapter(CityListActivity.this, responseList);
            cityListView.setAdapter(adapter);


            cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("city_en", responseList.get(position).getName_en());
                    returnIntent.putExtra("city_ar", responseList.get(position).getName_ar());
                    returnIntent.putExtra("city_id", responseList.get(position).getId());
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();


                }
            });
        }
        else
        {
            txtNoResult.setVisibility(View.VISIBLE);
        }
    }
}
