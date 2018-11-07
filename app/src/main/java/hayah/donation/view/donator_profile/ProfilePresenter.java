package hayah.donation.view.donator_profile;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.apiClient.ApiInterface;
import hayah.donation.baseClass.BasePresenter;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.models.States.StatesResponse;
import hayah.donation.models.city.CityResponse;
import hayah.donation.models.country.CountryResponse;
import hayah.donation.models.register.RegisterRequest;
import hayah.donation.models.register.RegisterResponse;
import hayah.donation.models.update.UpdateRequest;
import hayah.donation.models.update.UpdateResponse;
import hayah.donation.view.register.RegisterView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProfilePresenter implements BasePresenter<ProfileView> {
    ProfileView mView;
    boolean isLoaded = false;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;
    UpdateRequest updateRequest;



    @Override
    public void onAttach(ProfileView view) {
        mView = view;
        mView.onAttache();

    }



    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public ProfilePresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void updatePresenter(String token , String userId) {

        try {
            if (!Utilities.checkConnection(mContext)) {
                mView.showErrorMessage(mContext.getString(R.string.check_internet));
                checkConnection(false);
                return;
            }


            if (!Utilities.checkConnection(mContext)) {

                Toast.makeText(mContext, mContext.getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
                return;

            }
            else if (mView.getName().equals("")) {
                mView.showNameError();
                return;

            } else if (mView.getAge().equals("")) {

                mView.showAgeError();
                return;

            }
            else if (Integer.parseInt(mView.getAge()) < 18 ) {

                mView.showAgeLimitError();
                return;

            }
            else if (mView.getCountyId().equals("")) {

                mView.showCountryError();
                return;

            }

            else if (mView.getSateId().equals("")) {

                mView.showStateError();
                return;

            }
            else if (mView.getCityId().equals("")) {

                mView.showCityError();
                return;

            }
            else if (mView.getMobile().equals("")) {

                mView.showMobileError();
                return;

            }

            else if (mView.getBloodType().equals("")) {

                mView.showBloodTypeError();
                return;

            }
            else if (mView.getEmail().equals("")) {

                mView.showEmailError();
                return;

            }

            else {
                mView.showLoading();

                updateRequest = new UpdateRequest();
                updateRequest.setName(mView.getName());
                updateRequest.setAge(mView.getAge());
                updateRequest.setCountry_id(mView.getCountyId());
                updateRequest.setState_id(mView.getSateId());
                updateRequest.setCity_id(mView.getCityId());
                updateRequest.setPhone(mView.getMobile());
                updateRequest.setBlood_type(mView.getBloodType());
                updateRequest.setEmail(mView.getEmail());
                updateRequest.setAvailable(mView.getAvailability());
                updateRequest.setAddress(mView.getAddress());

                mApiInterface.updateObservable("Bearer "+token ,userId ,updateRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<UpdateResponse>() {
                            @Override
                            public final void onCompleted() {

                                mView.hideLoading();
                            }

                            @Override
                            public final void onError(Throwable e) {


                                mView.hideLoading();


                            }

                            @Override
                            public final void onNext(UpdateResponse response) {
                                isLoaded = true;
                                mView.showSuccessMessage(response.getMessage());

                            }
                        });


            }

        }catch (Exception e)
        {
            mView.showErrorMessage(mContext.getString(R.string.general_error));

        }

    }








    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            isLoaded = false;
            mView.showErrorMessage(mContext.getString(R.string.check_internet));
        }if(!isConnected && isLoaded){
            //offline check and  data loaded
            mView.showErrorMessage(mContext.getString(R.string.check_internet));


        }else if(isConnected && isLoaded){
            mView.showErrorMessage(mContext.getString(R.string.check_internet));
        }
    }


    public void getCountry(final String countryId)
    {

        mApiInterface.getCountriesObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CountryResponse>>() {
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
                    public final void onNext(List<CountryResponse> response) {

                        for(int i = 0 ; i<response.size() ; i++) {
                            if (countryId.equals(response.get(i).getId()))
                            {
                                if(Utilities.getLanguage().equals("en")) {

                                    mView.showCountry(response.get(i).getName_en());
                                }
                                else if(Utilities.getLanguage().equals("ar"))
                                {
                                    mView.showCountry(response.get(i).getName_ar());

                                }
                            }

                        }

                    }
                });

    }

    public void getState(String countryId, final String stateId)
    {
        Integer countryNewId = Integer.parseInt(countryId);

        mApiInterface.getStatesObservable(""+countryNewId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<StatesResponse>>() {
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
                    public final void onNext(List<StatesResponse> response) {

                        for(int i = 0 ; i<response.size() ; i++) {
                            if (stateId.equals(response.get(i).getId()))
                            {

                                if(Utilities.getLanguage().equals("en")) {

                                    mView.showState(response.get(i).getName_en());
                                }
                                else if(Utilities.getLanguage().equals("ar"))
                                {
                                    mView.showState(response.get(i).getName_ar());

                                }                            }

                        }
                    }
                });


    }

    public void getCity(String stateId, final String cityId)
    {
        mApiInterface.getCityObservable(stateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CityResponse>>() {
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
                    public final void onNext(List<CityResponse> response) {

                        for(int i = 0 ; i<response.size() ; i++) {
                            if (cityId.equals(response.get(i).getId()))
                            {


                                if(Utilities.getLanguage().equals("en")) {

                                    mView.showCity(response.get(i).getName_en());
                                }
                                else if(Utilities.getLanguage().equals("ar"))
                                {
                                    mView.showCity(response.get(i).getName_ar());

                                }                            }

                        }



                    }
                });

    }


}
