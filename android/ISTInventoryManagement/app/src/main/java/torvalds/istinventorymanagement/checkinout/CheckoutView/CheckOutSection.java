package torvalds.istinventorymanagement.checkinout.CheckoutView;

import android.content.Context;
import android.util.AttributeSet;

import com.hannesdorfmann.mosby.mvp.layout.MvpRelativeLayout;

import torvalds.istinventorymanagement.R;

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
