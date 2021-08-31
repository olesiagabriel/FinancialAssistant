package com.example.myfinancialassistant.ViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myfinancialassistant.Model.Payment;
import com.example.myfinancialassistant.View.AddAmountPaymentFragment;
import com.example.myfinancialassistant.View.AddExpenseFragment;
import com.example.myfinancialassistant.View.AddExpense_guideFragment;
import com.example.myfinancialassistant.View.AddIncomeFragment;
import com.example.myfinancialassistant.View.AddIncome_guideFragment;
import com.example.myfinancialassistant.View.AddMonthlyPaymentFragment;
import com.example.myfinancialassistant.View.AddYearlyPaymentFragment;
import com.example.myfinancialassistant.View.UpdatePaymentFragment;
import com.example.myfinancialassistant.View.tab.CabinetTabFragment;
import com.example.myfinancialassistant.View.tab.StatisticsTabFragment;
import com.example.myfinancialassistant.View.tab.PaymentTabFragment;
import com.example.myfinancialassistant.View.PasswordFragment;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.View.tab.IncomeTabFragment;
import com.example.myfinancialassistant.View.tab.ExpenseTabFragment;
import com.example.myfinancialassistant.View.UpdateExpenseFragment;
import com.example.myfinancialassistant.View.UpdateIncomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import static android.telephony.AvailableNetworkInfo.PRIORITY_HIGH;

public class BottomActivity extends AppCompatActivity {

    private Object IncomeTabFragment;
    Button mChooseBtn;
    ImageView mImageView;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID=1;
    private static final String CHANNEL_ID="CHANNEL_ID";

    private static final int PERMISSION_CODE = 1001;
    private static final int IMAGE_PICK_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //  mChooseBtn = findViewById(R.id.choose_image_btn);
        mImageView = findViewById(R.id.imageView_1);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        pickImageFromGallery();
                    }
                } else {
                    pickImageFromGallery();
                }
            }
        });

        int k = ((MyApplication) this.getApplication()).getSomeVariable();
        Payment payment = new Payment();
        payment.selectNotifications(k);

        if (payment.Notification_list.size() > 0){
            String Payment = "";
        // String separator = System.getProperty("line.separator");
        for (int i = 0; i < payment.Notification_list.size(); i++) {
            Payment = Payment + payment.Notification_list.get(i).Payment_name + ":" + String.valueOf(payment.Notification_list.get(i).getExpense_size()) +
                    "руб. от "+payment.Notification_list.get(i).getPeriod_reminder()+"; ";
        }


        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), BottomActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setAutoCancel(false)
                        .setSmallIcon(R.drawable.ic_money)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .setContentTitle("Истекает срок действия платежа")
                        .setContentText(Payment)
                        .setPriority(PRIORITY_HIGH).setStyle(new NotificationCompat.BigTextStyle().bigText(Payment));

        createChannelIfNeeded(notificationManager);
        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
    }
        Payment payment2 = new Payment();
        payment2.selectNotificationsold(k);

        if (payment2.Notificationold_list.size() > 0) {
            String Payment2 = "";
            // String separator = System.getProperty("line.separator");
            for (int i = 0; i < payment2.Notificationold_list.size(); i++) {
                Payment2 = Payment2 + payment2.Notificationold_list.get(i).Payment_name + ":"
                        + String.valueOf(payment2.Notificationold_list.get(i).getExpense_size()) + "руб. от "
                        +payment2.Notificationold_list.get(i).getPeriod_reminder()+"; ";
            }

            notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent = new Intent(getApplicationContext(), BottomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                            .setAutoCancel(false)
                            .setSmallIcon(R.drawable.ic_money)
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(pendingIntent)
                            .setContentTitle("Истек срок действия платежа")
                            .setContentText(Payment2)
                            .setPriority(PRIORITY_HIGH).setStyle(new NotificationCompat.BigTextStyle().bigText(Payment2));

            createChannelIfNeeded(notificationManager);
            notificationManager.notify(2, notificationBuilder.build());
        }

    }

    public static void createChannelIfNeeded(NotificationManager manager){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID, CHANNEL_ID,NotificationManager.IMPORTANCE_DEFAULT );
            manager.createNotificationChannel(notificationChannel);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.CabinetTabFragment:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment, CabinetTabFragment.class, null)
                            .commit();
                    return true;
                case R.id.IncomeTabFragment:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment, IncomeTabFragment.class, null)
                            .commit();
                    return true;
                case R.id.ExpenseTabFragment:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment, ExpenseTabFragment.class, null)
                            .commit();
                    return true;
                case R.id.PaymentTabFragment:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment, PaymentTabFragment.class, null)
                            .commit();
                    return true;
                case R.id.StatisticsTabFragment:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment, StatisticsTabFragment.class, null)
                            .commit();
                    return true;
            }
            return false;
        }
};
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openPasswordFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, PasswordFragment.class, null)
                .commit();
    }
    public void openIncomeTabFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, IncomeTabFragment.class, null)
                .commit();
    }
    public void openPaymentTabFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, PaymentTabFragment.class, null)
                .commit();
    }
    public void openExpenseTabFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, ExpenseTabFragment.class, null)
                .commit();
    }
    public void openCabinetTabFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, CabinetTabFragment.class, null)
                .commit();
    }
    public void openAddIncomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, AddIncomeFragment.class, null)
                .commit();
    }
    public void openAddExpenseFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, AddExpenseFragment.class, null)
                .commit();
    }
    public void openAddAmountPaymentFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, AddAmountPaymentFragment.class, null)
                .commit();
    }
    public void openAddMonthlyPaymentFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, AddMonthlyPaymentFragment.class, null)
                .commit();
    }
    public void openAddYearlyPaymentFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, AddYearlyPaymentFragment.class, null)
                .commit();
    }
    public void openUpdateExpenseFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, UpdateExpenseFragment.class, null)
                .commit();
    }
    public void openUpdatePaymentFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, UpdatePaymentFragment.class, null)
                .commit();
    }
    public void openUpdateIncomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, UpdateIncomeFragment.class, null)
                .commit();
    }
    public void openAddIncome_guidefragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, AddIncome_guideFragment.class, null)
                .commit();
    }
    public void openAddExpense_guidefragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, AddExpense_guideFragment.class, null)
                .commit();
    }
    public void openStatisticsTabFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment, StatisticsTabFragment.class, null)
                .commit();
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    pickImageFromGallery();
                } else {

                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE)
            mImageView.setImageURI(data.getData());


    }



}