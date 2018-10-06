package hayah.donation.view.Country.countryList.state;

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
import hayah.donation.models.States.StatesResponse;

public class StateActivity extends AppCompatActivity implements StateListView{


    @Inject
    StatesListPresenter statesListPresenter;
    private ListView statesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        statesListView = findViewById(R.id.listView_states);


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
    public void showStateList(final List<StatesResponse> responseList) {

        stateListAdapter adapter = new stateListAdapter(StateActivity.this , responseList);
        statesListView.setAdapter(adapter);


        statesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("state_en",responseList.get(position).getName_en());
                returnIntent.putExtra("state_ar",responseList.get(position).getName_ar());
                returnIntent.putExtra("state_id",responseList.get(position).getId());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();


            }
        });

    }
}
