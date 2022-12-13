package com.example.dammit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

public class DamFlagmentB extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.flagment_b_dam,container,false);

        View view = inflater.inflate(R.layout.flagment_b_dam, container, false);


        img(view);


        return view;
    }

    public void img(View view) {

        ImageView sadImg;

        sadImg = view.findViewById(R.id.sad_image);
        DrawableImageViewTarget gifImage = new DrawableImageViewTarget(sadImg);
        Glide.with(this).load(R.raw.sad_face).into(sadImg);

    }

}

