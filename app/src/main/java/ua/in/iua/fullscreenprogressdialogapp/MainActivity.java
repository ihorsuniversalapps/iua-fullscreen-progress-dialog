package ua.in.iua.fullscreenprogressdialogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ua.in.iua.iua_fullscreen_progress_dialog.FullscreenProgressDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FullscreenProgressDialog dialog = new FullscreenProgressDialog();
        dialog.show(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                });
            }
        }).start();
    }
}
