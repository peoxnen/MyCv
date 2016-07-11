package iview.wsienski.mycv.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iview.wsienski.mycv.R;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class TopFragment extends Fragment {

    private static final String TAG = TopFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        return inflater.inflate(R.layout.fragment_education, container, false);
    }
}
