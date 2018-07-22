package in.ashutoshchaubey.maharodoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RelativeLayout loginParent = (RelativeLayout)findViewById(R.id.log_in_parent);
        loginParent.getBackground().setAlpha(128);
    }
}
