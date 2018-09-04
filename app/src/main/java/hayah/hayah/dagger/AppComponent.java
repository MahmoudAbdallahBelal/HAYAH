package hayah.hayah.dagger;


import javax.inject.Singleton;

import dagger.Component;
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



}
