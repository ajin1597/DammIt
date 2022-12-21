package com.example.dammit;

import static com.example.dammit.MainActivity.rq;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class JsonReader {
//    boolean 이진우왔다감 = true;
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
        TextView riskLevel = view.findViewById(R.id.riskLevel);
        TextView textSys2 = view.findViewById(R.id.waterWarningText);
        TextView lightMsgText = view.findViewById(R.id.lightWarningText);

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

                            // API Json fragNum(int)번째 데이터
                            JSONObject item = (JSONObject) dataArray.get(fragNum);

                            ResponseData gsonObj = gson.fromJson(item.toString(), ResponseData.class);
                            waterLv = gsonObj.getWaterLevel();
                            light = gsonObj.getLight();
                            workNum = gsonObj.getWorkNmpr();
                            upTime = gsonObj.getUpdtDt();
                            damName = gsonObj.getDamName();

                            textDN.setText(damName);
                            textLight.setText("Light : " + light + "Lx");
                            textWork.setText(workNum + "명 근무중");

                            DecimalFormat df = new DecimalFormat("0.0");
                            String waterLvStr = waterLv;
                            String resultWaterLV = df.format( Double.parseDouble(waterLvStr) / 10.0);
                            Log.e("Dammit", resultWaterLV);
                            textWTLV.setText( "현재 수위는 " + resultWaterLV + "% 입니다.");

                            String timeStr = upTime;
                            String hourStr = timeStr.substring(11, 13);

                            // 시간 변환 switch문 Ex) 13시 -> 01
                            String changeStr = null;
                            switch(hourStr) {
                                case "13" :
                                    changeStr = timeStr.replace(" 13:" , " 01:");
                                    break;
                                case "14" :
                                    changeStr = timeStr.replace(" 14:" , " 02:");
                                    break;
                                case "15" :
                                    changeStr = timeStr.replace(" 15:" , " 03:");
                                    break;
                                case "16" :
                                    changeStr = timeStr.replace(" 16:" , " 04:");
                                    break;
                                case "17" :
                                    changeStr = timeStr.replace(" 17:" , " 05:");
                                    break;
                                case "18" :
                                    changeStr = timeStr.replace(" 18:" , " 06:");
                                    break;
                                case "19" :
                                    changeStr = timeStr.replace(" 19:" , " 07:");
                                    break;
                                case "20" :
                                    changeStr = timeStr.replace(" 20:" , " 08:");
                                    break;
                                case "21" :
                                    changeStr = timeStr.replace(" 21:" , " 09:");
                                    break;
                                case "22" :
                                    changeStr = timeStr.replace(" 22:" , " 10:");
                                    break;
                                case "23" :
                                    changeStr = timeStr.replace(" 23:" , " 11:");
                                    break;
                            }

                            // textView 깜빡이는 animation 추가 & 적용
                            // target -> 선택한 fragment context
                            Animation startAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.blink_anim);
                            // 00 ~ 11시 까지 AM || 12 ~ 23시 까지 PM
                            if (Integer.parseInt(hourStr) < 12) {
                                textUT.setText(timeStr + " AM");
                                textUT.startAnimation(startAnimation);

                            } else {
                                textUT.setText(changeStr + " PM");
                                textUT.startAnimation(startAnimation);
                            }

                            // 06 ~ 18시 까지 sun이미지 || 19 ~ 05시 까지 moon이미지
                            // .load((Drawable) null).fallback(R.drawable.(xmlName)) png가 아닌 xml파일 적용하는 코드
                            if (Integer.parseInt(hourStr) >= 6 && 18 >= Integer.parseInt(hourStr)) {
                                sunImg = view.findViewById(R.id.lightSensorImg);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(sunImg);
                                Glide.with(view).load((Drawable) null).fallback(R.drawable.sun).into(sunImg);

                                lightMsgText.setText("맑음");

                                // 06 ~ 18시 사이에 조도센서 수치가 높으면 구름이미지로 변경
                                if (Integer.parseInt(light) >= 100) {
                                    cloudImg = view.findViewById(R.id.lightSensorImg);
                                    DrawableImageViewTarget Image2 = new DrawableImageViewTarget(cloudImg);
                                    Glide.with(view).load((Drawable)null).fallback(R.drawable.cloudy).into(cloudImg);

                                    lightMsgText.setText("흐림");
                                }
                            } else {
                                moonImg = view.findViewById(R.id.lightSensorImg);
                                DrawableImageViewTarget Image2 = new DrawableImageViewTarget(moonImg);
                                Glide.with(view).load((Drawable) null).fallback(R.drawable.moon).into(moonImg);

                                lightMsgText.setText("어두움");
                            }

                            // 댐 수위에 맞는 경고 문구 및 이미지
                            if (Integer.parseInt(waterLv) > 950) {
                                //위험
                                sadImg = view.findViewById(R.id.select_face_img);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(sadImg);
                                Glide.with(view).load((Drawable) null).fallback(R.drawable.angry).into(sadImg);

                                riskLevel.setText("등급 : 위험");
                                textSys2.setText("근무인원들은 상황 조치 및 대피준비!");
                            } else if (949 > Integer.parseInt(waterLv) && Integer.parseInt(waterLv) > 850) {
                                //경고
                                nomalImg = view.findViewById(R.id.select_face_img);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(nomalImg);
                                Glide.with(view).load((Drawable) null).fallback(R.drawable.sad).into(nomalImg);

                                riskLevel.setText("등급 : 경고");
                                textSys2.setText("근무인원들은 예외 상황 준비");
                            } else {
                                //안전
                                smailImg = view.findViewById(R.id.select_face_img);
                                DrawableImageViewTarget Image1 = new DrawableImageViewTarget(smailImg);
                                Glide.with(view).load((Drawable) null).fallback(R.drawable.happy).into(smailImg);

                                riskLevel.setText("등급 : 안전");
                                textSys2.setText("-");
                            }

                            workImg = view.findViewById(R.id.workSensorImg);
                            DrawableImageViewTarget Image3 = new DrawableImageViewTarget(workImg);
                            Glide.with(view).load((Drawable) null).fallback(R.drawable.work).into(workImg);

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
        // 5초마다 timer 실행
        timer.schedule(timerTask, 0, 5 * 1000);
    }
}
