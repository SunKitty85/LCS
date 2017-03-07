package com.local.moltox.lcs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class qanda_activityFragment extends Fragment {

    public qanda_activityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_qanda_activity, container, false);
    }

/*
    private void test()  {
        String test = DBHelperClass.lcs_db.DATABASE_NAME;
    }
*/


}
