package hayah.donation.view.login;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import hayah.donation.R;
import hayah.donation.apiClient.ApiInterface;
import hayah.donation.baseClass.BasePresenter;
import hayah.donation.dagger.DaggerApplication;
import hayah.donation.helper.Utilities;
import hayah.donation.models.login.LoginRequest;
import hayah.donation.models.login.LoginResponse;
import hayah.donation.models.register.RegisterRequest;
import hayah.donation.models.register.RegisterResponse;
import hayah.donation.view.register.RegisterView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginPresenter implements BasePresenter<LoginView> {
    LoginView mView;
    boolean isLoaded = false;
    @Inject
    ApiInterface mApiInterface;
    @Inject
    Context mContext;

   LoginRequest loginRequest ;


    @Override
    public void onAttach(LoginView view) {
        mView = view;
        mView.onAttache();

    }



    @Override
    public void onDetach() {
        mView = null;
    }
    //create Constructor to get reference of api interface object
    public LoginPresenter(Context context){
        ((DaggerApplication)context).getAppComponent().inject(this);
    }

    //this function created to load items from specific endpoint
    public void loginPresenter(String email , String password) {

        try {
            if (!Utilities.checkConnection(mContext)) {
                mView.showInternetError(mContext.getString(R.string.check_internet));
                checkConnection(false);
                return;
            }
            else {
                loginRequest = new LoginRequest();
                loginRequest.setEmail(email);
                loginRequest.setPassword(password);
                mApiInterface.loginObservable(loginRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<LoginResponse>() {
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
                            public final void onNext(LoginResponse response) {
                                isLoaded = true;
                                mView.showSuccess(response.getToken());

                            }
                        });


            }

        }catch (Exception e)
        {
            mView.showError(mContext.getString(R.string.general_error));

        }

    }








    void checkConnection(boolean isConnected) {
        //check internet and  data not loaded
        if(isConnected  && !isLoaded){
            isLoaded = false;
        }if(!isConnected && isLoaded){
            //offline check and  data loaded


        }else if(isConnected && isLoaded){
        }
    }



}
