package com.example.myfinancialassistant;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class User {
    public void UserAuthorization(String name, String password){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/author.php?login="+name+"&password="+password;
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    status =response.body().string();
                    String s1="0";
                    if (!s1.equals(status.trim())){
                        id=Integer.parseInt(status.trim());

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
    public void CreateUser(String name, String surname, String login, String password, int limit_size,int id){

    }
    public void UserRegistration(String name, String surname, String login, String password, int limit_size){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/register.php?login="+login+"&password="+password+"&name="+name+"&surname="+surname+"&limit_size="+limit_size;
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    status =response.body().string();


                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void GetUserData(int a){


        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/GetUserStatus.php?id="+String.valueOf(a);
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
                        status = jsonRoot.getString("status");
                        String s1="1";
                        if (s1.equals(status.trim())){
                            name=jsonRoot.getString("name");
                            surname=jsonRoot.getString("surname");
                            login=jsonRoot.getString("login");
                            limit_size=Integer.parseInt(jsonRoot.getString("limit_size"));
                            balance=Integer.parseInt(jsonRoot.getString("expenseSaldo"));
                            saldo=Integer.parseInt(jsonRoot.getString("expenseMont"));
                           // GetExpenseMont(a);
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
    public void NewPassword(String oldpassword, String newpassword, int k){
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/UpdateUserPassword.php?id="+String.valueOf(k)+"&password_old="+oldpassword+"&password_new1="+newpassword;
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    status =response.body().string();


                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void UpdateUserData(String new_name, String new_surname, int new_limit_size, int k){
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/UpdateUserData.php?id="+String.valueOf(k)+"&name="+new_name+"&surname="+new_surname
                +"&limit_size="+String.valueOf(new_limit_size);
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    status =response.body().string();


                }
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
    }
    }

    public User(){}
    public User(String name, String surname, String login, String password, int limit_size,int id) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.limit_size = limit_size;
        this.status="No";
        this.saldo=0;
        this.balance=0;
    }

    public int id;
    public int saldo;
    public int balance;
    public String name;
    public String status;
    public String surname;
    public String login;
    public String password;
    public int limit_size;

}

