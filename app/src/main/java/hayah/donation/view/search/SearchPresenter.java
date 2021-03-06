package hayah.donation.view.search;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import javax.inject.Inject;

import hayah.donation.apiClient.ApiInterface;
import hayah.donation.baseClass.BasePresenter;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.models.search.SearchRequest;
import hayah.donation.models.search.SearchResponse;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class SearchPresenter implements BasePresenter<searchView> {
    searchView mView;
    boolean isLoaded = false;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;



    SearchRequest searchRequest ;
    @Override
    public void onAttach(searchView view) {
        mView = view;
        mView.onAttache();

    }



    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public SearchPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void searchPresenter(String countryId , String stateId , String cityId , String bloodType) {

        try {
           if(countryId == null)
           {

               mView.showCountryError();
           }
//           else if(stateId == null)
//           {
//               mView.showStateError();
//           }
//           else if(cityId == null)
//           {
//               mView.showCityError();
//           }
           else if(bloodType == null)
           {

               mView.showBloodTypeError();
           }

            else {
                mView.showLoading();

                searchRequest = new SearchRequest();
                searchRequest.setCountry_id(countryId);
                searchRequest.setState_id(stateId);
                searchRequest.setCity_id(cityId);
                searchRequest.setBlood_type(mView.getBloodType());

                mApiInterface.searchObservable(searchRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<SearchResponse>() {
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
                            public final void onNext(SearchResponse response) {
                                isLoaded = true;
                                mView.showSearchResponse(response);

                            }
                        });


            }

        }catch (Exception e)
        {
            mView.showErrorMessage("Error, try again");

        }

    }




    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
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
