package papo.hun.papomanager.papoIoT;

import android.app.Activity;
import android.content.Context;
import android.os.*;
import android.view.*;
import android.widget.*;

import androidx.fragment.app.Fragment;
import papo.hun.papomanager.Func.*;
import papo.hun.papomanager.*;

public class PIotRegit extends Fragment {
    private Activity mActivity;
    private Context mContext;
    private View mView;
    private FuncTool fTool;
    private FuncT fToolK;
    private WifiScanReceiver wifiRCV;

    // widget
    private int menu = -1;
    private LinearLayout[] deviceTypeBtns;
    private EditText device_type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.piot_regit, container, false);
        initVAR();

        fTool.gpsPermission();
        fToolK.turnOnGPS();

        //new Handler().postDelayed(() -> wifiRCV.initSET(), 1000);
        mView.findViewById(R.id.find_IOTssid).setOnClickListener(view -> {
            wifiRCV.scanWifiNetworks(1);
        });

        mView.findViewById(R.id.findSSID).setOnClickListener(view -> {
            wifiRCV.scanWifiNetworks(2);
        });

        mView.findViewById(R.id.regitBtnT).setOnClickListener(view -> {
            PIotMain.viewPager.setCurrentItem(1);
            Toast.makeText(getContext(), R.string.netExt_RegitMsg, Toast.LENGTH_SHORT).show();
        });

        deviceTypeBtns[0].setOnClickListener(view -> {
            chooseDevice(0);
        });
        deviceTypeBtns[1].setOnClickListener(view -> {
            chooseDevice(1);
        });
        deviceTypeBtns[2].setOnClickListener(view -> {
            chooseDevice(2);
        });
        deviceTypeBtns[3].setOnClickListener(view -> {
            chooseDevice(3);
        });
        deviceTypeBtns[4].setOnClickListener(view -> {
            chooseDevice(4);
        });

        TextView wifiSSID = mView.findViewById(R.id.wifiSSID);
        wifiSSID.setText(fTool.getWifiInfo());

        return mView;
    }

    private void initVAR() {
        mActivity = getActivity();
        mContext = getContext();
        fTool = new FuncTool(mActivity, mView);
        fToolK = new FuncT(mActivity, mView);
        wifiRCV = new WifiScanReceiver(mActivity, mView);

        device_type = mView.findViewById(R.id.device_type);
        deviceTypeBtns = new LinearLayout[5];
        deviceTypeBtns[0] = mView.findViewById(R.id.b1);
        deviceTypeBtns[1] = mView.findViewById(R.id.b2);
        deviceTypeBtns[2] = mView.findViewById(R.id.b3);
        deviceTypeBtns[3] = mView.findViewById(R.id.b4);
        deviceTypeBtns[4] = mView.findViewById(R.id.b5);
    }

    private void chooseDevice(int n) {
        menu = n;
        device_type.setVisibility(View.GONE);
        if (n==4) {
            device_type.setVisibility(View.VISIBLE);
        }
        for (int i=0; i<deviceTypeBtns.length; i++) {
            deviceTypeBtns[i].setBackground(mActivity.getDrawable(R.drawable.widget4));
        }
        deviceTypeBtns[n].setBackground(mActivity.getDrawable(R.drawable.widget5));
    }
}