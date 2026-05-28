package papo.hun.papomanager.Func;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NetTool {
    private String localURL;
    private View mView;
    private Activity mActivity;

    public NetTool(Activity mActivity, View mView) {
        this.mActivity = mActivity;
        this.mView = mView;
        //getDeviceInfo();
        //getDeviceAddress();
    }

    public JSONObject connNetwork(String url) {
        return connNetwork(url, "GET", null);
    }

    public JSONObject connNetwork(String url, String method, JSONObject jsonParm) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<JSONObject> callableTask = () -> {
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con = setHTTP(con, method, jsonParm);
                con.getResponseCode();

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject myResponse = new JSONObject(response.toString());
                return myResponse;
            } catch (Exception e) {
                Log.e("jsonErr", e.toString());
            }
            return null;
        };

        Future<JSONObject> futureResult = executorService.submit(callableTask);
        JSONObject res = null;
        try {
            res = futureResult.get();
        } catch (InterruptedException | ExecutionException e) {}
        finally {
            executorService.shutdown();
        }
        return res;
    }

    public HttpURLConnection setHTTP(HttpURLConnection con, String method, JSONObject jsonParm) {
        try{
            if (method.toUpperCase() == "GET") {
                con.setRequestMethod("GET");
                return con;
            } else if (method.toUpperCase() == "POST") {
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");
                con.setDoOutput(true);

                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonParm.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
                return con;
            }
        } catch (Exception e) {}
        return con;
    }

    public void connWIFI(String ssid, String password, int port) {
        try {
            String url = String.format("http://192.168.4.1:%d/submit", port);
            JSONObject initRequest = new JSONObject();
            initRequest.put("ssid", ssid);
            initRequest.put("password", password);
            connNetwork(url, "POST", initRequest);
        } catch (Exception e) {}
    }

    // Preference
    public void getDeviceInfo(int port) {
        connNetwork("http://192.168.4.1/info");
    }

    public void getDeviceAddress() {
        JSONObject result = connNetwork("http://192.168.4.1:1000/address");
        if (result != null) {
            try {
                String localAD = result.getString("status");
            } catch (JSONException e) {}
        }
    }

    public String getStatus() {
        JSONObject res = connNetwork("http://119.200.86.68:1000/stat");
        String stat = "-";
        try {
            stat = res.getString("status");
            //statTextVw.setText(stat);
        } catch (JSONException e) {
            Log.e("jsonErr", e.toString());
        }
        return stat;
    }

    public String getGlobalIP() {
        JSONObject res = connNetwork("https://ipinfo.io/?callback=");
        String stat = "-";
        try {
            stat = res.getString("ip");
        } catch (JSONException e) {
            Log.e("jsonErr", e.toString());
        }
        return stat;
    }

    public void init() {
        connNetwork("http://192.168.0.3:1000/submit", "POST", null);
    }

    public boolean turnON_Global() {
        connNetwork("http://119.200.86.68:1000/on");
        getStatus();
        return false;
    }

    public boolean turnOFF_Global() {
        connNetwork("http://119.200.86.68:1000/off");
        getStatus();
        return false;
    }

    public boolean toggle_Global() {
        connNetwork("http://119.200.86.68:1000/toggle");
        getStatus();
        return false;
    }

    public void timer_Global() {
        //int delayT = Integer.parseInt(String.valueOf(timerV.getText()));
        String statF = "";
        if (getStatus()=="on") {
            statF = "off";
        } else {
            statF = "on";
        }
        //Toast.makeText(mView.getContext(), String.format("%d분 후에 %s됩니다", delayT, statF), Toast.LENGTH_SHORT);
        //new Handler().postDelayed(() -> toggle_Global(), 1000 * 60 * delayT);
    }

    public boolean toggle() {
        connNetwork("http://192.168.0.3:1000/toggle");
        getStatus();
        return true;
    }

    public boolean turnON() {
        connNetwork("http://192.168.0.3:1000/on");
        getStatus();
        return true;
    }

    public boolean turnOFF() {
        connNetwork("http://192.168.0.3:1000/off");
        getStatus();
        return true;
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
}