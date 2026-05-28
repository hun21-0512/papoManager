package papo.hun.papomanager.Func;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.*;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.*;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import android.util.*;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import java.util.*;

import papo.hun.papomanager.R;

public class WifiScanReceiver extends BroadcastReceiver {
    private AlertDialog loadingD;
    private WifiManager wifiManager;
    private ArrayAdapter<String> wifiListAdapter;
    private WifiScanReceiver wifiScanReceiver;

    private Activity mActivity;
    private Context mContext;
    private View mView;
    private int opt = 2;

    public WifiScanReceiver(Activity mActivity, View mView) {
        wifiManager = (WifiManager) mActivity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        this.mContext = mActivity.getApplicationContext();
        this.mActivity = mActivity;
        this.mView = mView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false);
        loadingD.dismiss();
        if (success) {
            mActivity.runOnUiThread(() -> getScanResults());
        } else {
            Log.e("WifiConnect", "WiFi scan did not succeed.");
        }
    }

    public void loading() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_loading, null);
        builder.setView(dialogView);
        loadingD = builder.create();
        loadingD.setCancelable(false);
        loadingD.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingD.show();
    }

    private List<String> reListingWIFI(List<String> wifiList) {
        if (opt == 1 || opt == 10) {
            List<String> tmpWIFI = new ArrayList<>();
            for (int i=0; i<wifiList.size(); i++) {
                String tmp = wifiList.get(i);
                if (tmp.toLowerCase().startsWith("papo")) {
                    tmpWIFI.add(tmp);
                }
            }
            return tmpWIFI;
        } else {
            return wifiList;
        }
    }

    private void getScanResults() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        List<ScanResult> results = wifiManager.getScanResults();
        List<String> wifiList = new ArrayList<>();
        for (ScanResult result : results) {
            if (result.SSID.length() > 1) {
                wifiList.add(result.SSID);
            }
        }

        if (opt == 10) {
            String var1 = reListingWIFI(wifiList).get(0);
            if (var1 != null) {
                EditText pSSID = mActivity.findViewById(R.id.productSSID);
                pSSID.setText(var1);
            }
        } else {
            showWifiPopup(reListingWIFI(wifiList));
        }
    }

    private void showWifiPopup(List<String> wifiList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_wifilist, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        ListView wifiListView = dialogView.findViewById(R.id.wifiListView);
        wifiListAdapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_list_item_1, wifiList);
        wifiListView.setAdapter(wifiListAdapter);

        if (wifiList.size() == 0) {
            dialogView.findViewById(R.id.msgTxt).setVisibility(View.VISIBLE);
        }

        wifiListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = wifiListAdapter.getItem(position);
            if (opt == 1) {
                EditText pSSID = mActivity.findViewById(R.id.productSSID);
                pSSID.setText(selectedItem);
            } else {
                EditText ssid = mActivity.findViewById(R.id.wifiSSID);
                ssid.setText(selectedItem);
            }

            dialog.dismiss();
        });

        dialogView.findViewById(R.id.closeBtn).setOnClickListener(view -> {
            dialog.dismiss();
        });

        dialog.show();
    }

    public void initSET() {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        loading();
        this.opt = 10;
        mActivity.registerReceiver(this, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void scanWifiNetworks(int opt) {
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        this.opt = opt;
        loading();
        mActivity.registerReceiver(this, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        boolean success = wifiManager.startScan();
        if (!success) {
            Toast.makeText(mContext, "WiFi scan failed. Try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}