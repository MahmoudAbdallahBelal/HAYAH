package hayah.donation.view.Country.countryList.city;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.models.city.CityResponse;

public class CityListActivity extends AppCompatActivity implements CityListView {


    @Inject
    CityListPresenter cityListPresenter;
    private ListView cityListView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        cityListView = findViewById(R.id.listView_city);

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

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showSuccessMessage(String message) {

    }

    @Override
    public void showCityList(final List<CityResponse> responseList) {

        CityListAdapter adapter = new CityListAdapter(CityListActivity.this , responseList);
        cityListView.setAdapter(adapter);


        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("city_en" , responseList.get(position).getName_en());
                returnIntent.putExtra("city_id" , responseList.get(position).getId());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();


            }
        });
    }
}
