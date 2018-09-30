package hayah.hayah.view.Country.countryList;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import hayah.hayah.R;
import hayah.hayah.dagger.DaggerApplication;
import hayah.hayah.models.country.CountryResponse;

public class CountryListActivity extends AppCompatActivity implements  CountryListView {


    @Inject
    CountryListPresenter countryListPresenter;
    private ListView listViewCountries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        listViewCountries = findViewById(R.id.listView_countries) ;


        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        countryListPresenter.onAttach(this);


        countryListPresenter.getCountries();



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

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showCountryList(final List<CountryResponse> responseList) {


        CountryListAdapter adapter = new CountryListAdapter(CountryListActivity.this , responseList);
        listViewCountries.setAdapter(adapter);


        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("country_en",responseList.get(position).getName_en());
                returnIntent.putExtra("country_ar",responseList.get(position).getName_ar());
                returnIntent.putExtra("phone_code",responseList.get(position).getPhonecode());
                returnIntent.putExtra("country_id",responseList.get(position).getId());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();


            }
        });



    }
}
