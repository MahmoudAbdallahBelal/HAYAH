package hayah.hayah.apiClient;


import java.util.List;

import hayah.hayah.models.States.StatesResponse;
import hayah.hayah.models.city.CityResponse;
import hayah.hayah.models.country.CountryResponse;
import hayah.hayah.models.register.RegisterRequest;
import hayah.hayah.models.register.RegisterResponse;
import hayah.hayah.models.search.SearchRequest;
import hayah.hayah.models.search.SearchResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiInterface {

    @POST(EndPoints.REGISTER)
    Observable<RegisterResponse> registerObservable(@Body RegisterRequest registerRequest);

    @POST(EndPoints.SEARCH)
    Observable<SearchResponse> searchObservable(@Body SearchRequest request);


    @GET(EndPoints.COUNTRIES)
    Observable<List<CountryResponse>> getCountriesObservable();

    @GET(EndPoints.STATES)
    Observable<List<StatesResponse>> getStatesObservable(@Path("id") String id);


    @GET(EndPoints.CITY)
    Observable<List<CityResponse>> getCityObservable(@Path("id") String id);






}
