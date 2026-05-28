package papo.hun.papomanager.homebook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.*;

import java.util.ArrayList;

import papo.hun.papomanager.Frame.WgBookTool;
import papo.hun.papomanager.Func.*;
import papo.hun.papomanager.R;
import papo.hun.papomanager.SubMenu.ShowMnPopup;

public class BookB02 extends Fragment {
    private float dX, dY;
    private ImageView prevBtn;
    private Activity mActivity;
    private Context mContext;
    private View mView;

    private FuncTool fTool;
    private WgBookTool wgTool;
    private connPref conPref;
    private ShowMnPopup showPopup;

    private ArrayList<StackItem> stackItems;
    private LinearLayout homeBoard;
    private int lastAction;
    private int waitT = 0;
    private View[] menu;
    private LinearLayout[] drinkMENU;
    private RelativeLayout[] menuShowBTN;
    private ImageView[] menuShowIcon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.book_wg2b, container, false);
        homeBoard = mView.findViewById(R.id.homeBoard);
        initVAR();
        setStackItem();
        //setPopup();

        prevBtn = mView.findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(view -> {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStackImmediate();
        });
        for (int ix=0; ix < drinkMENU.length; ix++) {
            setDragAndDropListener(ix);
        }

        return mView;
    }

    private void initVAR() {
        mActivity = getActivity();
        mContext = getContext();

        // menuIcon
        drinkMENU = new LinearLayout[12];
        drinkMENU[0] = mView.findViewById(R.id.b1);
        drinkMENU[1] = mView.findViewById(R.id.b2);
        drinkMENU[2] = mView.findViewById(R.id.b3);
        drinkMENU[3] = mView.findViewById(R.id.b4);
        drinkMENU[4] = mView.findViewById(R.id.b5);
        drinkMENU[5] = mView.findViewById(R.id.b6);
        drinkMENU[6] = mView.findViewById(R.id.b7);
        drinkMENU[7] = mView.findViewById(R.id.b8);
        drinkMENU[8] = mView.findViewById(R.id.b9);
        drinkMENU[9] = mView.findViewById(R.id.b10);
        drinkMENU[10] = mView.findViewById(R.id.b11);
        drinkMENU[11] = mView.findViewById(R.id.b12);

        // menuIcon
        fTool = new FuncTool(mActivity, mView);
        conPref = new connPref(mContext);
        menuShowIcon = new ImageView[]{mView.findViewById(R.id.mfI1)};
        menu = new androidx.gridlayout.widget.GridLayout[] {mView.findViewById(R.id.menuboard)};
        showPopup = new ShowMnPopup(mActivity, mContext, getParentFragmentManager().beginTransaction());

        // menu
        menuShowBTN = new RelativeLayout[]{mView.findViewById(R.id.mfb1)};
        wgTool = new WgBookTool(mActivity, mView, menuShowIcon, menu, menuShowBTN);
        wgTool.setBookMenuLayout("B", 0);
        wgTool.setBookMenuLST("B", 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setDragAndDropListener(final int ix) {
        StackItem tmpItem = stackItems.get(ix);
        drinkMENU[ix].setOnTouchListener((v, event) -> {
            //LinearLayout parentLayout = (LinearLayout) v.getParent().getParent().getParent().getParent().getParent();
            androidx.gridlayout.widget.GridLayout parentLayout = (androidx.gridlayout.widget.GridLayout) v.getParent();

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    dX = v.getX() - event.getRawX();
                    dY = v.getY() - event.getRawY();
                    lastAction = MotionEvent.ACTION_DOWN;
                    return true;

                case MotionEvent.ACTION_MOVE:
                    waitT += 1;
                    float newX = event.getRawX() + dX;
                    float newY = event.getRawY() + dY;

                    int parentWidth = parentLayout.getWidth();
                    int parentHeight = parentLayout.getHeight();
                    int viewWidth = v.getWidth();
                    int viewHeight = v.getHeight();

                    if (newX < 0) {
                        newX = 0;
                    } else if (newX + viewWidth > parentWidth) {
                        newX = parentWidth - viewWidth;
                    }

                    if (newY < 0) {
                        newY = 0;
                    } else if (newY + viewHeight > parentHeight) {
                        newY = parentHeight - viewHeight;
                    }

                    v.setX(newX);
                    v.setY(newY);
                    lastAction = MotionEvent.ACTION_MOVE;
                    return true;
                case MotionEvent.ACTION_UP:
                    if (waitT < 20) {
                        showPopup.showItems(tmpItem.getImg(), tmpItem.getTitle(), tmpItem.getContents());
                    }
                    waitT = 0;
                    return true;
                default:
                    return false;
            }
        });
    }

    private void setStackItem() {
        stackItems = new ArrayList<>();
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList01),  mActivity.getString(R.string.book_itemList01M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList02), mActivity.getString(R.string.book_itemList02M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList03), mActivity.getString(R.string.book_itemList03M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList04), mActivity.getString(R.string.book_itemList04M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList05), mActivity.getString(R.string.book_itemList05M)));
        stackItems.add(new StackItem(R.drawable.icon_lemonade, mActivity.getString(R.string.book_itemList06), mActivity.getString(R.string.book_itemList06M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList07), mActivity.getString(R.string.book_itemList07M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList08), mActivity.getString(R.string.book_itemList08M)));
        stackItems.add(new StackItem(R.drawable.icon_chocolate, mActivity.getString(R.string.book_itemList09), mActivity.getString(R.string.book_itemList09M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList10), mActivity.getString(R.string.book_itemList10M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList11), mActivity.getString(R.string.book_itemList11M)));
        stackItems.add(new StackItem(R.drawable.icon_ice, mActivity.getString(R.string.book_itemList12), mActivity.getString(R.string.book_itemList12M)));
    }
}