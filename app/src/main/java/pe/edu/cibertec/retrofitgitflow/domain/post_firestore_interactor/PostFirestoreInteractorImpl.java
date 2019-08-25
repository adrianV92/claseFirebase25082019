package pe.edu.cibertec.retrofitgitflow.domain.post_firestore_interactor;

import com.google.firebase.firestore.Query;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.repository.IPostFirestoreRepository;

public class PostFirestoreInteractorImpl implements IPostFirestoreInteractor {

    private final IPostFirestoreRepository repository;

    @Inject
    public PostFirestoreInteractorImpl(IPostFirestoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Query getAllPosts() {
        return repository.getAllPosts();
    }
}
