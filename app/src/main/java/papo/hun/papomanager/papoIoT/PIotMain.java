package papo.hun.papomanager.papoIoT;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import papo.hun.papomanager.Func.FuncT;
import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.PhiloMenu.PhiloPager;
import papo.hun.papomanager.R;

public class PIotMain extends Fragment {
    public static ViewPager viewPager;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.piot_index, container, false);
        viewPager = mView.findViewById(R.id.viewPager);
        PIotPager pagerAdapter = new PIotPager(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
        return mView;
    }
}