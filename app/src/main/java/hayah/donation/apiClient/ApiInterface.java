package hayah.donation.apiClient;


import java.util.List;

import hayah.donation.models.States.StatesResponse;
import hayah.donation.models.city.CityResponse;
import hayah.donation.models.country.CountryResponse;
import hayah.donation.models.login.LoginRequest;
import hayah.donation.models.login.LoginResponse;
import hayah.donation.models.register.RegisterRequest;
import hayah.donation.models.register.RegisterResponse;
import hayah.donation.models.search.SearchRequest;
import hayah.donation.models.search.SearchResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface ApiInterface {

    @POST(EndPoints.REGISTER)
    Observable<RegisterResponse> registerObservable(@Body RegisterRequest registerRequest);

    @POST(EndPoints.LOGIN)
    Observable<LoginResponse> loginObservable(@Body LoginRequest loginRequest);




    @POST(EndPoints.SEARCH)
    Observable<SearchResponse> searchObservable(@Body SearchRequest request);


    @GET(EndPoints.COUNTRIES)
    Observable<List<CountryResponse>> getCountriesObservable();

    @GET(EndPoints.STATES)
    Observable<List<StatesResponse>> getStatesObservable(@Path("id") String id);


    @GET(EndPoints.CITY)
    Observable<List<CityResponse>> getCityObservable(@Path("id") String id);






}
