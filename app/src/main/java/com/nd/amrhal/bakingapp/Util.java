package com.nd.amrhal.bakingapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Util {


    public static String TWO_PANE_SHAREDP = "mtwopane";
    public static String TWO_PANE_PHONE_OR_TABLET = "phoneortablet"; //0 for phone //1 for tablet
    public static int PHONE = 0;   //0 for phone case
    public static int TABLET = 1;   //1 for tablet case
    //--------------- for position of rec
    public static String POSITION_SHAREDP = "position"; //position value of steps recyclerView
    public static String POSITION_VALUE = "positionvalue";
    //---------------- for widgets
    public static String WIDGET_SHAREDP = "widgetshared";
    public static String WIDGET_STRING = "widgetstring";


    public static void setPhoneOrTablet(Context context, int phoneortablet) {

        context.getSharedPreferences(TWO_PANE_SHAREDP, Context.MODE_PRIVATE).edit().putInt(TWO_PANE_PHONE_OR_TABLET, phoneortablet).apply();

    }


    public static int getPhoneOrTablet(Context context) {

        int check_phone_or_tablet = context.getSharedPreferences(TWO_PANE_SHAREDP, Context.MODE_PRIVATE).getInt(TWO_PANE_PHONE_OR_TABLET, 0);

        return check_phone_or_tablet;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------------
    public static void setPositionfortabletonly(Context context, int pos) {

        context.getSharedPreferences(POSITION_SHAREDP, Context.MODE_PRIVATE).edit().putInt(POSITION_VALUE, pos).apply();

    }


    public static int getPositionfortabletonly(Context context) {

        int pos = context.getSharedPreferences(POSITION_SHAREDP, Context.MODE_PRIVATE).getInt(POSITION_VALUE, 0);

        return pos;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------


    public static void setSharedPValueforWidget(Context context, String IngreduentString) {

        context.getSharedPreferences(WIDGET_SHAREDP, Context.MODE_PRIVATE).edit().putString(WIDGET_STRING, IngreduentString).apply();

    }


    public static String getSharedPValueforWidget(Context context) {

        String s = context.getSharedPreferences(WIDGET_SHAREDP, Context.MODE_PRIVATE).getString(WIDGET_STRING, "Nothing here");

        return s;
    }

}

