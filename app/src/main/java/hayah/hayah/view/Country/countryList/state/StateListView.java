package hayah.hayah.view.Country.countryList.state;


import java.util.List;

import hayah.hayah.baseClass.BaseView;
import hayah.hayah.models.States.StatesResponse;
import hayah.hayah.models.country.CountryResponse;

//class created for register function for main view
public interface StateListView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void  showStateList(List<StatesResponse> responseList);


}
