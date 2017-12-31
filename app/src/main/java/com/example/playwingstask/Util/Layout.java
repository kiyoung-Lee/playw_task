package com.example.playwingstask.Util;

import java.text.DecimalFormat;

/**
 * Created by KiyoungLee on 2017-12-31.
 */

public class Layout {

    public static String getFormattedPrice(int price) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(price);
    }
}
