package com.lingrixin.donetwork.business.volley;

import com.android.volley.VolleyError;

/**
 * for volley call back 
 * @author cyhan
 *
 */
public interface ResultCallback{
    void onSuccess(String response);
    void onFailed(VolleyError error);
}
