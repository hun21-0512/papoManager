package papo.hun.papomanager.Frame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.Func.connPref;
import papo.hun.papomanager.R;

public class WgBookTool {
    private FuncTool fTool;
    private Activity mActivity;
    private View mView;
    private connPref conPref;
    private View[] menu;
    private RelativeLayout[] menuShowBTN;
    private ImageView[] menuShowIcon;

    public WgBookTool(Activity mActivity, View mView, ImageView[] menuShowIcon, View[] menu, RelativeLayout[] menuShowBTN) {
        this.mActivity = mActivity;
        this.mView = mView;
        this.menu = menu;
        this.menuShowIcon = menuShowIcon;
        this.menuShowBTN = menuShowBTN;
        fTool = new FuncTool(mActivity, mView);
        conPref = new connPref(mActivity.getApplicationContext());
    }

    public void setBookMenuFold(String part, int ix) {
        String menu_name = String.format("bookName%s%d", part, ix);
        menuShowIcon[ix].setBackgroundResource(R.drawable.baseline_arrow_drop_down_24);
        menu[ix].setVisibility(View.GONE);
        conPref.prefSave(menu_name, "fold");
    }

    public void setBookMenuUnFold(String part, int ix) {
        String menu_name = String.format("bookName%s%d", part, ix);
        menuShowIcon[ix].setBackgroundResource(R.drawable.baseline_arrow_drop_up_24);
        menu[ix].setVisibility(View.VISIBLE);
        conPref.prefSave(menu_name, "unfold");
    }

    public void setBookMenuLayout(String part, int ix) {
        String menuPref = conPref.prefLoad(String.format("bookName%s%d", part, ix));
        if (menuPref.equals("None") || menuPref.equals("unfold")) {
            setBookMenuUnFold(part, ix);
        } else {
            setBookMenuFold(part, ix);
        }
    }

    public void setBookMenuLST(String part, int ix) {
        menuShowBTN[ix].setOnClickListener(view -> {
            String menuPref = conPref.prefLoad(String.format("bookName%s%d", part, ix));
            if (menuPref.equals("unfold")) {
                setBookMenuFold(part, ix);
            } else {
                setBookMenuUnFold(part, ix);
            }
        });
    }
}