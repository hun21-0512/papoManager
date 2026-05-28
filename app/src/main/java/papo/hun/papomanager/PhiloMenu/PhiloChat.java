package papo.hun.papomanager.PhiloMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import papo.hun.papomanager.R;

public class PhiloChat extends Fragment {
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         mView = inflater.inflate(R.layout.philo_curious, container, false);

        return mView;
    }
}