package papo.hun.papomanager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.PhiloMenu.PhiloMain;
import papo.hun.papomanager.SubMenu.LivingIDX;
import papo.hun.papomanager.homebook.BookIndex;
import papo.hun.papomanager.papoIoT.PIotMain;

public class AppMain extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FuncTool fTool;
    private LivingIDX menu1 = new LivingIDX();
    private PIotMain PIotMain = new PIotMain();
    private PhiloMain philoMain = new PhiloMain();
    private BookIndex bookIndex = new BookIndex();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main_ui);
        Objects.requireNonNull(getSupportActionBar()).hide();
        fTool = new FuncTool(this, findViewById(R.id.menu_main));
        fTool.nofityPermission();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menuLayout, menu1).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            int menuid = menuItem.getItemId();
            if (menuid == R.id.menuHome) {
                transaction.replace(R.id.menuLayout, menu1).commitAllowingStateLoss();
            } else if (menuid == R.id.menuIoT) {
                transaction.replace(R.id.menuLayout, PIotMain).commitAllowingStateLoss();
            } else if (menuid == R.id.menuAI) {
                transaction.replace(R.id.menuLayout, philoMain).commitAllowingStateLoss();
            } else if (menuid == R.id.menuOther) {
                transaction.replace(R.id.menuLayout, bookIndex).commitAllowingStateLoss();
            }
            return true;
        }
    }
}