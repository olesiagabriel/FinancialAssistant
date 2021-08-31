package com.example.myfinancialassistant.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myfinancialassistant.View.AuthorizationFragment;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.View.RegistrationFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_view, AuthorizationFragment.class, null)
                    .commit();
        }

    }
    public void openRegistrationFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, RegistrationFragment.class, null)
                .commit();
    }
    public void openAuthorizationFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container_view, AuthorizationFragment.class, null)
                .commit();
    }
    public void openCabinetFragment() {
        Intent intent = new Intent(this, BottomActivity.class);
        startActivity(intent);
    }
}