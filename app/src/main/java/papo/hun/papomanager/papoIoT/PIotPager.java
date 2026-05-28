package papo.hun.papomanager.papoIoT;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import papo.hun.papomanager.PhiloMenu.PhiloAI;
import papo.hun.papomanager.PhiloMenu.PhiloChat;
import papo.hun.papomanager.PhiloMenu.PhiloERP;
import papo.hun.papomanager.PhiloMenu.PhiloPlayStation;

public class PIotPager extends FragmentPagerAdapter {

    public PIotPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PIotRegit();
            case 1:
                return new PIotDevice();
            case 2:
                return new PIotSchedule();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}