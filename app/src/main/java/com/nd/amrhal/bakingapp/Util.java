package com.nd.amrhal.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Util {


    public static String TWO_PANE_SHAREDP = "mtwopane";
    public static String TWO_PANE_PHONE_OR_TABLET = "phoneortablet"; //0 for phone //1 for tablet
    public static int PHONE  =  0  ; //0 for phone case
    public static int TABLET =  1  ; //0 for tablet case



    public static void setPhoneOrTablet(Context context, int phoneortablet) {

        context.getSharedPreferences(TWO_PANE_SHAREDP, Context.MODE_PRIVATE).edit().putInt(TWO_PANE_PHONE_OR_TABLET, phoneortablet ).apply();

    }


    public static int getPhoneOrTablet(Context context) {

        int check_phone_or_tablet = context.getSharedPreferences(TWO_PANE_SHAREDP, Context.MODE_PRIVATE).getInt(TWO_PANE_PHONE_OR_TABLET, 0);

        return check_phone_or_tablet;
    }


//    private String checkPhoneOrTablet(int i) {
//        String result = "not specific Phone or Tablet";
//        if (i == PHONE) {
//            result = "it's a Phone";
//        } else if (i == TABLET) {
//            result = "it's a Tablet";
//        }
//        return result;
//    }
}

