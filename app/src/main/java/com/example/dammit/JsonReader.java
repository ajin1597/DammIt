package com.example.dammit;

import static com.example.dammit.DamFlagmentA.rq;
//import static com.example.dammit.DamFlagmentB.rq;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Timer;
import java.util.TimerTask;

public class JsonReader {

    public static String waterLv = null;
    public static String light = null;
    public static String workNum = null;
    public static String upTime = null;
    public static String damName = null;

    static ImageView smailImg;
    static ImageView nomalImg;
    static ImageView sadImg;
    static ImageView sunImg;
    static ImageView cloudImg;
    static ImageView moonImg;
    static ImageView workImg;

    public static void makeRequest(View view, int fragNum) {

        Log.i("Dammit", String.valueOf(fragNum));

        String url = "http://61.103.243.188:8080/main/damGet";
        TextView textUT = view.findViewById(R.id.textUpTime);
        TextView textDN = view.findViewById(R.id.textDamName);
        TextView textWTLV = view.findViewById(R.id.textWaterLV);
        TextView textLight = view.findViewById(R.id.lightSensorText);
        TextView textWork = view.findViewById(R.id.workSersorText);
        TextView textSys1 = view.findViewById(R.id.systemText1);
        TextView textSys2 = view.findViewById(R.id.systemText2);


//        TextView waterT = view.findViewById(R.id.LVtext);
        ResponseData responseData;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {


                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //응답
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("dam");
                            Gson gson = new Gson();


                            // API 0번째(1번째 댐) Json 데이터
                            JSONObject item = (JSONObject) dataArray.get(fragNum);
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

                            String timeStr = upTime;
                            String hourStr = timeStr.substring(11, 13);
//                    System.out.println(hourStr);

                            // 00 ~ 11시 까지 AM || 12 ~ 23시 까지 PM
                            if (Integer.parseInt(hourStr) < 12) {
                                textUT.setText(upTime + " AM");
                            } else {
                                textUT.setText(upTime + " PM");
                            }

                            // 06 ~ 18시 까지 sun이미지 || 19 ~ 05시 까지 moon이미지
                            if (Integer.parseInt(hourStr) >= 6 && 18 >= Integer.parseInt(hourStr)) {
                                sunImg = view.findViewById(R.id.lightSensorImg);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(sunImg);
                                Glide.with(view).load(R.raw.sun).into(sunImg);

                                // 06 ~ 18시 사이에 조도센서 수치가 높으면 구름이미지로 변경
                                if (Integer.parseInt(light) >= 100) {
                                    cloudImg = view.findViewById(R.id.lightSensorImg);
                                    DrawableImageViewTarget Image2 = new DrawableImageViewTarget(cloudImg);
                                    Glide.with(view).load(R.raw.cloud).into(cloudImg);
                                }
                            } else {
                                moonImg = view.findViewById(R.id.lightSensorImg);
                                DrawableImageViewTarget Image2 = new DrawableImageViewTarget(moonImg);
                                Glide.with(view).load(R.raw.moon).into(moonImg);
                            }


                            // 댐 수위에 맞는 경고 문구 및 이미지
                            if (Integer.parseInt(waterLv) > 950) {
                                //위험
                                sadImg = view.findViewById(R.id.select_face_img);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(sadImg);
                                Glide.with(view).load(R.raw.sad_face).into(sadImg);

                                textSys1.setText("System : 댐 수위 위험 수치 도달!");
                                textSys2.setText("System : 근무인원들은 상황 조치 및 대피준비!");
                            } else if (949 > Integer.parseInt(waterLv) && Integer.parseInt(waterLv) > 850) {
                                //경고
                                nomalImg = view.findViewById(R.id.select_face_img);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(nomalImg);
                                Glide.with(view).load(R.raw.nomal_face).into(nomalImg);

                                textSys1.setText("System : 댐 수위 경고 수치 도달!");
                                textSys2.setText("System : 근무인원들은 예외 상황 준비");
                            } else {
                                //안전
                                smailImg = view.findViewById(R.id.select_face_img);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(smailImg);
                                Glide.with(view).load(R.raw.smail_face).into(smailImg);

                                textSys1.setText("System : 댐 수위 평균 수치 유지중!");
                                textSys2.setText("System : `-");
                            }

                            workImg = view.findViewById(R.id.workSensorImg);
                            DrawableImageViewTarget Image3 = new DrawableImageViewTarget(workImg);
                            Glide.with(view).load(R.raw.worker).into(workImg);

//                          0 ~ 1000 950위험, 850경고
//                          빛이 80미만 없으면 100 이상

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
                    rq.add(stringRequest);
                } catch (Exception e) {
                    System.out.println(e + "112312331321");
                }

                System.out.println("이진우이진우이진우이진우이진우이진우이진우이진우이진우이진우이진우이진우이진우");
            }
        };
        timer.schedule(timerTask, 0, 5 * 1000); //Timer 실행

    }
}
