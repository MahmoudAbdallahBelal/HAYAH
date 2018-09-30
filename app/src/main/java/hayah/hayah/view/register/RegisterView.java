package hayah.hayah.view.register;


import java.util.List;

import hayah.hayah.baseClass.BaseView;
import hayah.hayah.models.country.CountryResponse;

//class created for register function for main view
public interface RegisterView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void showNameError();
    void showAgeError();
    void showAgeLimitError();

    void showAddressError();
    void showMobileError();
    void showBloodTypeError();








    String getName();

    String getAge();

    String getAddress();
    String getAddress2();

    String getMobile();
    String getMobile2();

    String getBloodType();



}
