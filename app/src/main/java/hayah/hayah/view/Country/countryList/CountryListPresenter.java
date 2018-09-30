package hayah.hayah.view.Country.countryList;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import hayah.hayah.apiClient.ApiInterface;
import hayah.hayah.baseClass.BasePresenter;
import hayah.hayah.dagger.DaggerApplication;
import hayah.hayah.helper.Utilities;
import hayah.hayah.models.country.CountryResponse;
import hayah.hayah.models.register.RegisterRequest;
import hayah.hayah.models.register.RegisterResponse;
import hayah.hayah.view.register.RegisterView;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CountryListPresenter implements BasePresenter<CountryListView> {
    CountryListView mView;
    boolean isLoaded = false;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;



    @Override
    public void onAttach(CountryListView view) {
        mView = view;
        mView.onAttache();

    }



    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public CountryListPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void getCountries() {

        try {
            if (!Utilities.checkConnection(mContext)) {
                mView.showErrorMessage("");
                checkConnection(false);
                return;
            }

            if (!Utilities.checkConnection(mContext)) {

                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                return;

            }


            else {
                mView.showLoading();

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

                                mView.showCountryList(response);

                            }
                        });


            }

        }catch (Exception e)
        {
            mView.showErrorMessage("");

        }

    }








    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            getCountries();
            isLoaded = false;
            mView.showErrorMessage("internet connected");
        }if(!isConnected && isLoaded){
            //offline check and  data loaded
            mView.showErrorMessage("offline");


        }else if(isConnected && isLoaded){
            mView.showErrorMessage("connect to internet");
        }
    }



    private String getErrorMessage(ResponseBody responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody.string());
            return jsonObject.getString("message");
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
