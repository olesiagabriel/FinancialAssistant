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

public class Payment {
    private int Id;
    private int User_FK;
    private int Expense_type_FK;
    private String Expense_date_start;
    private String Expense_date_end;
    private int Expense_status;
    private int Payment_type_FK;
    public String Payment_name;
    public int Expense_size;
    public String Period_reminder;
    public String Expense_type_name;
    public ArrayList<Payment> Payment_list = new ArrayList<Payment>();
    public ArrayList<Payment> Notification_list = new ArrayList<Payment>();
    public ArrayList<Payment> Notificationold_list = new ArrayList<Payment>();
    public Payment(){};
    Payment(int Id, int Expense_type_FK,int User_FK,String Expense_date_start, String Expense_date_end,int Expense_status,
            int Payment_type_FK,String Payment_name,int Expense_size,String Period_reminder,String Expense_type_name){
        this.Id= Id;
        this.User_FK = User_FK;
        this.Expense_type_FK=Expense_type_FK;
        this.Expense_date_start=Expense_date_start;
        this.Expense_date_end=Expense_date_end;
        this.Expense_status=Expense_status;
        this.Payment_type_FK=Payment_type_FK;
        this.Payment_name=Payment_name;
        this.Expense_size=Expense_size;
        this.Period_reminder=Period_reminder;
        this.Expense_type_name=Expense_type_name;

    }

    public int getId() {
        return this.Id;
    }
    public int getUser_FK() {
        return this.User_FK;
    }
    public int getExpense_type_FK() {
        return this.Expense_type_FK;
    }
    public String getExpense_date_start() {
        return this.Expense_date_start;
    }
    public String getExpense_date_end() {
        return this.Expense_date_end;
    }
    public int getExpense_status() {
        return this.Expense_status;
    }
    public int getPayment_type_FK() {
        return this.Payment_type_FK;
    }
    public String getPayment_name() {
        return this.Payment_name;
    }
    public int getExpense_size() {
        return this.Expense_size;
    }
    public String getPeriod_reminder() {
        return this.Period_reminder;
    }
    public String getExpense_type_name() {
        return this.Expense_type_name;
    }
    public void getPayment(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/getpaymentid.php?id="+id;
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

                        Id=jsonRoot.getInt("Id_PK");
                        Expense_type_FK=jsonRoot.getInt("Expense_type_FK");
                        User_FK=jsonRoot.getInt("User_FK");
                        Expense_date_start=jsonRoot.getString("Expense_date_start");
                        Expense_date_end=jsonRoot.getString("Expense_date_end");
                        Expense_status=jsonRoot.getInt("Expense_status");
                        Payment_type_FK=jsonRoot.getInt("Payment_type_FK");
                        Expense_size=jsonRoot.getInt("Expense_size");
                        Payment_name=jsonRoot.getString("Payment_name");
                        Period_reminder=jsonRoot.getString("Period_reminder");
                        Expense_type_name=jsonRoot.getString("Expense_type");

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

    public void PaymentSelectCategory(int Id_user, int Id_payment){

        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/PaymentSelectCategory.php?Id_user="+Id_user+"&Id_payment="+Id_payment;
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
                        JSONArray payment = jsonRoot.getJSONArray("payment");
                        //String[] websites = new String[income_guide2.length()];

                        for(int i=0;i < payment.length();i++) {
                            String ss= payment.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Payment pay=new Payment(newOb.getInt("Id_PK"),
                                    newOb.getInt("Expense_type_FK"),
                                    newOb.getInt("User_FK"),
                                    newOb.getString("Expense_date_start"),
                                    newOb.getString("Expense_date_end"),
                                    newOb.getInt("Expense_status"),
                                    newOb.getInt("Payment_type_FK"),
                                    newOb.getString("Payment_name"),
                                    newOb.getInt("Expense_size"),
                                    newOb.getString("Period_reminder"),
                                    newOb.getString("Expense_type")
                            );
                            Payment_list.add(pay);
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

    public void deletePayment(int id){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/deletepayment.php?id="+id;
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

    public void updatePaymentAll(int id, String Expense_date_start, String Expense_date_end, String Payment_name, int Expense_size, String Period_reminder, String Expense_guide){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/UpdatePaymentAll.php?id="+id+"&Expense_date_start="+Expense_date_start+"&Expense_date_end="+Expense_date_end
                +"&Payment_name="+Payment_name+"&Expense_size="+Expense_size+"&Period_reminder="+Period_reminder+"&Expense_guide="+Expense_guide;
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

    public void updatePayment(int id,  int NewStatus){
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/UpdatePayment.php?id="+id+"&newstatus="+NewStatus;
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

    public void insertPayment(int User_FK, String Expense_date_start, String Expense_date_end, String Payment_name, int Expense_size, String Period_reminder, String Expense_guide, int Payment_type_FK ) {
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/NewPayment_add.php?User_FK="+User_FK+"&Expense_date_start="+Expense_date_start+
                "&Expense_date_end="+Expense_date_end+"&Payment_name="+Payment_name+"&Expense_size="+Expense_size+
                "&Period_reminder="+Period_reminder+"&Expense_guide="+Expense_guide+"&Payment_type_FK="+Payment_type_FK;

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

    public void selectNotifications(int User_FK ) {
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/Notifications.php?User_FK="+User_FK;
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
                        JSONArray payment = jsonRoot.getJSONArray("Notifications");
                        //String[] websites = new String[income_guide2.length()];

                        for(int i=0;i < payment.length();i++) {
                            String ss= payment.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Payment pay=new Payment(newOb.getInt("Id_PK"),
                                    newOb.getInt("Expense_type_FK"),
                                    newOb.getInt("User_FK"),
                                    newOb.getString("Expense_date_start"),
                                    newOb.getString("Expense_date_end"),
                                    newOb.getInt("Expense_status"),
                                    newOb.getInt("Payment_type_FK"),
                                    newOb.getString("Payment_name"),
                                    newOb.getInt("Expense_size"),
                                    newOb.getString("Period_reminder"),
                                    ""
                            );
                            Notification_list.add(pay);
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
    public void selectNotificationsold(int User_FK ) {
        final String[] s = {""};
        OkHttpClient client=new OkHttpClient();
        String url="http://10.0.2.2:90/1/Notificationsold.php?User_FK="+User_FK;
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
                        JSONArray payment = jsonRoot.getJSONArray("Notifications");
                        //String[] websites = new String[income_guide2.length()];

                        for(int i=0;i < payment.length();i++) {
                            String ss= payment.getString(i);
                            JSONObject newOb = new JSONObject(ss);
                            Payment pay=new Payment(newOb.getInt("Id_PK"),
                                    newOb.getInt("Expense_type_FK"),
                                    newOb.getInt("User_FK"),
                                    newOb.getString("Expense_date_start"),
                                    newOb.getString("Expense_date_end"),
                                    newOb.getInt("Expense_status"),
                                    newOb.getInt("Payment_type_FK"),
                                    newOb.getString("Payment_name"),
                                    newOb.getInt("Expense_size"),
                                    newOb.getString("Period_reminder"),
                                    ""
                            );
                            Notificationold_list.add(pay);
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
