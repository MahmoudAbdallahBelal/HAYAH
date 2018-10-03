package hayah.hayah.dagger;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hayah.hayah.view.Country.countryList.country.CountryListPresenter;
import hayah.hayah.view.Country.countryList.city.CityListPresenter;
import hayah.hayah.view.Country.countryList.state.StatesListPresenter;
import hayah.hayah.view.register.RegisterPresenter;
import hayah.hayah.view.search.SearchPresenter;


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

}

