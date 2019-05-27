package com.example.nguyenhuongit.qlnhahangapp.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.nguyenhuongit.qlnhahangapp.R;

public class LoginActivity extends AppCompatActivity {

    Button btnDangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        Mapping();
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Mapping() {
        btnDangnhap = findViewById(R.id.btnDangNhap);
    }
}
