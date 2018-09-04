package hayah.hayah.apiClient;


import hayah.hayah.models.register.RegisterRequest;
import hayah.hayah.models.register.RegisterResponse;
import hayah.hayah.models.search.SearchRequest;
import hayah.hayah.models.search.SearchResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiInterface {

    @POST(EndPoints.REGISTER)
    Observable<RegisterResponse> registerObservable(@Body RegisterRequest registerRequest);

    @POST(EndPoints.SEARCH)
    Observable<SearchResponse> searchObservable(@Body SearchRequest request);










}
