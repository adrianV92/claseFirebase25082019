package pe.edu.cibertec.retrofitgitflow.presentation.activities.create_post_firestore;

import android.text.TextUtils;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import pe.edu.cibertec.retrofitgitflow.data.entities.NewPost;
import pe.edu.cibertec.retrofitgitflow.domain.create_post_interactor.ICreatePostInteractor;

public class CreatePostFirestorePresenter implements ICreatePostFirestoreContract.IPresenter {

    ICreatePostFirestoreContract.IView view;
    @Inject
    FirebaseAuth firebaseAuth;
    @Inject
    ICreatePostInteractor interactor;

    @Inject
    public CreatePostFirestorePresenter() {
    }

    @Override
    public void attachView(ICreatePostFirestoreContract.IView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public boolean isViewAttached() {
        return view != null;
    }

    @Override
    public void createPost(String title, String content, String path) {
        view.showProgressDialog();
        if(TextUtils.isEmpty(title) || content.isEmpty()){
            if(isViewAttached()) view.showError("Ingrese datos porfavor");
            return;
        }


        NewPost post = new NewPost();
        post.setTitle(title);
        post.setContent(content);
        post.setUserUid(firebaseAuth.getUid());
        interactor.createPost(post, task->{
            if (isViewAttached()){
                view.hideProgressDialog();
                if (task.isSuccessful()){
                    view.onSuccessCreate();
                }else{
                    view.showError(task.getException().getMessage());
                }
            }
        });
    }


}
