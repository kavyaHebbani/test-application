package kavya.sample.testapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kavya.sample.testapplication.fragments.MyFragmentManager;
import kavya.sample.testapplication.fragments.WelcomeFragment;

import static kavya.sample.testapplication.fragments.MyFragmentManager.WELCOME_FRAGMENT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }

}
