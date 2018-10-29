package hayah.donation.dagger;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hayah.donation.view.Country.countryList.country.CountryListPresenter;
import hayah.donation.view.Country.countryList.city.CityListPresenter;
import hayah.donation.view.Country.countryList.state.StatesListPresenter;
import hayah.donation.view.donator_profile.ProfilePresenter;
import hayah.donation.view.login.LoginPresenter;
import hayah.donation.view.register.RegisterPresenter;
import hayah.donation.view.search.SearchPresenter;


//this class created to put  providers for Presenters

@Module
public class PresenterModule {


    @Provides
    @Singleton
    RegisterPresenter provideRegisterPresenter(Context context) {

        return new RegisterPresenter(context);
    }


    @Provides
    @Singleton
    SearchPresenter provideSearchPresenter(Context context) {

        return new SearchPresenter(context);
    }


    @Provides
    @Singleton
    CountryListPresenter provideCountryListPresenter(Context context) {

        return new CountryListPresenter(context);
    }


    @Provides
    @Singleton
    StatesListPresenter provideStatesListPresenter(Context context) {

        return new StatesListPresenter(context);
    }

    @Provides
    @Singleton
    CityListPresenter provideCityListPresenter(Context context) {

        return new CityListPresenter(context);
    }

    @Provides
    @Singleton
    LoginPresenter provideLoginPresenter(Context context) {

        return new LoginPresenter(context);
    }



    @Provides
    @Singleton
    ProfilePresenter provideProfilePresenter(Context context) {

        return new ProfilePresenter(context);
    }

}

