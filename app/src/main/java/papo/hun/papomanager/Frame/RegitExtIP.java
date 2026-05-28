package papo.hun.papomanager.Frame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import papo.hun.papomanager.Func.NetTool;
import papo.hun.papomanager.Func.connPref;
import papo.hun.papomanager.R;

public class RegitExtIP extends AppCompatActivity {
    private connPref conPref;
    private NetTool tool;
    private ImageView prevBtn;
    private Button loadIpBtn, regitBtn;
    private EditText ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piot_global_ip);
        Objects.requireNonNull(getSupportActionBar()).hide();

        tool = new NetTool(this, null);
        conPref = new connPref(this);
        ipAddress = findViewById(R.id.ext_ip);

        String ip = conPref.prefLoad("globalIP");
        if (!ip.equals("None")) {
            ipAddress.setText(ip);
        }

        prevBtn = findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(view -> finish());

        loadIpBtn = findViewById(R.id.loadIpBtn);
        loadIpBtn.setOnClickListener(view -> {
            String globalIp = tool.getGlobalIP();
            ipAddress.setText(globalIp);
        });

        regitBtn = findViewById(R.id.regitExtIpBtn);
        regitBtn.setOnClickListener(view -> {
            conPref.prefSave("globalIP", String.valueOf(ipAddress.getText()));
            Toast.makeText(this, R.string.netExt_RegitMsg, Toast.LENGTH_SHORT).show();
        });
    }
}