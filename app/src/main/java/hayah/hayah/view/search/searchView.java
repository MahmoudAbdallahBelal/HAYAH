package hayah.hayah.view.search;


import hayah.hayah.baseClass.BaseView;
import hayah.hayah.models.search.SearchResponse;

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
