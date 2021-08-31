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

public class Expense_guide {
    private int Id;
    private String Expense_type;
    public Expense_guide(){};
    public String[] Expense_guide_list;
    private int User_Id_FK;
    public ArrayList<Expense_guide>  Expense_guide_list_object = new ArrayList<Expense_guide>();
    Expense_guide(int Id, String Expense_type, int User_Id_FK){
        this.Id= Id;
        this.Expense_type = Expense_type;
        this.User_Id_FK=User_Id_FK;
    }
    public String getExpense_type() {
        return this.Expense_type;
    }
    public void setExpense_type(String Expense_type) {
        this.Expense_type = Expense_type;
    }

    public int getId() {
        return this.Id;
    }
    public int getUser_Id_FK() {
        return this.User_Id_FK;
    }
    public void ExpenseGuideListSelect(int User_Id_FK){


        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/GetExpense_guide.php?User_Id_FK="+User_Id_FK;
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
                        JSONArray expense_guide = jsonRoot.getJSONArray("expense_guide");

                        Expense_guide_list=new String[(expense_guide.length())];
                        for (int i = 0; i < expense_guide.length(); i++) {
                            JSONObject c = expense_guide.getJSONObject(i);

                            int Expense_User_FK=c.getInt("User_Id_FK");
                            String Expense_type= c.getString("Expense_type");
                            Expense_guide_list[i]=Expense_type;
                            int myId= c.getInt("Id");
                            Expense_guide expense_guide1=new Expense_guide(myId,Expense_type, Expense_User_FK);
                            Expense_guide_list[i]=Expense_type;
                            Expense_guide_list_object.add(expense_guide1);

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
    public void deleteExpense_guide(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/deleteexpense_guide.php?id="+id;
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
    public void insertExpense_guide( String NameExpense_guide, int User_Id_FK){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/InsertExpense_guide.php?newname="+NameExpense_guide+"&User_Id_FK="+User_Id_FK;
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
