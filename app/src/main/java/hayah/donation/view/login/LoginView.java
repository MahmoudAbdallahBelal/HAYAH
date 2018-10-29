package hayah.donation.view.login;


import hayah.donation.baseClass.BaseView;
import hayah.donation.models.login.LoginResponse;

//class created for register function for main view
public interface LoginView extends BaseView {

    void showLoading();
    void hideLoading();

    void  showEmailError();
    void showPasswordError();
    void showInternetError(String error);
    void showSuccess(String message);
    void  showError(String error);

}
