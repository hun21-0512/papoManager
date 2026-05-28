
package papo.hun.papomanager.SubMenu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import papo.hun.papomanager.Frame.WidgetTools;
import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.Func.connPref;
import papo.hun.papomanager.R;

public class ShowMnPopup {
    private FuncTool fTool;
    private ImageView prevBtn;
    private Activity mActivity;
    private Context mContext;
    private FragmentTransaction mFragmentTA;
    private WidgetTools wgTool;
    private View mView;
    private connPref conPref;

    public ShowMnPopup(Activity mActivity, Context mContext, FragmentTransaction mFragmentTA) {
        this.mActivity = mActivity;
        this.mContext = mContext;
        this.mFragmentTA = mFragmentTA;
        fTool = new FuncTool(mActivity, mView);
        wgTool = new WidgetTools(mActivity, mView);
    }

    public void showMenu_popup(int menuIx) {
        if (menuIx==1) {
            showMenu_circulation();
        } else {
            Toast.makeText(mContext, R.string.underDev, Toast.LENGTH_SHORT).show();
        }
    }

    private void showMenu_circulation() {
        Dialog popup = new Dialog(mContext);
        popup.setContentView(R.layout.popup_circulation);
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popup.findViewById(R.id.b1).setOnClickListener(view -> fTool.openApp("minibig"));
        popup.findViewById(R.id.b2).setOnClickListener(view -> fTool.openApp("heyhome"));
        popup.findViewById(R.id.b3).setOnClickListener(view -> fTool.openApp("heyhome"));
        popup.findViewById(R.id.b4).setOnClickListener(view -> {
            wgTool.moveMenu(mFragmentTA, new Living02());
            popup.dismiss();
        });
        popup.show();
    }

    public void showItems(int itemImg, String title, String msg) {
        Dialog popup = new Dialog(mContext);
        popup.setContentView(R.layout.popup_item);
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView titleTV = popup.findViewById(R.id.itemTitle);
        titleTV.setText(title);

        TextView msgTV = popup.findViewById(R.id.content);
        msgTV.setText(msg);

        popup.findViewById(R.id.iconImg).setBackground(mActivity.getDrawable(itemImg));
        popup.findViewById(R.id.closeBtn).setOnClickListener(view -> {
            popup.dismiss();
        });
        popup.show();
    }
}
