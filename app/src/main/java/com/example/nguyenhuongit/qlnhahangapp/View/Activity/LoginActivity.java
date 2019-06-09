package com.example.nguyenhuongit.qlnhahangapp.View.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyenhuongit.qlnhahangapp.R;

public class LoginActivity extends AppCompatActivity {

    Button btn_DangNhap;
    EditText edt_signin_username,edt_signin_password;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        edt_signin_username = findViewById(R.id.edt_signin_username);
        edt_signin_password = findViewById(R.id.edt_signin_password);


//        Log.d("username1", edt_signin_username.getText().toString());
//        Log.d("password1",edt_signin_password.getText().toString());
        btn_DangNhap = findViewById(R.id.btn_DangNhap);
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_signin_username.getText().toString();
                String password = edt_signin_password.getText().toString();

                if(username.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Bạn chưa nhập thông tin!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(username.equals("admin") && password.equals("admin")) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo!!!");
        builder.setMessage("Tên tài khoản hoặc mật khẩu không đúng!");
        builder.setCancelable(false);

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
