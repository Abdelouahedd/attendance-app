package com.example.attendanceapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.common.BitMatrix;

public class QrCodeDiaglo extends DialogFragment {

    private TextView header;
    private ImageView qrcodeView;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_qrcode, null);

        header = view.findViewById(R.id.qrcode_header);
        qrcodeView = view.findViewById(R.id.qrcode_img);

        QrCode qrcode = (QrCode) getArguments().getSerializable("qrcode");
        qrcodeView.setImageBitmap(qrcode.getQrcode());

        String classroom = getArguments().getString("classroom");
        int duration = getArguments().getInt("duration");
        header.setText(classroom + " / " + duration + " min");

        builder.setView(view).setTitle("Generated Qr Code");

        return builder.create();
    }

    public static QrCodeDiaglo newInstance(QrCode qrCode, String classLabel,
                                           int duration) {
        QrCodeDiaglo dialog = new QrCodeDiaglo();
        Bundle bundle = new Bundle();

        bundle.putSerializable("qrcode", qrCode);
        bundle.putString("classroom", classLabel);
        bundle.putInt("duration", duration);
        dialog.setArguments(bundle);

        return dialog;
    }
}
