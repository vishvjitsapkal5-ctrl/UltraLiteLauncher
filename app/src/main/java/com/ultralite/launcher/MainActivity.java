package com.ultralite.launcher;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Long-press empty space: wallpaper picker
        findViewById(R.id.root).setOnLongClickListener(v -> {
            openWallpaperPicker();
            return true;
        });

        // Escape hatch until the app drawer exists (Stage 2)
        findViewById(R.id.btn_default_home).setOnClickListener(v -> openHomeSettings());
    }

    private void openWallpaperPicker() {
        try {
            startActivity(Intent.createChooser(
                    new Intent(Intent.ACTION_SET_WALLPAPER),
                    getString(R.string.wallpaper)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.action_unavailable, Toast.LENGTH_SHORT).show();
        }
    }

    private void openHomeSettings() {
        try {
            startActivity(new Intent(Settings.ACTION_HOME_SETTINGS));
        } catch (ActivityNotFoundException e) {
            try {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
            } catch (ActivityNotFoundException e2) {
                Toast.makeText(this, R.string.action_unavailable, Toast.LENGTH_SHORT).show();
            }
        }
    }

    // A launcher should not exit when Back is pressed.
    @Override
    @SuppressWarnings("deprecation")
    public void onBackPressed() {
        // intentionally empty
    }
}
