package pe.edu.cibertec.retrofitgitflow.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostFirestoreRepository;
import pe.edu.cibertec.retrofitgitflow.data.repository.IPostRepository;
import pe.edu.cibertec.retrofitgitflow.di.scope.PerActivity;
import pe.edu.cibertec.retrofitgitflow.domain.create_post_interactor.CreatePostInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.create_post_interactor.ICreatePostInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.IMainInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.main_interactor.MainInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.IPostDetailInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.post_detail_interactor.PostDetailInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.domain.post_firestore_interactor.IPostFirestoreInteractor;
import pe.edu.cibertec.retrofitgitflow.domain.post_firestore_interactor.PostFirestoreInteractorImpl;
import pe.edu.cibertec.retrofitgitflow.network.JsonPlaceHolderApi;
import retrofit2.Retrofit;

@Module
public class PresentationModule {


    @PerActivity
    @Provides
    IMainInteractor provideMainInteractor(IPostRepository iPostRepository,
                                          @Named("ui_thread") Scheduler uiThread,
                                          @Named("executor_thread") Scheduler executorThread){
        return new MainInteractorImpl(iPostRepository, uiThread, executorThread);
    }

    @PerActivity
    @Provides
    IPostDetailInteractor providePostDetailInteractor(IPostRepository iPostRepository,
                                                      @Named("ui_thread") Scheduler uiThread,
                                                      @Named("executor_thread") Scheduler executorThread){
        return new PostDetailInteractorImpl(iPostRepository,uiThread,executorThread);
    }

    @PerActivity
    @Provides
    IPostFirestoreInteractor providesPostFirestoreInteractor(IPostFirestoreRepository repository){
        return  new PostFirestoreInteractorImpl(repository);
    }

    @PerActivity
    @Provides
    ICreatePostInteractor provideCreatePostInteractor(IPostFirestoreRepository repository){
        return new CreatePostInteractorImpl(repository);
    }
}
