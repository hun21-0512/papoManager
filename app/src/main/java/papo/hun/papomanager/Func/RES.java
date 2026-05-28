package papo.hun.papomanager.Func;

import androidx.fragment.app.Fragment;

import papo.hun.papomanager.R;
import papo.hun.papomanager.homebook.*;

public class RES {
    // SQL
    public static final String wgNameQ = "CREATE TABLE wgNames (widgetID INTEGER PRIMARY KEY, icon INTEGER, menu_name TEXT);";
    public static final String wgOrderQ = "CREATE TABLE wgOrders (widgetID INTEGER PRIMARY KEY, orderIx INTEGER);";
    public static final String papoDeviceQ = "CREATE TABLE papoDevice (serial_port INTEGER PRIMARY KEY, deviceName TEXT, deviceType TEXT, localip TEXT);";
    public static final String extAppsQ = "CREATE TABLE extApps (app_name TEXT PRIMARY KEY, package_name TEXT);";

    public static int[][] wgNames = {
        {R.drawable.icon_weather, R.string.sub_weatherTxt},
        {R.drawable.icon_aircycle, R.string.sub_circulationTxt},
        {R.drawable.icon_cleaning, R.string.sub_cleaningTxt},
        {R.drawable.icon_standlamp, R.string.sub_moodlampTxt},
        {R.drawable.icon_bgm, R.string.sub_bgmTxt},
        {R.drawable.icon_scentiment, R.string.sub_scentTxt},
        {R.drawable.icon_malinfection, R.string.sub_malInfection},
        {R.drawable.icon_electric, R.string.sub_batteryTxt},
        {R.drawable.icon_temp_monit, R.string.sub_tempMoistMonitoringTxt},
        {R.drawable.icon_nanodust, R.string.sub_microDustTxt},
    };

    public static int[] menuBtnIds = {
        R.id.m1a, R.id.m1b, R.id.m1c, R.id.m1d, R.id.m1e, R.id.m1f,
        R.id.m2a, R.id.m2b, R.id.m2c, R.id.m2d,
        R.id.m3a, R.id.m3b, R.id.m3c, R.id.m3d,
        R.id.m4a, R.id.m4b, R.id.m4c, R.id.m4d, R.id.m4e, R.id.m4f,
        R.id.m5a, R.id.m5b, R.id.m5c, R.id.m5d, R.id.m5e, R.id.m5f
    };

    public static Fragment[] books = {
        new BookA01(), new BookA02(), new BookA03(), new BookA04(), new BookA05(), new BookA06(),
        new BookB01(), new BookB02(), new BookB03(), new BookB04(),
        new BookC01(), new BookC02(), new BookC03(), new BookC04(),
        new BookD01(), new BookD02(), new BookD03(), new BookD04(), new BookD05(), new BookD06(),
        new BookE01(), new BookE02(), new BookE03(), new BookE04(), new BookE05(), new BookE06()
    };
}