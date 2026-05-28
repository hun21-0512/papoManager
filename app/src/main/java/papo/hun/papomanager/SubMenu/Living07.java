
package papo.hun.papomanager.SubMenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.fragment.app.*;
import papo.hun.papomanager.Func.*;
import papo.hun.papomanager.R;

public class Living07 extends Fragment {
    private ImageView prevBtn;
    private Activity mActivity;
    private Context mContext;
    private View mView;
    private connPref conPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_malinfection, container, false);
        initVAR();
        
        prevBtn = mView.findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(view -> {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStackImmediate();
        });
        return mView;
    }

    private void initVAR() {
        mActivity = getActivity();
        mContext = getContext();
        conPref = new connPref(mContext);
    }
}
