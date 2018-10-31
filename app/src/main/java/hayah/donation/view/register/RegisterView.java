package hayah.donation.view.register;


import hayah.donation.baseClass.BaseView;

//class created for register function for main view
public interface RegisterView extends BaseView {

    void showLoading();
    void hideLoading();
    void showErrorMessage(String message);
    void showSuccessMessage(String message);
    void showNameError();
    void showAgeError();
    void showAgeLimitError();
    void showMobileError();
    void showBloodTypeError();
    void showCountryError();
    void showStateError();
    void showCityError();
    void  showEmailError();
    void showPasswordError();

    String getCountyId();
    String getSateId();
    String getCityId();
    String getName();
    String getAge();
    String getMobile();
    String getBloodType();
    String getEmail();
    String getPassword();
    String getAddress();



}
