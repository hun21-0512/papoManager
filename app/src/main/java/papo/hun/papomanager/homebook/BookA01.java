package papo.hun.papomanager.homebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import papo.hun.papomanager.Frame.WgBookTool;
import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.Func.connPref;
import papo.hun.papomanager.R;

public class BookA01 extends Fragment {
    private ImageView prevBtn;
    private Activity mActivity;
    private Context mContext;
    private View mView;

    private FuncTool fTool;
    private WgBookTool wgTool;
    private connPref conPref;
    private LinearLayout[] menu;
    private RelativeLayout[] menuShowBTN;
    private ImageView[] menuShowIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.book_wg1a, container, false);
        initVAR();
        
        prevBtn = mView.findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(view -> {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStackImmediate();
        });

        mView.findViewById(R.id.mf1a).setOnClickListener(view -> fTool.openApp("heyhome"));
        mView.findViewById(R.id.mf1b).setOnClickListener(view -> fTool.openApp("minibig"));
        mView.findViewById(R.id.mf2a).setOnClickListener(view -> fTool.openApp("heyhome"));
        mView.findViewById(R.id.mf2b).setOnClickListener(view -> fTool.openApp("smartThings"));
        return mView;
    }

    private void initVAR() {
        mActivity = getActivity();
        mContext = getContext();

        // menuIcon
        menuShowIcon = new ImageView[2];
        menuShowIcon[0] = mView.findViewById(R.id.mfI1);
        menuShowIcon[1] = mView.findViewById(R.id.mfI2);

        // menuLayout
        menu = new LinearLayout[2];
        menu[0] = mView.findViewById(R.id.mf1);
        menu[1] = mView.findViewById(R.id.mf2);

        // menuBtn
        menuShowBTN = new RelativeLayout[2];
        menuShowBTN[0] = mView.findViewById(R.id.mfb1);
        menuShowBTN[1] = mView.findViewById(R.id.mfb2);

        fTool = new FuncTool(mActivity, mView);
        conPref = new connPref(mContext);
        wgTool = new WgBookTool(mActivity, mView, menuShowIcon, menu, menuShowBTN);
        wgTool.setBookMenuLayout("A", 0);
        wgTool.setBookMenuLST("A", 0);
        wgTool.setBookMenuLayout("A", 1);
        wgTool.setBookMenuLST("A", 1);
    }
}
