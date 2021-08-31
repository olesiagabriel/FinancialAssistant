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

public class Expense {
    private int Id;
    private String Expense_name;
    private String Expense_guide;
    private int Expense_guide_id;
    private int Expense_size;
    private String Expense_date;
    private int sum_size;
    public int sum_report=0;
    public ArrayList<Expense>  Expense_list = new ArrayList<Expense>();
    public Expense(){};
    Expense(int Id, String Expense_guide, int Expense_size, String Expense_date, String Expense_name,int Expense_guide_id){
        this.Id= Id;
        this.Expense_guide = Expense_guide;
        this.Expense_size=Expense_size;
        this.Expense_date=Expense_date;
        this.Expense_name=Expense_name;
        this.Expense_guide_id=Expense_guide_id;


    }
    public int getId() {
        return this.Id;
    }
    public String getExpense_guide() {
        return this.Expense_guide;
    }
    public int getExpense_size() {
        return this.Expense_size;
    }
    public String getExpense_date() {
        return this.Expense_date;
    }
    public String getExpense_name() { return this.Expense_name; }
    public int getExpense_guide_id() {
        return this.Expense_guide_id;
    }

    public void setExpense_guide(String Expense_guide) {
        this.Expense_guide = Expense_guide;
    }
    public void setExpense_size(int Expense_size) {
        this.Expense_size = Expense_size;
    }
    public void setExpense_date(String Expense_date) {
        this.Expense_date = Expense_date;
    }
    public void setExpense_name(String Expense_name) {
        this.Expense_name = Expense_name;
    }
    public void setExpense_guide_id(int Expense_guide_id) { this.Expense_guide_id = Expense_guide_id; }

    public int getsum_size() { return this.sum_size; }
    public void setsum_size(int sum_size) {
        this.sum_size = sum_size;
    }

    public void ExpensePeriodSelect(int Id_user, String start, String stop){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/ExpensePeriodSelect.php?Id_user="+Id_user+"&start="+start+"&stop="+stop;
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
                        JSONArray expense_guide2 = jsonRoot.getJSONArray("expense_period");
                        String[] websites = new String[expense_guide2.length()];

                        for(int i=0;i < expense_guide2.length();i++) {
                            String ss= expense_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Expense exp= new Expense(newOb.getInt("Id"),
                                    newOb.getString("Expense_type"),
                                    newOb.getInt("Expense_size"),
                                    newOb.getString("Expense_date"),
                                    newOb.getString("Expense_name"),
                                    newOb.getInt("Expense_guide_FK")
                            );
                            Expense_list.add(exp);
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

    public void ExpensePeriodSelectCharts(int Id_user, String start, String stop){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/ExpensePeriodSelectCharts.php?Id_user="+Id_user+"&start="+start+"&stop="+stop;
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
                        JSONArray expense_guide2 = jsonRoot.getJSONArray("expense_period");
                        String[] websites = new String[expense_guide2.length()];

                        for(int i=0;i < expense_guide2.length();i++) {
                            String ss= expense_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Expense exp= new Expense(newOb.getInt("Id"),
                                    newOb.getString("Expense_type"),
                                    newOb.getInt("Expense_size"),
                                    newOb.getString("Expense_date"),
                                    newOb.getString("Expense_name"),
                                    newOb.getInt("Expense_guide_FK")
                            );

                            exp.setsum_size(newOb.getInt("mysum"));
                            Expense_list.add(exp);
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
    public void ExpensePeriodSelectGuide(int Id_user, String start, String stop, String guide){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/ExpensePeriodSelectGuide.php?Id_user="+Id_user+"&start="+start+"&stop="+stop+"&guide="+guide;
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
                        JSONArray expense_guide2 = jsonRoot.getJSONArray("expense_period");
                       // String[] websites = new String[expense_guide2.length()];

                        for(int i=0;i < expense_guide2.length();i++) {
                            String ss= expense_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Expense exp= new Expense(newOb.getInt("Id"),
                                    newOb.getString("Expense_type"),
                                    newOb.getInt("Expense_size"),
                                    newOb.getString("Expense_date"),
                                    newOb.getString("Expense_name"),
                                    newOb.getInt("Expense_guide_FK")
                            );
                            Expense_list.add(exp);
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
    public void ExpensePeriodSelectGuideReport(int Id_user, String start, String stop, String guide){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();

        String url="http://10.0.2.2:90/1/ExpensePeriodSelectGuideReport.php?Id_user="+Id_user+"&start="+start+"&stop="+stop+"&guide="+guide;
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

                        //ЭТА ЧАСТЬ ВЫЛЕТАЕТ ПРОВЕРИТЬ!
                       /* JSONArray income_guide3 = jsonRoot.getJSONArray("all_sum2");
                        String ss3= income_guide3.getString(0);
                        JSONObject newOb3 = new JSONObject(ss3);
                        sum_report=newOb3.getInt("all_sum");*/

                        JSONArray expense_guide2 = jsonRoot.getJSONArray("expense_period");
                        //String[] websites = new String[income_guide2.length()];

                        for(int i=0;i < expense_guide2.length();i++) {
                            String ss= expense_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Expense ex;
                            if(guide.equals("")){

                                ex= new Expense(newOb.getInt("Id"),
                                        "",
                                        newOb.getInt("Expense_size"),
                                        newOb.getString("Expense_date"),
                                        newOb.getString("Expense_name"),
                                        newOb.getInt("Expense_guide_FK")
                                );
                            }else{
                                ex= new Expense(newOb.getInt("Id"),
                                        newOb.getString("Expense_type"),
                                        newOb.getInt("Expense_size"),
                                        newOb.getString("Expense_date"),
                                        newOb.getString("Expense_name"),
                                        newOb.getInt("Expense_guide_FK")
                                );}
                            sum_report+=ex.Expense_size;
                            Expense_list.add(ex);
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
    public void deleteExpense(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/deleteexpense.php?id="+id;
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
    public void getExpense(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/getexpenseid.php?id="+id;
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
                        Expense_name = jsonRoot.getString("Expense_name");
                        Expense_guide=jsonRoot.getString("Expense_type");
                        Expense_guide_id=jsonRoot.getInt("Expense_guide_FK");
                        Expense_size=jsonRoot.getInt("Expense_size");
                        Expense_date=jsonRoot.getString("Expense_date");

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

    public void insertExpense(int id_user, String NameExpense, int SizeExpense, String DateExpense, String spinner3){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/NewExpense_add.php?id="+id_user+"&name_expense="+NameExpense+"&category="+spinner3
                +"&summa="+SizeExpense+"&thisdate="+DateExpense;
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
    public void updateExpense(int id, String NameExpense, int SizeExpense, String DateExpense, String spinner3){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/UpdateExpense.php?id="+id+"&name_expense="+NameExpense+"&category="+spinner3
                +"&summa="+SizeExpense+"&thisdate="+DateExpense;
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
