package hayah.donation.view.Country.countryList.country;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.models.country.CountryResponse;

public class CountryListActivity extends AppCompatActivity implements  CountryListView, ChooseCountryAdapter.OnCountrySelectedListener {


    @Inject
    CountryListPresenter countryListPresenter;
    private ListView listViewCountries;

    private Toolbar toolbar;
    private RecyclerView recyclerView ;

    private  String countryNameSelected;
    private  String NameCountryEn , NameCountryAr,CountryPhoneCode ,CountryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        toolbar = findViewById(R.id.toolbar);

        //listViewCountries = findViewById(R.id.listView_countries) ;
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.choose_maker_recycler);

        ((DaggerApplication) getApplication()).getAppComponent().inject(this);
        countryListPresenter.onAttach(this);

        setTitle("Choose Country");

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_close_clear_cancel));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult();
            }
        });

        if(getIntent().getStringExtra("country_selected") != null) {
            countryNameSelected = getIntent().getStringExtra("country_selected");
           }

        countryListPresenter.getCountries();



    }


    private  ChooseCountryAdapter adapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.country_menu, menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
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

        try {
            adapter = new ChooseCountryAdapter(responseList, countryNameSelected, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

            /*
            CountryListAdapter adapter = new CountryListAdapter(CountryListActivity.this , responseList);
        listViewCountries.setAdapter(adapter);


        listViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             NameCountryEn =   responseList.get(position).getName_en();
              NameCountryAr =  responseList.get(position).getName_ar();
                CountryPhoneCode = responseList.get(position).getPhonecode();
               CountryId =  responseList.get(position).getId();


            }
        });
*/


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void getCountrySelected(String countryName, String countryId, String phoneCountryCode) {

        countryNameSelected = countryName;
        CountryId = countryId;
        CountryPhoneCode = phoneCountryCode;

        setResult();
    }

    private void setResult(){

        Intent returnIntent = new Intent();
        returnIntent.putExtra("country_en",countryNameSelected);
        returnIntent.putExtra("country_ar",NameCountryAr);
        returnIntent.putExtra("phone_code",CountryPhoneCode);
        returnIntent.putExtra("country_id",CountryId);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }

}
