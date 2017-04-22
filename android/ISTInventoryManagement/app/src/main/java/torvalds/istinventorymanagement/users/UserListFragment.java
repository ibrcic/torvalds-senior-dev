package torvalds.istinventorymanagement.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;

import java.util.ArrayList;
import java.util.List;

import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.MainLoggedInView;
import torvalds.istinventorymanagement.MainViewType;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by Hassan Jegan Ndow on 4/8/2017.
 */

public class UserListFragment extends MvpFragment<UsersView, UsersPresenter> implements  UsersView {

    private  UserListAdapter listAdapter;

    public  UserListFragment(){

    }

    @Override
    public  UsersPresenter createPresenter(){ return new UsersPresenter();}

    public static UserListFragment newInstance() { return  new UserListFragment();}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view;
        this.listAdapter = new UserListAdapter();
        recyclerView.setAdapter(listAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        return  view;
    }

    @Override
    public void showUsers(List<Student> students) {
        this.listAdapter.updateUsers(students);
    }

    @Override
    public void showBorrowerSelectedConfirm() {
        Toast.makeText(getActivity(), R.string.borrower_selected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToCheckInOutTab() {
        ((MainLoggedInView)getActivity()).goToView(MainViewType.CHECKINOUT);
    }

    private class UserListAdapter extends  RecyclerView.Adapter<UserListAdapter.ViewHolder> {
        private List<Student> students;

        private UserListAdapter() {
            students = new ArrayList<>();
        }

        @Override
        public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, parent, false);
            return new UserListAdapter.ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(UserListAdapter.ViewHolder holder, int position) {
            Student student = students.get(position);

            holder.username.setText(student.getUsername());
            holder.uid.setText("UID: " + student.getUserId());

            holder.view.setOnClickListener(v -> {
                Intent i = new Intent(getActivity(), UserDetailActivity.class);
                i.putExtra(Constants.USER_KEY, student);
                startActivity(i);
            });

            holder.btnSelect.setOnClickListener(view -> presenter.selectClicked(student));


        }

        @Override
        public  int getItemCount() { return students == null ? 0 : students.size();}

        class ViewHolder extends  RecyclerView.ViewHolder {
            private final View view;
            private final TextView username;
            private final TextView uid;
            private  final Button btnSelect;

            private ViewHolder(View view){
                super(view);
                this.view = view;
                this.username = (TextView) view.findViewById(R.id.borrower_name);
                this.uid = (TextView) view.findViewById(R.id.uid);
                this.btnSelect = (Button) view.findViewById(R.id.btn_select);
            }
        }

        public void updateUsers(List<Student> students) {
            this.students = students;
            notifyDataSetChanged();
        }

    }
}
