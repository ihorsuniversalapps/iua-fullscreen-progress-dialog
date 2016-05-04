package ua.in.iua.iua_fullscreen_progress_dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FullscreenProgressDialog extends BaseFullscreenDialog {

    private final static String TAG = "ProgressDialog";
    final private Handler mHandler = new Handler();
    private String mMessage;
    private TextView tvMessage;
    private Runnable mRunnable;

    private float mDimAmount = 0.7f;
    private int mTintColor = R.color.colorProgressDialog;

    public static FullscreenProgressDialog dialogWithCustomMessage(String message) {
        FullscreenProgressDialog progressDialog = new FullscreenProgressDialog();
        progressDialog.mMessage = message;
        return progressDialog;
    }

    public float getDimAmount() {
        return mDimAmount;
    }

    public void setDimAmount(float dimAmount) {
        mDimAmount = dimAmount;
    }

    public int getTintColor() {
        return mTintColor;
    }

    public void setTintColor(int tintColor) {
        mTintColor = tintColor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fullscreen_progress_dialog, container, false);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(ActivityCompat.getColor(getContext(), mTintColor), PorterDuff.Mode.MULTIPLY);

        tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setTextColor(ActivityCompat.getColor(getContext(), mTintColor));

        if (mMessage != null) {
            tvMessage.setText(mMessage);
        }

        setCancelable(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(mDimAmount);

        return view;
    }

    public void setMessage(String message) {
        if (message != null && tvMessage != null) {
            tvMessage.setText(message);
        }
    }

    public void show(int delay, final AppCompatActivity activity) {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                show(activity);
            }
        };
        mHandler.postDelayed(mRunnable, delay);
    }

    public void show(AppCompatActivity activity) {
        if (activity != null && !activity.isFinishing() && !isShowing() && !isAdded()) {
            super.show(activity.getSupportFragmentManager(), TAG);
        }
    }

    public void show(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = fragment.getFragmentManager();
            if (fragmentManager != null && !fragmentManager.isDestroyed() && !isShowing() || !isAdded()) {
                super.show(fragmentManager, TAG);
            }
        }
    }

    public boolean isShowing() {
        Dialog dialog = getDialog();
        return dialog != null && dialog.isShowing();
    }

    @Override
    public void dismiss() {
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
            mRunnable = null;
        }
        if (isShowing()) {
            try {
                super.dismissAllowingStateLoss();
            } catch (IllegalStateException ignored) {

            }
        }
    }
}
