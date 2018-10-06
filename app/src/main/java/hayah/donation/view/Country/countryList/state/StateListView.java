package hayah.donation.view.Country.countryList.state;


import java.util.List;

import hayah.donation.baseClass.BaseView;
import hayah.donation.models.States.StatesResponse;

//class created for register function for main view
public interface StateListView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void  showStateList(List<StatesResponse> responseList);


}
