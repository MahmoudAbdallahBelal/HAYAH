package hayah.hayah.view.Country.countryList.city;


import java.util.List;

import hayah.hayah.baseClass.BaseView;
import hayah.hayah.models.city.CityResponse;
import hayah.hayah.models.country.CountryResponse;

//class created for register function for main view
public interface CityListView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void  showCityList(List<CityResponse> responseList);


}
