package hayah.donation.view.Country.countryList.country;


import java.util.List;

import hayah.donation.baseClass.BaseView;
import hayah.donation.models.country.CountryResponse;

//class created for register function for main view
public interface CountryListView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void  showCountryList(List<CountryResponse> responseList);


}
