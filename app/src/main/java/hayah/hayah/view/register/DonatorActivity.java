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
import android.widget.Toast;

import javax.inject.Inject;

import hayah.hayah.R;
import hayah.hayah.dagger.DaggerApplication;
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



    String bloodTypeArray[] = {"A+" ,"A-" ,"B+" , "B-" , "O+" , "O-","AB+","AB-" , "لا أعلم"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donator);


        nameEdit = findViewById(R.id.edit_name);
        registerBtn = findViewById(R.id.button_register);
        registerBtn.setOnClickListener(this);
        progressBarRegister = findViewById(R.id.progress_register);
        linearLayoutAddress2 = findViewById(R.id.linear_address);
        addAddress = findViewById(R.id.image_button_add_address);
        removeAddress = findViewById(R.id.image_button_add_address_2);
        linearLayoutAddress1 = findViewById(R.id.linear_address_1);

        linearLayoutMobile2 = findViewById(R.id.linear_mobile_2);

        addMobile = findViewById(R.id.image_button_add_mobile);

        removeMobile = findViewById(R.id.image_button_add_mobile_2);


        ageEdit      = findViewById(R.id.edit_age);
        addressEdit  = findViewById(R.id.edit_address);
        addressEdit2 = findViewById(R.id.edit_address_2);
        mobileEdit   = findViewById(R.id.edit_mobile);
        mobileEdit2  = findViewById(R.id.edit_mobile_2);
        bloodType    = findViewById(R.id.edit_blood_type);



        ((DaggerApplication) getApplication()).getAppComponent().inject(this);

        registerPresenter.onAttach(this);



        addressEdit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Intent intent = new Intent(DonatorActivity.this , PlacesActivity.class);
                    startActivityForResult(intent , 2020);
                }


                return false;
            }
        });



        addressEdit2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    Intent intent = new Intent(DonatorActivity.this , PlacesActivity.class);
                    startActivityForResult(intent , 2030);
                }


                return false;
            }
        });

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutAddress2.setVisibility(View.VISIBLE);
            }
        });

        removeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutAddress2.setVisibility(View.GONE);
            }
        });


        addMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutMobile2.setVisibility(View.VISIBLE);
            }
        });

        removeMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutMobile2.setVisibility(View.GONE);
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

      nameEdit.setError("أدخل الاسم");
    }

    @Override
    public void showAgeError() {

        ageEdit.setError("أدخل العمر");
    }

    @Override
    public void showAgeLimitError() {
        ageEdit.setError("لابد أن يبدأ سن المتبرع من ١٨ عام");

    }

    @Override
    public void showAddressError() {

        addressEdit.setError("أدخل العنوان");
    }

    @Override
    public void showMobileError() {
        mobileEdit.setError("أدخل رقم الموبايل");

    }

    @Override
    public void showBloodTypeError() {

        bloodType.setError("اختر فصيلة الدم الخاصة بك");
    }

    @Override
    public String getName() {
        return nameEdit.getText().toString();
    }

    @Override
    public String getAge() {
        return ageEdit.getText().toString();
    }

    @Override
    public String getAddress() {
        return addressEdit.getText().toString();
    }

    @Override
    public String getAddress2() {
        return addressEdit2.getText().toString();
    }

    @Override
    public String getMobile() {
        return mobileEdit.getText().toString();
    }

    @Override
    public String getMobile2() {
        return mobileEdit2.getText().toString();
    }

    @Override
    public String getBloodType() {
        return bloodType.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_register :

                registerPresenter.registerPresenter();
                break;



        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2020) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                addressEdit.setText(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }





     else if (requestCode == 2030) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                addressEdit2.setText(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }

    }
}
