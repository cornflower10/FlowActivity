package com.cornflower;

import android.support.v4.app.Fragment;

/**
 * Created by xiejingbao on 2018/6/20.
 */

public class BaseFragment extends Fragment implements FragmentBackHandler{

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}
