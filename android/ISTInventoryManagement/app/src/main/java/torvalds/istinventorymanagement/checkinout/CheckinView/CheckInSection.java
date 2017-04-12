package torvalds.istinventorymanagement.checkinout.CheckinView;

import android.content.Context;
import android.util.AttributeSet;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;

import torvalds.istinventorymanagement.R;

/**
 * Custom view class representing selected user view,
 * decided to extract this as a custom view and add MVP logic to it
 * so it is easier to handle all of the events in  form of user slection, change, checkout etc...
 */
public class CheckInSection extends MvpRelativeLayout<CheckInSectionView, CheckInSectionPresenter> implements CheckInSectionView {

    public CheckInSection(Context context) {
        super(context);
        init();
    }

    public CheckInSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public CheckInSectionPresenter createPresenter() {
        return new CheckInSectionPresenter();
    }


    private void init() {
        inflate(getContext(), R.layout.section_checkin, this);
    }
}
