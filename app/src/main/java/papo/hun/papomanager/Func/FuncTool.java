package papo.hun.papomanager.Func;

import android.Manifest;
import android.app.*;
import android.content.*;
import android.content.pm.PackageManager;
import android.net.*;
import android.net.wifi.*;
import android.os.Build;
import android.util.*;
import android.view.View;

import java.time.LocalDateTime;
import java.util.*;

import androidx.core.app.*;
import androidx.core.content.ContextCompat;

public class FuncTool {
    private View mView;
    private Activity mActivity;
    private Context mContext;
    private DBTool dbTool;

    public FuncTool (Activity mActivity, View mView) {
        this.mView = mView;
        this.mActivity = mActivity;
        this.mContext = mActivity.getApplicationContext();
        dbTool = new DBTool(mContext);
    }

    public void openApp(String app_name) {
        try {
            String rApp_name = app_name.toLowerCase().replace(" ", "");
            String appDomain = dbTool.getApp_Package(rApp_name);
            if (appDomain==null)
                appDomain = subPackageFinder(rApp_name);
            Intent mIntent = mActivity.getPackageManager().getLaunchIntentForPackage(appDomain);
            mActivity.startActivity(mIntent);
        } catch (Exception e) {
            Log.e("var1234", e.toString());
        }
    }

    private String subPackageFinder(String app_name) {
        if (app_name.equals("googlehome")) {
            return "com.google.android.apps.chromecast.app";
        } else if (app_name.equals("googletv")) {
            return "com.google.android.videos";
        } else if (app_name.equals("smartthings")) {
            return "com.samsung.android.oneconnect";
        } else if (app_name.equals("minibig")) {
            return "com.minibigap";
        } else if (app_name.equals("heyhome")) {
            return "com.goqual";
        }
        return null;
    }

    public void checkAndRequestPermissions() {
        List<String> permissionsNeeded = new ArrayList<>();
        Context ctxt = mView.getContext();
        if (ContextCompat.checkSelfPermission(ctxt, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(ctxt, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(ctxt, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_WIFI_STATE);
        }
        if (ContextCompat.checkSelfPermission(ctxt, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.CHANGE_WIFI_STATE);
        }
        if (ContextCompat.checkSelfPermission(ctxt, Manifest.permission.CHANGE_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.CHANGE_NETWORK_STATE);
        }
        if (ContextCompat.checkSelfPermission(ctxt, Manifest.permission.WRITE_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.WRITE_SETTINGS);
        }

        if (!permissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(mActivity, permissionsNeeded.toArray(new String[0]), 1);
        }
    }

    public void receiverNotify() {

    }

    public void senderNotify(String title, String message, int icon) {
        int notifyID = (int)(Math.random()*100000000);
        String channelID = String.format("%s_%d",
                LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyDDMM_HHmmss")), notifyID);

        nofityPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelID, channelID, importance);

            NotificationManager notificationManager = mActivity.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        NotificationManager mNotificationManager = (NotificationManager) mActivity.getSystemService(mActivity.NOTIFICATION_SERVICE);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelID,channelID,mNotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(channelID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(mContext, channelID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(icon);

        mNotificationManager.notify(notifyID, notifyBuilder.build());
    }

    public void nofityPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    public boolean gpsPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(mContext, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE
                }, 1);
                return false;
            }
        }
        return true;
    }

    public String getWifiInfo() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

        // Android 10 (Q) 이상에서는 ConnectivityManager 사용
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (networkInfo != null && networkInfo.isConnected()) {
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ssid = wifiInfo.getSSID().replace("\"", "");
                String bssid = wifiInfo.getBSSID();
                int ipAddress = wifiInfo.getIpAddress();
                int linkSpeed = wifiInfo.getLinkSpeed(); // Wi-Fi 속도 (Mbps)
                return ssid;
            }
        } else {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID().replace("\"", "");;
            String bssid = wifiInfo.getBSSID();
            int ipAddress = wifiInfo.getIpAddress();
            int linkSpeed = wifiInfo.getLinkSpeed();
            return ssid;
        }
        return null;
    }

}