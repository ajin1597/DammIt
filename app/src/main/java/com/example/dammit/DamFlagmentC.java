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

public class DamFlagmentC extends Fragment {

    JsonReader jsonReader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_a_dam, container, false);
        int fragNum = 2;

        // JsonReader 클래스의 makeRequest매서드에 view와 json 데이터의 2번째 데이터를 불러오기 위한 index번호를 매게변수로 전송
        JsonReader.makeRequest(view, fragNum);

        return view;
    }


}

