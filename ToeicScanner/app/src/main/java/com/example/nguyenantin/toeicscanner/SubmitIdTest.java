package com.example.nguyenantin.toeicscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SubmitIdTest extends AppCompatActivity {

    private Button submit_id;
    private EditText edt_submit_id;
    private LinearLayout hide_nav;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSystemUI();
        setContentView(R.layout.activity_submit_id_test);
        submit_id = (Button) findViewById(R.id.submit_id);
        edt_submit_id = (EditText)findViewById(R.id.edt_submit_id);
        hide_nav = (LinearLayout)findViewById(R.id.hide_nav);
        // permission camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 100);
        //Activity in component
        try {
            submit_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mis-clicking prevention, using threshold of 1000 ms
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 2000){
                        return;
                    }
                    mLastClickTime = SystemClock.elapsedRealtime();
                    try {
                        hide_nav.setEnabled(false);
                        submit_id.setEnabled(false);
                        Intent intent = new Intent(SubmitIdTest.this, CustomCamera.class);
                        startActivity(intent);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
        try {
            hide_nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSystemUI();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //hide system navigation
    // This snippet hides the system bars.
    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }
}
