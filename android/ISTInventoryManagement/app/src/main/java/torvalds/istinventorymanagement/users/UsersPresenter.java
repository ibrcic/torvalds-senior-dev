package torvalds.istinventorymanagement.users;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import torvalds.istinventorymanagement.api.ISTInventoryClient;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by Hassan Jegan Ndow on 4/8/2017.
 */

public class UsersPresenter extends MvpBasePresenter<UsersView> {

    @Override
    public void attachView(UsersView view) {
        super.attachView(view);
        loadUsers();
    }

    public void loadUsers(){
        ISTInventoryClient.getApi().getStudentList().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                showUsers(response.body());
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("StudentsPresenter", "onFailure: " + t.getMessage());
            }
        });
    }

    private void showUsers(List<Student> students){
        if(isViewAttached()) {
            getView().showUsers(students);
        }
    }
}
