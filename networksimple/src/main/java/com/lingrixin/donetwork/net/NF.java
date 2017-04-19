package com.lingrixin.donetwork.net;

import java.util.Map;

/**
 * Created by LRXx on 2017-4-19.
 */

public interface NF {
    void get(String mUrl,NetCall nc);
    void post(String mUrl, Map par,NetCall nc);
}
