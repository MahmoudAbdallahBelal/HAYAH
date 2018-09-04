package hayah.hayah.view.places;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import hayah.hayah.R;

public class PlacesActivity extends AppCompatActivity {


    public ListView listView;
    public EditText editText;
    GooglePlacesAutocompleteAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        // LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        listView = (ListView) findViewById(R.id.listView1);
        editText=(EditText) findViewById(R.id.edEnterLocation);
        TextView txtView = (TextView)findViewById(R.id.textView);
        //  CardView cardView=(CardView)findViewById(R.id.cardView);


        dataAdapter = new GooglePlacesAutocompleteAdapter(getApplicationContext(),R.layout.adapter_google_places_autocomplete);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        //enables filtering for the contents of the given ListView
        listView.setTextFilterEnabled(true);



        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged (Editable s){



            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());


            }


        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String  selectedLocation = dataAdapter.getItem(position);
                editText.setText(selectedLocation);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("result",selectedLocation);
                setResult(Activity.RESULT_OK,returnIntent);

                PlacesActivity.this.finish();

            }
        });


    }
}
