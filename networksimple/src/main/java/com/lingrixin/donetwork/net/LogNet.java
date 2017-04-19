package com.lingrixin.donetwork.net;

import com.lingrixin.donetwork.utils.L;

/**
 * Created by LRXx on 2017-4-19.
 */

public class LogNet {

    public static void l(String url,String par,String result){
        L.i(L.makeLogTag("net"), "-------------start--------------");
        L.i(L.makeLogTag("net"), url);
        L.i(L.makeLogTag("net"), par);
        L.i(L.makeLogTag("net"), result);
        L.i(L.makeLogTag("net"), "-------------end----------------");
    }

}
