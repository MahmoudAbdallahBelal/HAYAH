package hayah.donation.view.Country.countryList.city;


import java.util.List;

import hayah.donation.baseClass.BaseView;
import hayah.donation.models.city.CityResponse;

//class created for register function for main view
public interface CityListView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void  showCityList(List<CityResponse> responseList);


}
