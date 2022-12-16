package com.example.dammit;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class DamFlagmentA extends Fragment {

    public static RequestQueue rq = null;
    public static String waterLv = null;
    public static String light = null;
    public static String workNum = null;
    public static String upTime = null;
    public static String damName = null;
    public static String warningT = null;

    ImageView smailImg;
    ImageView nomalImg;
    ImageView sadImg;
    ImageView lightImg;
    ImageView workImg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flagment_a_dam, container, false);

        if (rq == null) {
            rq = Volley.newRequestQueue(getContext());
        }

        img(view);
        makeRequest(view);

        return view;
    }

    //    @Nullable
    public void makeRequest(View view) {

        String url = "http://61.103.243.188:8080/main/damGet";
        TextView textUT = view.findViewById(R.id.textUpTime);
        TextView textDN = view.findViewById(R.id.textDamName);
        TextView textWTLV = view.findViewById(R.id.textWaterLV);
        TextView textLight = view.findViewById(R.id.lightSensorText);
        TextView textWork = view.findViewById(R.id.workSersorText);
//        TextView textWarning = view.findViewById(R.id.warningText);



//        TextView waterT = view.findViewById(R.id.LVtext);
        ResponseData responseData;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //응답
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray dataArray = jsonObject.getJSONArray("dam");
                    Gson gson = new Gson();



                    JSONObject item = (JSONObject) dataArray.get(1);
                    ResponseData gsonObj = gson.fromJson(item.toString(), ResponseData.class);
                    waterLv = gsonObj.getWaterLevel();
                    light = gsonObj.getLight();
                    workNum = gsonObj.getWorkNmpr();
                    upTime = gsonObj.getUpdtDt();
                    damName = gsonObj.getDamName();



                    textDN.setText(damName);
                    textWTLV.setText(waterLv + " / 1000");
                    textLight.setText(light + "lx");
                    textWork.setText(workNum + "명 근무중");
//                    waterT.setText(textspan);

                    String timeStr = upTime;
                    String hourStr = timeStr.substring(11, 13);
                    System.out.println(hourStr);

                    // 00 ~ 11시 까지 AM || 12 ~ 23시 까지 PM
                    if (Integer.parseInt(hourStr) < 12) {
                        textUT.setText(upTime + " AM");
                    } else {
                        textUT.setText(upTime + " PM");
                    }

                    // 댐 수위에 맞는 경고 문구 및 이미지
                    if (Integer.parseInt(waterLv) > 950) {
                        //위험
                        sadImg = view.findViewById(R.id.select_face_img);
                        DrawableImageViewTarget Image1 = new DrawableImageViewTarget(sadImg);
                        Glide.with(getActivity()).load(R.raw.sad_face).into(sadImg);

//                        textWarning.setText("댐 수위 위험 수치 도달!");
                    } else if (949 > Integer.parseInt(waterLv) && Integer.parseInt(waterLv) > 850) {
                        //경고
                        nomalImg = view.findViewById(R.id.select_face_img);
                        DrawableImageViewTarget Image1 = new DrawableImageViewTarget(nomalImg);
                        Glide.with(getActivity()).load(R.raw.nomal_face).into(nomalImg);

//                        textWarning.setText("댐 수위 경고 수치 도달!");
                    } else {
                        //안전
                        smailImg = view.findViewById(R.id.select_face_img);
                        DrawableImageViewTarget Image1 = new DrawableImageViewTarget(smailImg);
                        Glide.with(getActivity()).load(R.raw.smail_face).into(smailImg);

//                        textWarning.setText("댐 수위 평균 수치 유지중!");
                    }
//★★★★★★★★★★★★★★★★★★★★★★★★★이미지 맞게 넣으세요
//                    0 ~ 1000 950위험, 850경고

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //에러
                textUT.setText("에러: " + error.toString());
            }
        });
        try {
            if (rq.add(stringRequest) == null) {
            }
        } catch (Exception e) {
            System.out.println(e + "112312331321");
        }
    }

    public void img(View view) {


//
//
//        smailImg = view.findViewById(R.id.select_face_img);
//        DrawableImageViewTarget Image1 = new DrawableImageViewTarget(smailImg);
//        Glide.with(this).load(R.raw.smail_face).into(smailImg);

        lightImg = view.findViewById(R.id.lightSensorImg);
        DrawableImageViewTarget Image2 = new DrawableImageViewTarget(lightImg);
        Glide.with(this).load(R.raw.flash).into(lightImg);

        workImg = view.findViewById(R.id.workSensorImg);
        DrawableImageViewTarget Image3 = new DrawableImageViewTarget(workImg);
        Glide.with(this).load(R.raw.worker).into(workImg);



    }

}



