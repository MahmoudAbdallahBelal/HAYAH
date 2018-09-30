package hayah.hayah.dagger;


import javax.inject.Singleton;

import dagger.Component;
import hayah.hayah.view.Country.countryList.CountryListActivity;
import hayah.hayah.view.Country.countryList.CountryListPresenter;
import hayah.hayah.view.Country.countryList.city.CityListActivity;
import hayah.hayah.view.Country.countryList.city.CityListPresenter;
import hayah.hayah.view.Country.countryList.state.StateActivity;
import hayah.hayah.view.Country.countryList.state.StatesListPresenter;
import hayah.hayah.view.register.DonatorActivity;
import hayah.hayah.view.register.RegisterPresenter;
import hayah.hayah.view.search.PatientActivity;
import hayah.hayah.view.search.SearchPresenter;


// this class created for register who need inject
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class,PresenterModule.class})
public interface AppComponent {

    //register main activity it will need objects for injection
    void inject(DonatorActivity donatorActivity);
    //register LoginPresenter it will need objects for injection
    void inject(RegisterPresenter registerPresenter);


    void inject(PatientActivity patientActivity);
    //register LoginPresenter it will need objects for injection
    void inject(SearchPresenter searchPresenter);

    void inject(CountryListActivity countryListActivity);
    void inject(CountryListPresenter countryListPresenter);

    void inject(StateActivity stateActivity);
    void inject(StatesListPresenter statesListPresenter);

    void inject(CityListActivity cityListActivity);
    void inject(CityListPresenter cityListPresenter);


}
