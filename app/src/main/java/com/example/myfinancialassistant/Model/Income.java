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

public class Income {
    private int Id;
    private String Income_name;
    private String Income_guide;
    private int Income_guide_id;
    private int Income_size;
    private String Income_date;
    private int sum_size;
    public int sum_report=0;
    public ArrayList<Income>  Income_list = new ArrayList<Income>();
    public Income(){};
    Income(int Id, String Income_guide, int Income_size, String Income_date, String Income_name,int Income_guide_id){
        this.Id= Id;
        this.Income_guide = Income_guide;
        this.Income_size=Income_size;
        this.Income_date=Income_date;
        this.Income_name=Income_name;
        this.Income_guide_id=Income_guide_id;

    }
    public int getId() {
        return this.Id;
    }
    public String getIncome_guide() {
        return this.Income_guide;
    }
    public int getIncome_size() {
        return this.Income_size;
    }
    public String getIncome_date() {
        return this.Income_date;
    }
    public String getIncome_name() { return this.Income_name; }
    public int getIncome_guide_id() {
        return this.Income_guide_id;
    }

    public void setIncome_guide(String Income_guide) {
        this.Income_guide = Income_guide;
    }
    public void setIncome_size(int Income_size) {
        this.Income_size = Income_size;
    }
    public void setIncome_date(String Income_date) {
        this.Income_date = Income_date;
    }
    public void setIncome_name(String Income_name) {
        this.Income_name = Income_name;
    }
    public void setIncome_guide_id(int Income_guide_id) {
        this.Income_guide_id = Income_guide_id;
    }

    public int getsum_size() { return this.sum_size; }
    public void setsum_size(int sum_size) {
        this.sum_size = sum_size;
    }

    public void IncomePeriodSelect(int Id_user, String start, String stop){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/IncomePeriodSelect.php?Id_user="+Id_user+"&start="+start+"&stop="+stop;
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
                        JSONArray income_guide2 = jsonRoot.getJSONArray("income_period");
                        String[] websites = new String[income_guide2.length()];

                        for(int i=0;i < income_guide2.length();i++) {
                            String ss= income_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Income inc=new Income(newOb.getInt("Id"),
                                    newOb.getString("Income_type"),
                                    newOb.getInt("Income_size"),
                                    newOb.getString("Income_date"),
                                    newOb.getString("Income_name"),
                                    newOb.getInt("Income_guide_FK")
                            );
                            Income_list.add(inc);
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

    public void IncomePeriodSelectCharts(int Id_user, String start, String stop){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/IncomePeriodSelectCharts.php?Id_user="+Id_user+"&start="+start+"&stop="+stop;
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
                        JSONArray income_guide2 = jsonRoot.getJSONArray("income_period");
                        //String[] websites = new String[expense_guide2.length()];

                        for(int i=0;i < income_guide2.length();i++) {
                            String ss= income_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Income inc= new Income(newOb.getInt("Id"),
                                    newOb.getString("Income_type"),
                                    newOb.getInt("Income_size"),
                                    newOb.getString("Income_date"),
                                    newOb.getString("Income_name"),
                                    newOb.getInt("Income_guide_FK")
                            );

                            inc.setsum_size(newOb.getInt("mysum"));
                            Income_list.add(inc);
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
    public void IncomePeriodSelectGuide(int Id_user, String start, String stop, String guide){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/IncomePeriodSelectGuide.php?Id_user="+Id_user+"&start="+start+"&stop="+stop+"&guide="+guide;
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
                        JSONArray income_guide2 = jsonRoot.getJSONArray("income_period");
                        //String[] websites = new String[income_guide2.length()];

                        for(int i=0;i < income_guide2.length();i++) {
                            String ss= income_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Income inc= new Income(newOb.getInt("Id"),
                                    newOb.getString("Income_type"),
                                    newOb.getInt("Income_size"),
                                    newOb.getString("Income_date"),
                                    newOb.getString("Income_name"),
                                    newOb.getInt("Income_guide_FK")
                            );
                            Income_list.add(inc);
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

    public void IncomePeriodSelectGuideReport(int Id_user, String start, String stop, String guide){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();

        String url="http://10.0.2.2:90/1/IncomePeriodSelectGuideReport.php?Id_user="+Id_user+"&start="+start+"&stop="+stop+"&guide="+guide;
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

                        JSONArray income_guide2 = jsonRoot.getJSONArray("income_period");
                        //String[] websites = new String[income_guide2.length()];

                        for(int i=0;i < income_guide2.length();i++) {
                            String ss= income_guide2.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Income inc;
                            if(guide.equals("")){

                                 inc= new Income(newOb.getInt("Id"),
                                       "",
                                        newOb.getInt("Income_size"),
                                        newOb.getString("Income_date"),
                                        newOb.getString("Income_name"),
                                         newOb.getInt("Income_guide_FK")
                                );

                            }else{
                             inc= new Income(newOb.getInt("Id"),
                                    newOb.getString("Income_type"),
                                    newOb.getInt("Income_size"),
                                    newOb.getString("Income_date"),
                                    newOb.getString("Income_name"),
                                    newOb.getInt("Income_guide_FK")
                            );}
                            sum_report+=inc.Income_size;
                            Income_list.add(inc);
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
    public void deleteIncome(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/deleteincome.php?id="+id;
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

    public void getIncome(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/getincomeid.php?id="+id;
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
                        Income_name = jsonRoot.getString("Income_name");
                        Income_guide=jsonRoot.getString("Income_type");
                        Income_guide_id=jsonRoot.getInt("Income_guide_FK");
                        Income_size=jsonRoot.getInt("Income_size");
                        Income_date=jsonRoot.getString("Income_date");

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

    public void insertIncome(int id_user, String NameIncome, int SizeIncome, String DateIncome, String spinner2){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/NewIncome_add.php?id="+id_user+"&name_income="+NameIncome+"&category="+spinner2
                +"&summa="+SizeIncome+"&thisdate="+DateIncome;
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

    public void updateIncome(int id, String NameIncome, int SizeIncome, String DateIncome, String spinner2){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/UpdateIncome.php?id="+id+"&name_income="+NameIncome+"&category="+spinner2
                +"&summa="+SizeIncome+"&thisdate="+DateIncome;
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
