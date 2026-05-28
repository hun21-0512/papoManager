package papo.hun.papomanager.Frame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import papo.hun.papomanager.Func.connPref;
import papo.hun.papomanager.R;

public class RegitThing extends AppCompatActivity {
    private connPref conPref;
    private ImageView prevBtn;
    private Button iotSSID_Btn, regitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piot_regit);
        Objects.requireNonNull(getSupportActionBar()).hide();

        prevBtn = findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(view -> {
            finish();
        });

        iotSSID_Btn = findViewById(R.id.find_IOTssid);
        iotSSID_Btn.setOnClickListener(view -> {

        });

        regitBtn = findViewById(R.id.regitBtnT);
        regitBtn.setOnClickListener(view -> {
            Set<String> device = new HashSet<>();
            device.add("Apple");
            device.add("Banana");
            conPref.regitDevice("", device);
        });
    }
}