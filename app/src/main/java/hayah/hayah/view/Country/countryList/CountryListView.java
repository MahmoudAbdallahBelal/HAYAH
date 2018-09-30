package hayah.hayah.view.Country.countryList;


import java.util.List;

import hayah.hayah.baseClass.BaseView;
import hayah.hayah.models.country.CountryResponse;

//class created for register function for main view
public interface CountryListView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void  showCountryList(List<CountryResponse> responseList);


}
