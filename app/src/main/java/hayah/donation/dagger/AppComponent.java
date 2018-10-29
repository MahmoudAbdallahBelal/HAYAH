package hayah.donation.dagger;


import javax.inject.Singleton;

import dagger.Component;
import hayah.donation.view.Country.countryList.country.CountryListActivity;
import hayah.donation.view.Country.countryList.country.CountryListPresenter;
import hayah.donation.view.Country.countryList.city.CityListActivity;
import hayah.donation.view.Country.countryList.city.CityListPresenter;
import hayah.donation.view.Country.countryList.state.StateActivity;
import hayah.donation.view.Country.countryList.state.StatesListPresenter;
import hayah.donation.view.donator_profile.DonstorProfileActivity;
import hayah.donation.view.donator_profile.ProfilePresenter;
import hayah.donation.view.login.LoginActivity;
import hayah.donation.view.login.LoginPresenter;
import hayah.donation.view.register.DonatorActivity;
import hayah.donation.view.register.RegisterPresenter;
import hayah.donation.view.search.PatientActivity;
import hayah.donation.view.search.SearchPresenter;


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

    void inject(LoginActivity loginActivity);
    void inject(LoginPresenter loginPresenter);


    void inject(DonstorProfileActivity donstorProfileActivity);
    void inject(ProfilePresenter profilePresenter);

}
