package torvalds.istinventorymanagement.checkinout.UserView;

import android.content.Context;
import android.util.AttributeSet;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;

import torvalds.istinventorymanagement.R;

/**
 * Custom view class representing selected user view,
 * decided to extract this as a custom view and add MVP logic to it
 * so it is easier to handle all of the events in  form of user slection, change, checkout etc...
 */
public class UserSection extends MvpRelativeLayout<UserSectionView, UserSectionPresenter> implements UserSectionView {

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
        inflate(getContext(), R.layout.section_user, this);
    }
}
