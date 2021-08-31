package com.example.myfinancialassistant.Model;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Period_type {
    private int Id;
    private String Period_type;
    public Period_type(){};
    public String[] Period_type_list;
    Period_type(int Id, String Payment_type){
        this.Id= Id;
        this.Period_type = Period_type;
    }

    public int getId() {
        return this.Id;
    }
    public String getPeriod_type() {
        return this.Period_type;
    }
    public void Period_typeListSelect(){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/GetPeriod_type.php";
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String json_response  =response.body().string();
                    try {
                        JSONObject jsonRoot = new JSONObject(json_response);

                        // Getting JSON Array node
                        JSONArray Period_type = jsonRoot.getJSONArray("Period_type");

                        Period_type_list=new String[(Period_type.length())];
                        for (int i = 0; i < Period_type.length(); i++) {
                            JSONObject c = Period_type.getJSONObject(i);

                            String Income_type= c.getString("Name");
                            int myId= c.getInt("Id");
                            Period_type_list[i]=Income_type;


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
