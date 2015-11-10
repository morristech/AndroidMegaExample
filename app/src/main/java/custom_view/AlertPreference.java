package custom_view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import jr.timesaverexample.R;


/**
 * Created by tanjunrong on 09/11/2015.
 */
public class AlertPreference extends Preference {
    boolean DEFAULT_VALUE = false;
    boolean mCurrentValue;

    public AlertPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public AlertPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AlertPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlertPreference(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return li.inflate(R.layout.preference_settings, parent, false);

    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        checkBox.setChecked(getPersistedBoolean(DEFAULT_VALUE));
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ddw", "test test 123: " + ((CheckBox) v).isChecked());
//                setChecked(((CheckBox) v).isChecked());
                persistBoolean(((CheckBox) v).isChecked());
            }
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        debugLog("onBindView", "prefs.getBoolean: " + prefs.getBoolean("alertType", true));
    }

    private void debugLog(String tag, String value) {
        Log.d("ddw", "[" + tag + "]" + value);
    }
}
