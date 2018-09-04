package hayah.hayah.dagger;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
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

}

