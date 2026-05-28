package papo.hun.papomanager.PhiloMenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import papo.hun.papomanager.Func.FuncT;
import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.R;

public class PhiloAI extends Fragment {
    private Activity mActivity;
    private Context mContext;
    private View mView;
    private FuncTool fTool;
    private FuncT fToolK;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.philo_ai, container, false);
        this.mActivity = getActivity();
        this.mContext = getContext();
        fTool = new FuncTool(mActivity, mView);
        fToolK = new FuncT(mActivity, mView);

        mView.findViewById(R.id.b1).setOnClickListener(view -> {
            fToolK.turnOnGPS();
            //fTool.senderNotify("테스트", "테스트 알림", R.drawable.menu_home1);
        });


        return mView;
    }
}