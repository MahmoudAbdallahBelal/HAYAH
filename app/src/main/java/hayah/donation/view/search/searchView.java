package hayah.donation.view.search;


import hayah.donation.baseClass.BaseView;
import hayah.donation.models.search.SearchResponse;

//class created for register function for main view
public interface searchView extends BaseView {
    void showLoading();
    void hideLoading();

    void showErrorMessage(String message);
    void showSuccessMessage(String message);

    void showCityError(String cityError);


    void showSearchResponse(SearchResponse searchResponse);



    String getCity();

    String getBloodType();



}
