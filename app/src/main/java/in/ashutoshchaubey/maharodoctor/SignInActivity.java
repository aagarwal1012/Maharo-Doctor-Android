package in.ashutoshchaubey.maharodoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        RelativeLayout signinParent = (RelativeLayout)findViewById(R.id.sign_in_parent);
        signinParent.getBackground().setAlpha(128);
    }
}
