package nl.perry3d.ghostnet;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        registerPlugin(GhostNetAndroidPlugin.class);
        super.onCreate(savedInstanceState);
    }
}
