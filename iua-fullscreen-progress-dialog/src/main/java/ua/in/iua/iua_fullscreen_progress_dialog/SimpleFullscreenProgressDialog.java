package ua.in.iua.iua_fullscreen_progress_dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SimpleFullscreenProgressDialog extends Dialog {

    public SimpleFullscreenProgressDialog(Context context, String message, @ColorRes int tintColorResource) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(ua.in.iua.iua_fullscreen_progress_dialog.R.layout.fullscreen_progress_dialog, null, false);

        int tintColor = ActivityCompat.getColor(context, tintColorResource);

        ProgressBar progressBar = (ProgressBar) view.findViewById(ua.in.iua.iua_fullscreen_progress_dialog.R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(tintColor, PorterDuff.Mode.MULTIPLY);

        TextView tvMessage = (TextView) view.findViewById(ua.in.iua.iua_fullscreen_progress_dialog.R.id.tvMessage);
        tvMessage.setTextColor(tintColor);
        tvMessage.setText(message);

        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setDimAmount(0.7f);
        setCancelable(false);
    }

    public SimpleFullscreenProgressDialog(Context context) {
        this(context, context.getString(ua.in.iua.iua_fullscreen_progress_dialog.R.string.dialog_progress_default_text), ua.in.iua.iua_fullscreen_progress_dialog.R.color.colorProgressDialog);
    }

    public static Dialog showDialog(Context context, String message, @ColorRes int tintColorResource) {
        Dialog dialog = new SimpleFullscreenProgressDialog(context, message, tintColorResource);
        dialog.show();
        return dialog;
    }
}
