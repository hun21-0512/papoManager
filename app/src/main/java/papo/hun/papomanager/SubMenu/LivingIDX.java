package papo.hun.papomanager.SubMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.*;
import android.widget.*;

import androidx.fragment.app.Fragment;

import java.util.List;

import papo.hun.papomanager.Frame.Settings;
import papo.hun.papomanager.Frame.WidgetTools;
import papo.hun.papomanager.Func.*;
import papo.hun.papomanager.R;

public class LivingIDX extends Fragment {
    private LinearLayout homeBoard;
    private ImageView editListM, setListM, setGridM, settingBtn;
    private View mView;

    private Activity mActivity;
    private Context mContext;
    private FuncTool tools;
    private WidgetTools wgTool;
    private ShowMnPopup popMenu;
    private DBTool dbTool;
    private connPref conPref;

    private boolean isEditMenu = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_home, container, false);
        initVAR();
        initWG();
        editLISTMenu();
        loadLayout();
        return mView;
    }

    private void initVAR() {
        mActivity = getActivity();
        mContext = getContext();
        conPref = new connPref(mContext);
        dbTool = new DBTool(mContext);
        tools = new FuncTool(mActivity, mView);
        wgTool = new WidgetTools(mActivity, mView);
        popMenu = new ShowMnPopup(mActivity, mContext, getParentFragmentManager().beginTransaction());
    }

    private void initWG() {
        homeBoard = mView.findViewById(R.id.homeBoard);
        editListM = mView.findViewById(R.id.editMenu);
        settingBtn = mView.findViewById(R.id.settingBtn);
        setListM = mView.findViewById(R.id.setListMenu);
        setGridM = mView.findViewById(R.id.setGridMenu);
    }

    private void editLISTMenu() {
        editListM.setOnClickListener(view -> {
            if (isEditMenu) {
                editListM.setBackground(mActivity.getDrawable(R.drawable.widget4));
                isEditMenu = false;
            } else {
                editListM.setBackground(mActivity.getDrawable(R.drawable.widget5));
                isEditMenu = true;
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Settings.class));
            }
        });
    }

    private void menuLIST() {
        List<Pair<Integer, Integer>> menuORDs = dbTool.loadWG_Orders();
        for (int ix=0; ix < menuORDs.size(); ix++) {
            Pair<Integer, Integer> menuORD = menuORDs.get(ix);
            int[] wgValue = dbTool.loadWG_Names(menuORD.first);

            // menu set
            LinearLayout menu = wgTool.addMenu(wgValue[0], wgValue[1]);
            int finalIx = ix;
            menu.setOnClickListener(view -> {
                popMenu.showMenu_popup(menuORD.first);
                //wgTool.showDialog1(getContext(), R.layout.popup_circulation);
            });

            // last menu
            if (ix == menuORDs.size()-1) {
                LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) menu.getLayoutParams();
                param.setMargins(0, 0,0, 30);
                menu.setLayoutParams(param);
            }
            homeBoard.addView(menu);
        }
    }

    private void menuGRID() {

    }

    private void loadLayout() {
        setMenuChange();
        String menuLayout = conPref.getLayoutSET("menuLayout");
        if (menuLayout.equals("menuLIST")) {
            setListM.setBackground(mActivity.getDrawable(R.drawable.widget5));
            menuLIST();
        }
        else if (menuLayout.equals("menuGRID")) {
            setGridM.setBackground(mActivity.getDrawable(R.drawable.widget5));
            menuGRID();
        }
    }

    private void setMenuLIST() {
        setListM.setBackground(mActivity.getDrawable(R.drawable.widget5));
        setGridM.setBackground(mActivity.getDrawable(R.drawable.widget4));
        menuLIST();
        conPref.prefSave("menuLayout", "menuLIST");
    }

    private void setMenuGRID() {
        setListM.setBackground(mActivity.getDrawable(R.drawable.widget4));
        setGridM.setBackground(mActivity.getDrawable(R.drawable.widget5));
        menuGRID();
        conPref.prefSave("menuLayout", "menuGRID");
    }

    private void setMenuChange() {
        setListM.setOnClickListener(view -> setMenuLIST());
        setGridM.setOnClickListener(view -> setMenuGRID());
    }
}