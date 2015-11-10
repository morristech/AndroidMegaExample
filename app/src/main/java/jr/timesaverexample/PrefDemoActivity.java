package jr.timesaverexample;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by tanjunrong on 10/11/2015.
 */
public class PrefDemoActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
