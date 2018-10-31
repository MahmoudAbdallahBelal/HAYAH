package hayah.donation.view.register;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.apiClient.ApiInterface;
import hayah.donation.baseClass.BasePresenter;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.models.register.RegisterRequest;
import hayah.donation.models.register.RegisterResponse;
import okhttp3.ResponseBody;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RegisterPresenter implements BasePresenter<RegisterView> {
    RegisterView mView;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    RegisterRequest registerRequest;



    @Override
    public void onAttach(RegisterView view) {
        mView = view;
        mView.onAttache();

    }



    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public RegisterPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void registerPresenter(String name , String age , String bloodType , String email ,String password , String countryId ,String stateId , String cityId,String mobile) {

        try {
            if (!Utilities.checkConnection(mContext)) {
                mView.showErrorMessage(mContext.getString(R.string.check_internet));
                return;
            }


            else if (name.equals("")) {
                mView.showNameError();
                return;

            } else if (age.equals("")) {

                mView.showAgeError();
                return;

            }
            else if ((Integer.parseInt(age)) < 18 ) {

                mView.showAgeLimitError();
                return;

            }
            else if (bloodType.equals("")) {

                mView.showBloodTypeError();
                return;

            }
            else if (password.equals("")) {

                mView.showPasswordError();
                return;

            }

            else if (countryId == null) {

                mView.showCountryError();
                return;

            }

            else if (stateId == null ) {

                mView.showStateError();
                return;

            }
            else if (cityId == null) {

                mView.showCityError();
                return;

            }
            else if (mobile.equals("")) {

                mView.showMobileError();
                return;

            }



            else {
                mView.showLoading();

                registerRequest = new RegisterRequest();
                registerRequest.setName(mView.getName());
                registerRequest.setAge(mView.getAge());
                registerRequest.setCountry_id(mView.getCountyId());
                registerRequest.setState_id(mView.getSateId());
                registerRequest.setCity_id(mView.getCityId());
                registerRequest.setPhone(mView.getMobile());
                registerRequest.setBlood_type(mView.getBloodType());
                registerRequest.setEmail(mView.getEmail());
                registerRequest.setPassword(mView.getPassword());
                registerRequest.setAddress(mView.getAddress());
                registerRequest.setAvailable("1");

                mApiInterface.registerObservable(registerRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<RegisterResponse>() {
                            @Override
                            public final void onCompleted() {

                                mView.hideLoading();
                            }

                            @Override
                            public final void onError(Throwable e) {


                                Toast.makeText(mContext, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                mView.hideLoading();


                            }

                            @Override
                            public final void onNext(RegisterResponse response) {
                                mView.showSuccessMessage(response.getMessage());

                            }
                        });


            }

        }catch (Exception e)
        {
            mView.showErrorMessage(mContext.getString(R.string.general_error));

        }

    }











}
