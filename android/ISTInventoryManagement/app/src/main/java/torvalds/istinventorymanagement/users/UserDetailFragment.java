package torvalds.istinventorymanagement.users;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.model.Student;

/**
 * Created by Hassan Jegan Ndow on 4/8/2017.
 */

public class UserDetailFragment extends Fragment {


    @BindView(R.id.user_detail_username) TextView username;
    @BindView(R.id.user_detail_uid) TextView uid;
    @BindView(R.id.user_detail_major_name) TextView majorName;
    @BindView(R.id.user_detail_class_name) TextView className;

    private Student student;

    public UserDetailFragment() {

    }

    public static UserDetailFragment newInstance(Student student) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.USER_KEY, student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(Constants.USER_KEY)) {
            student = (Student) getArguments().getSerializable(Constants.USER_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_detail, container, false);
        ButterKnife.bind(this, rootView);

        if (student != null) {
            this.username.setText(this.student.getUsername());
            this.uid.setText(String.valueOf(this.student.getUserId()));
            this.majorName.setText(this.student.getMajorTitle());
            this.className.setText(this.student.getClassName());
        }

        return rootView;
    }



}
