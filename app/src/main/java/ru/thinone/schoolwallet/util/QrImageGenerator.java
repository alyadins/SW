package ru.thinone.schoolwallet.util;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Created by alexandrlyadinskii on 26.04.15.
 * All rights reserved©
 */
public class QrImageGenerator {
    public static Bitmap getQrImage(String string) {
        Bitmap result = null;
        if (string != null && !string.isEmpty()) {
            QRCodeWriter writer = new QRCodeWriter();
            try {
                BitMatrix bitMatrix = writer.encode(string, BarcodeFormat.QR_CODE, 256, 256);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }

                result = bmp;
            } catch (WriterException e) {
            }
        }
        return result;
    }
}
