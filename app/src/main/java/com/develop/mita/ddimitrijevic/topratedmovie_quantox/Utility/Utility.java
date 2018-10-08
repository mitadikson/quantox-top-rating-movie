package com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String date) {
        SimpleDateFormat existingUTCFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat requiredFormat = new SimpleDateFormat("MMMM dd, yyyy");
        Date getDate = null;
        try {
            getDate = existingUTCFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return requiredFormat.format(getDate);
    }
}
