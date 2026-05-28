
package papo.hun.papomanager.homebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.*;

import papo.hun.papomanager.Frame.WgBookTool;
import papo.hun.papomanager.Func.*;
import papo.hun.papomanager.R;

public class BookE04 extends Fragment {
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
        mView = inflater.inflate(R.layout.book_wg5d, container, false);
        //initVAR();
        
        prevBtn = mView.findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(view -> {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStackImmediate();
        });

        //mView.findViewById(R.id.mf1a).setOnClickListener(view -> fTool.openApp("heyhome"));
        return mView;
    }

    private void initVAR() {
        mActivity = getActivity();
        mContext = getContext();

        // menuIcon
        menuShowIcon = new ImageView[2];
        menuShowIcon[0] = mView.findViewById(R.id.mfI1);

        // menuLayout
        menu = new LinearLayout[2];
        menu[0] = mView.findViewById(R.id.mf1);

        // menuBtn
        menuShowBTN = new RelativeLayout[2];
        menuShowBTN[0] = mView.findViewById(R.id.mfb1);

        fTool = new FuncTool(mActivity, mView);
        conPref = new connPref(mContext);
        wgTool = new WgBookTool(mActivity, mView, menuShowIcon, menu, menuShowBTN);
        wgTool.setBookMenuLayout("E", 4);
        wgTool.setBookMenuLST("E", 4);
    }
}
