package com.lingrixin.donetwork.net;

import java.util.Map;

/**
 * Created by LRXx on 2017-4-19.
 */

public class N {

    private NF nf;

    public N(NF nf) {
        this.nf = nf;
    }

    public void mGet(String url, NetCall call) {
        nf.get(url, call);
    }

    public void mPost(String url, Map par, NetCall call) {
        nf.post(url, par, call);
    }

}
