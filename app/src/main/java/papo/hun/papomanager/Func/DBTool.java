package papo.hun.papomanager.Func;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import android.util.Pair;
import java.util.*;

public class DBTool extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "papo.db";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    public DBTool(Context mContext) {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = mContext;
        initDB_Apps();
        initDB_wgNames();
        initDB_wgOrderLayout();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RES.wgNameQ);
        db.execSQL(RES.wgOrderQ);
        db.execSQL(RES.extAppsQ);
        db.execSQL(RES.papoDeviceQ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS wgNames");
        db.execSQL("DROP TABLE IF EXISTS wgOrders");
        db.execSQL("DROP TABLE IF EXISTS extApps");
        db.execSQL("DROP TABLE IF EXISTS papoDevice");
        onCreate(db);
    }

    public boolean isTableExist(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[]{tableName});
        boolean tableExists = (cursor.getCount() > 0);
        cursor.close();
        return tableExists;
    }

    public int getRowCount(String tableName) {
        String sql = String.format("SELECT COUNT(*) FROM %s", tableName);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }

    public void editLayoutORDER(int widgetID, int orderIx) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("widgetID", orderIx);
        db.update("wgOrders", values, "widgetID = ?", new String[]{String.valueOf(widgetID)});
        db.close();
    }

    public void initDB_wgNames() {
        if (getRowCount("wgNames") < 1) {
            for (int wgID=0; wgID < RES.wgNames.length; wgID++) {
                addNames(wgID, RES.wgNames[wgID][0], RES.wgNames[wgID][1]);
            }
        }
    }

    public void initDB_wgOrderLayout() {
        if (getRowCount("wgOrders") < 1) {
            for (int wgID=0; wgID < RES.wgNames.length; wgID++) {
                addOrders(wgID, wgID);
            }
        }
    }

    public void initDB_Apps() {
        if (getRowCount("extApps") < 1) {
            addAppPackages("googlehome", "com.google.android.apps.chromecast.app");
            addAppPackages("smartthings", "com.samsung.android.oneconnect");
            addAppPackages("heyhome", "com.goqual");
            addAppPackages("minibig", "com.minibigapp");
            addAppPackages("googleTV", "com.google.android.videos");
        }
    }

    public String getApp_Package(String app_name) {
        String sql = String.format("SELECT * FROM extApps WHERE app_name==\"%s\"", app_name);
        String package_name = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            int colPackage = cursor.getColumnIndex("package_name");
            if (colPackage > -1) {
                package_name = cursor.getString(colPackage);
            }
        }
        cursor.close();
        db.close();
        return package_name;
    }

    // exist - remove
    public String[] loadALLwg() {
        return null;
    }

    public int[] loadWG_Names(int widget_ix) {
        String sql = String.format("SELECT * FROM wgNames WHERE widgetID==%d", widget_ix);
        int[] values = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int colIcon = cursor.getColumnIndex("icon");
            int colName = cursor.getColumnIndex("menu_name");
            if (colIcon > -1 && colName > -1) {
                int wgIcon = cursor.getInt(colIcon);
                int wgName = cursor.getInt(colName);
                values = new int[]{wgIcon, wgName};
            }
        }
        cursor.close();
        db.close();
        return values;
    }

    public List<Pair<Integer, Integer>> loadWG_Orders() {
        List<Pair<Integer, Integer>> widgetLIST = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM wgOrders WHERE orderIx!=-1", null);
        if (cursor.moveToFirst()) {
            do {
                int colID = cursor.getColumnIndex("widgetID");
                int colORDER = cursor.getColumnIndex("orderIx");
                if (colID > -1 && colORDER > -1) {
                    int wgID = cursor.getInt(colID);
                    int wgOrders = cursor.getInt(colORDER);
                    widgetLIST.add(new Pair<>(wgID, wgOrders));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // sorted
        Collections.sort(widgetLIST, (p1, p2) -> Integer.compare(p1.second, p2.second));
        return widgetLIST;
    }

    private void addNames(int widgetID, int icon, int menu_nameIx) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("widgetID", widgetID);
        values.put("icon", icon);
        values.put("menu_name", menu_nameIx);
        db.insert("wgNames", null, values);
        db.close();
    }

    private void addOrders(int widgetID, int orderIx) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("widgetID", widgetID);
        values.put("orderIx", orderIx);
        db.insert("wgOrders", null, values);
        db.close();
    }

    private void addAppPackages(String app_name, String package_name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("app_name", app_name);
        values.put("package_name", package_name);
        db.insert("extApps", null, values);
        db.close();
    }
}