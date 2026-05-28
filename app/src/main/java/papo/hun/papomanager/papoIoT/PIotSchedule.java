package papo.hun.papomanager.papoIoT;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import papo.hun.papomanager.Func.FuncT;
import papo.hun.papomanager.Func.FuncTool;
import papo.hun.papomanager.PhiloMenu.PhiloPager;
import papo.hun.papomanager.R;

public class PIotSchedule extends Fragment {
    private Activity mActivity;
    private Context mContext;
    private View mView;
    private FuncTool fTool;
    private FuncT fToolK;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.piot_schedule, container, false);
        mActivity = getActivity();
        mContext = getContext();
        fTool = new FuncTool(mActivity, mView);
        fToolK = new FuncT(mActivity, mView);

        //fTool.gpsPermission();
        //fToolK.turnOnGPS();

        return mView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("permission1234", "권한 획득 성공");
            } else {
                Log.d("permission1234", "권한 획득 실패");
            }
        }

        if (requestCode == 1) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mView.getContext(), "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }
}