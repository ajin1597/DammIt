package com.example.dammit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DamFlagmentA extends Fragment {

    public static RequestQueue rq = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_a_dam, container, false);

        if (rq == null) {
            rq = Volley.newRequestQueue(getContext());
        }

        int fragNum = 3;

        JsonReader jsonReader;
//        img(view);
        JsonReader.makeRequest(view, fragNum);

        return view;
    }

}



