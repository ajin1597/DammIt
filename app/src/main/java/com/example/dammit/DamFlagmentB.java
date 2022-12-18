package com.example.dammit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

public class DamFlagmentB extends Fragment {

    public static RequestQueue rq = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.flagment_b_dam,container,false);

        View view = inflater.inflate(R.layout.flagment_b_dam, container, false);

        if (rq == null) {
            rq = Volley.newRequestQueue(getContext());
        }
        JsonReader jsonReader;
//        img(view);

        int fragNum = 4;

        JsonReader.makeRequest(view, fragNum);

        return view;
    }


}

