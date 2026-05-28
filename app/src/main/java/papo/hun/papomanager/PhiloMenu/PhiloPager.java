package papo.hun.papomanager.PhiloMenu;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PhiloPager extends FragmentPagerAdapter {

    public PhiloPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PhiloAI();
            case 1:
                return new PhiloChat();
            case 2:
                return new PhiloERP();
            case 3:
                return new PhiloPlayStation();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}