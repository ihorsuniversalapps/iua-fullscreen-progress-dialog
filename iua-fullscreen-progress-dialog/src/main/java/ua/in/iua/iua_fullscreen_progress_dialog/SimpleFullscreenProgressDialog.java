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
        View view = inflater.inflate(R.layout.fullscreen_progress_dialog, null, false);

        int tintColor = ActivityCompat.getColor(context, tintColorResource);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(tintColor, PorterDuff.Mode.MULTIPLY);

        TextView tvMessage = (TextView) view.findViewById(R.id.tvMessage);
        tvMessage.setTextColor(tintColor);
        tvMessage.setText(message);

        setContentView(view);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public SimpleFullscreenProgressDialog(Context context) {
        this(context, context.getString(R.string.dialog_progress_default_text), R.color.colorProgressDialog);
    }

    public static Dialog showDialog(Context context, String message, @ColorRes int tintColorResourse) {
        Dialog dialog = new Dialog(context);
        dialog.show();
        return dialog;
    }
}
