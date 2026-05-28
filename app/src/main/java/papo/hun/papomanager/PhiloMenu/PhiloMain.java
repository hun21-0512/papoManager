package papo.hun.papomanager.PhiloMenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.R;

public class PhiloMain extends Fragment {
    private Activity mActivity;
    private Context mContext;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.philo_index, container, false);

        ViewPager viewPager = mView.findViewById(R.id.viewPager);
        PhiloPager pagerAdapter = new PhiloPager(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);

        return mView;
    }
}