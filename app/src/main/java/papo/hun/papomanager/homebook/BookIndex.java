package papo.hun.papomanager.homebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import papo.hun.papomanager.Frame.WidgetTools;
import papo.hun.papomanager.Func.RES;
import papo.hun.papomanager.Func.connPref;
import papo.hun.papomanager.R;

public class BookIndex extends Fragment {
    private connPref conPref;
    private WidgetTools wTools;
    private View mView;
    private LinearLayout[] menu;
    private ImageView[] menuShowIcon;
    private RelativeLayout[] menuShowBTN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.book_maindex, container, false);
        conPref = new connPref(getContext());
        wTools = new WidgetTools(getActivity(), mView);

        // menuLayout - Layout
        setWidget();
        setMenuLayouts(5);
        setMenuFoldEvents(5);
        addMenuBTNs();
        return mView;
    }

    private void setWidget() {
        menu = new LinearLayout[5];
        menu[0] = mView.findViewById(R.id.mf1);
        menu[1] = mView.findViewById(R.id.mf2);
        menu[2] = mView.findViewById(R.id.mf3);
        menu[3] = mView.findViewById(R.id.mf4);
        menu[4] = mView.findViewById(R.id.mf5);

        menuShowIcon = new ImageView[5];
        menuShowIcon[0] = mView.findViewById(R.id.mfI1);
        menuShowIcon[1] = mView.findViewById(R.id.mfI2);
        menuShowIcon[2] = mView.findViewById(R.id.mfI3);
        menuShowIcon[3] = mView.findViewById(R.id.mfI4);
        menuShowIcon[4] = mView.findViewById(R.id.mfI5);

        menuShowBTN = new RelativeLayout[5];
        menuShowBTN[0] = mView.findViewById(R.id.mfb1);
        menuShowBTN[1] = mView.findViewById(R.id.mfb2);
        menuShowBTN[2] = mView.findViewById(R.id.mfb3);
        menuShowBTN[3] = mView.findViewById(R.id.mfb4);
        menuShowBTN[4] = mView.findViewById(R.id.mfb5);
    }

    private void addMenuBTNs() {
        for (int ix=0; ix < RES.menuBtnIds.length; ix++) {
            int menuBtnID = RES.menuBtnIds[ix];
            wTools.moveMenuBTN(mView.findViewById(menuBtnID), getParentFragmentManager().beginTransaction(), RES.books[ix]);
        }
    }

    private void setMenuFold(int ix) {
        String menu_name = String.format("menuName%d", ix);
        menuShowIcon[ix].setBackgroundResource(R.drawable.baseline_arrow_drop_down_24);
        menu[ix].setVisibility(View.GONE);
        conPref.prefSave(menu_name, "fold");
    }

    private void setMenuUnFold(int ix) {
        String menu_name = String.format("menuName%d", ix);
        menuShowIcon[ix].setBackgroundResource(R.drawable.baseline_arrow_drop_up_24);
        menu[ix].setVisibility(View.VISIBLE);
        conPref.prefSave(menu_name, "unfold");
    }

    private void setMenuLayouts(int totalIx) {
        for (int v=0; v<totalIx; v++) {
            setMenuLayout(v);
        }
    }

    private void setMenuLayout(int ix) {
        String menuPref = conPref.prefLoad(String.format("menuName%d", ix));
        if (menuPref.equals("None") || menuPref.equals("unfold")) {
            setMenuUnFold(ix);
        } else {
            setMenuFold(ix);
        }
    }

    private void setMenuFoldEvents(int totalIx) {
        for (int v=0; v<totalIx; v++) {
            setMenuFoldEvent(v);
        }
    }

    private void setMenuFoldEvent(int ix) {
        menuShowBTN[ix].setOnClickListener(view -> {
            String menuPref = conPref.prefLoad(String.format("menuName%d", ix));
            if (menuPref.equals("unfold")) {
                setMenuFold(ix);
            } else {
                setMenuUnFold(ix);
            }
        });
    }
}