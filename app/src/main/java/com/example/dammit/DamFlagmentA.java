package com.example.dammit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DamFlagmentA extends Fragment {

    public static RequestQueue rq;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_a_dam, container, false);

        makeRequest(view);
        if (rq == null) {
            rq = Volley.newRequestQueue(getContext());
        }
        return view;
    }

    //    @Nullable
    public void makeRequest(View view) {

        TextView textView;

        String url = "http://61.103.243.140:3000/api/sensor";

        textView = view.findViewById(R.id.textView1);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //응답
                try {
                    if (textView != null) {
                        textView.setText(response);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //에러
                textView.setText("에러: " + error.toString());
            }
        });
        System.out.println(stringRequest + "이진우");

        try {
            if (rq.add(stringRequest) == null) {
//                Thread.sleep(5000);
//                rq.add(stringRequest);
            }
            
        } catch (Exception e) {
            System.out.println(e + "112312331321");
        }
    }
}



