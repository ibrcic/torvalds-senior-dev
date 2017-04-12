package torvalds.istinventorymanagement.checkinout.CheckoutView;

import android.content.Context;
import android.util.AttributeSet;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;

import torvalds.istinventorymanagement.R;

/**
 * Custom view class representing selected user view,
 * decided to extract this as a custom view and add MVP logic to it
 * so it is easier to handle all of the events in  form of user slection, change, checkout etc...
 */
public class CheckOutSection extends MvpRelativeLayout<CheckOutSectionView, CheckOutSectionPresenter> implements CheckOutSectionView {

    public CheckOutSection(Context context) {
        super(context);
        init();
    }

    public CheckOutSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public CheckOutSectionPresenter createPresenter() {
        return new CheckOutSectionPresenter();
    }


    private void init() {
        inflate(getContext(), R.layout.section_checkout, this);
    }
}
