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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.Func.connPref;
import papo.hun.papomanager.R;

public class WidgetTools {
    private FuncTool fTool;
    private Activity mActivity;
    private View mView;
    private connPref conPref;

    public WidgetTools(Activity mActivity, View mView) {
        this.mActivity = mActivity;
        this.mView = mView;
        fTool = new FuncTool(mActivity, mView);
        conPref = new connPref(mActivity.getApplicationContext());
    }

    public void moveMenu(FragmentTransaction parent, Fragment child) {
        parent.replace(R.id.menuLayout, child);
        parent.addToBackStack(null);
        parent.commit();
    }

    public void moveMenuBTN(View menu, FragmentTransaction parent, Fragment child) {
        menu.setOnClickListener(view -> {
            parent.replace(R.id.menuLayout, child);
            parent.addToBackStack(null);
            parent.commit();
        });
    }

    public LinearLayout addMenu(int icon, int titleIX) {
        String title = mActivity.getString(titleIX);
        return addMenu(icon, new int[] {90, 90}, title);
    }

    public LinearLayout addMenu(int icon, int[] iconSize, String title) {
        LinearLayout item = new LinearLayout(mView.getContext());
        item.setOrientation(LinearLayout.HORIZONTAL);
        item.setPadding(40,10,10,10);
        item.setLayoutParams(setParam(-1, 140));
        item.setGravity(Gravity.CENTER_VERTICAL);
        item.setBackgroundColor(Color.parseColor("#FFDDDDDD")); // 회색

        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.parseColor("#FFDDDDDD")); // 배경색
        border.setStroke(1, Color.BLACK); // 테두리 두께와 색상
        item.setBackground(border);

        // Icon
        ImageView iconVw = new ImageView(mView.getContext());
        iconVw.setImageResource(icon);
        iconVw.setLayoutParams(setParam(iconSize[0], iconSize[1]));
        item.addView(iconVw);

        // Title
        TextView titleVw = new TextView(mView.getContext());
        titleVw.setText(title);
        titleVw.setTextSize(15);
        titleVw.setTextColor(Color.BLACK);
        titleVw.setPaintFlags(titleVw.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
        titleVw.setPadding(16, 16, 16, 16);
        item.addView(titleVw);

        // icon
        return item;
    }

    public ViewGroup.LayoutParams setParam(int width, int height) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(width, height);
        return param;
    }

    public ViewGroup.LayoutParams setParam() {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        return param;
    }

    public ViewGroup.LayoutParams setMargin(int n) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        return param;
    }

    public void popup(Context mContext, int layout_ui) {
        Dialog dialog = new Dialog(mContext);
        dialog.setContentView(layout_ui);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}