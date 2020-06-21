package com.example.attendanceapp;

import android.graphics.Bitmap;

import java.io.Serializable;

public class QrCode implements Serializable {

    private Bitmap qrcode;

    public QrCode () {

    }

    public QrCode(Bitmap qrcode) {
        this.qrcode = qrcode;
    }

    public Bitmap getQrcode() {
        return qrcode;
    }

    public void setQrcode(Bitmap qrcode) {
        this.qrcode = qrcode;
    }
}
