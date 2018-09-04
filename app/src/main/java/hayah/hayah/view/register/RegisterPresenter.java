package hayah.hayah.view.register;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONObject;

import javax.inject.Inject;

import hayah.hayah.R;
import hayah.hayah.apiClient.ApiInterface;
import hayah.hayah.baseClass.BasePresenter;
import hayah.hayah.dagger.DaggerApplication;
import hayah.hayah.helper.Utilities;
import hayah.hayah.models.register.RegisterRequest;
import hayah.hayah.models.register.RegisterResponse;
import okhttp3.ResponseBody;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RegisterPresenter implements BasePresenter<RegisterView> {
    RegisterView mView;
    boolean isLoaded = false;
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
    public void registerPresenter() {

        try {
            if (!Utilities.checkConnection(mContext)) {
                mView.showErrorMessage("الرجاء التأكد من الاتصال بالانترنت");
                checkConnection(false);
                return;
            }


            if (!Utilities.checkConnection(mContext)) {

                Toast.makeText(mContext, "الرجاء التأكد من الاتصال بالانترنت", Toast.LENGTH_SHORT).show();
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
            else if (mView.getAddress().equals("")) {

                mView.showAddressError();
                return;

            } else if (mView.getMobile().equals("")) {

                mView.showMobileError();
                return;

            }

            else if (mView.getBloodType().equals("")) {

                mView.showBloodTypeError();
                return;

            }

            else {
                mView.showLoading();

                registerRequest = new RegisterRequest();
                registerRequest.setName(mView.getName());
                registerRequest.setAge(mView.getAge());
                registerRequest.setAddress(mView.getAddress());
                registerRequest.setAddress2(mView.getAddress2());
                registerRequest.setPhone(mView.getMobile());
                registerRequest.setPhone2(mView.getMobile2());
                registerRequest.setBlood_type(mView.getBloodType());

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
                                isLoaded = true;
                                mView.showSuccessMessage(response.getMessage());

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
            registerPresenter();
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
