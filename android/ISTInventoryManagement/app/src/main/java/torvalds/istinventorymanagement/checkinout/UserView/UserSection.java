package torvalds.istinventorymanagement.checkinout.UserView;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import torvalds.istinventorymanagement.Constants;
import torvalds.istinventorymanagement.R;
import torvalds.istinventorymanagement.SimpleScannerActivity;
import torvalds.istinventorymanagement.login.BarcodeScanDialog;
import torvalds.istinventorymanagement.model.Student;

/**
 * Custom view class representing selected user view,
 * decided to extract this as a custom view and add MVP logic to it
 * so it is easier to handle all of the events in  form of user slection, change, checkout etc...
 */
public class UserSection extends MvpRelativeLayout<UserSectionView, UserSectionPresenter> implements UserSectionView {

    @BindView(R.id.container_select_user) ViewGroup containerSelectUser;
    @BindView(R.id.student_container) ViewGroup studentContainer;
    @BindView(R.id.tv_major) TextView tvMajor;
    @BindView(R.id.tv_uid) TextView tvUid;
    @BindView(R.id.tv_student_name) TextView tvStudentName;
    @BindView(R.id.et_student_uid) MaterialEditText etStudentUid;

    public UserSection(Context context) {
        super(context);
        init();
    }

    public UserSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public UserSectionPresenter createPresenter() {
        return new UserSectionPresenter();
    }


    private void init() {
        View v = inflate(getContext(), R.layout.section_user, this);
        ButterKnife.bind(v);
    }

    @Override
    public void setStudent(Student student) {
        containerSelectUser.setVisibility(GONE);
        studentContainer.setVisibility(VISIBLE);
        tvStudentName.setText(student.getUsername());
        tvMajor.setText(student.getMajorTitle());
        tvUid.setText(student.getUserId() + "");
    }

    @Override
    public void showNoUserError() {
        Toast.makeText(getContext(), R.string.no_user_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showScanBarcodeView() {
        Intent i = new Intent(getContext(), SimpleScannerActivity.class);
        i.putExtra(Constants.SCAN_TYPE_KEY, Constants.ScanType.STUDENT);
        getContext().startActivity(i);
    }

    @OnClick({R.id.btn_scan_id, R.id.btn_login_using_credentials, R.id.btn_go})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_id:
                presenter.btnScanIdClicked();
                break;
            case R.id.btn_login_using_credentials:
                break;
            case R.id.btn_go:
                presenter.btnGoClicked(etStudentUid.getText().toString());
                hideKeyboard();
                break;
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
