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

public class Income_guide {
    private int Id;
    private String Income_type;
    public Income_guide(){};
    public String[] Income_guide_list;
    private int User_Id_FK;
    public ArrayList<Income_guide>  Income_guide_list_object = new ArrayList<Income_guide>();
    Income_guide(int Id, String Income_type, int User_Id_FK){
        this.Id= Id;
        this.Income_type = Income_type;
        this.User_Id_FK=User_Id_FK;
    }


    public String getIncome_type() {
        return this.Income_type;
    }
    public void setIncome_type(String Income_type) {
        this.Income_type = Income_type;
    }
    public int getId() {
        return this.Id;
    }
    public int getUser_Id_FK() {
        return this.User_Id_FK;
    }
    public void IncomeGuideListSelect(int User_Id_FK){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/GetIncome_guide.php?User_Id_FK="+User_Id_FK;
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
                        JSONArray income_guide = jsonRoot.getJSONArray("income_guide");

                        Income_guide_list=new String[(income_guide.length())];
                        for (int i = 0; i < income_guide.length(); i++) {
                            JSONObject c = income_guide.getJSONObject(i);

                            int Income_User_FK=c.getInt("User_Id_FK");
                            String Income_type= c.getString("Income_type");
                            int myId= c.getInt("Id");
                            Income_guide income_guide1=new Income_guide(myId,Income_type, Income_User_FK);
                            Income_guide_list[i]=Income_type;
                            Income_guide_list_object.add(income_guide1);

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
    public void deleteIncome_guide(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/deleteincome_guide.php?id="+id;
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){


                }
            }
        });
    }

    public void insertIncome_guide( String NameIncome_guide,int User_Id_FK){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/InsertIncome_guide.php?newname="+NameIncome_guide+"&User_Id_FK="+User_Id_FK;
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){


                }
            }
        });

    }
}


