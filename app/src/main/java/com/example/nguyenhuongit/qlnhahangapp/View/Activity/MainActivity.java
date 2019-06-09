package com.example.nguyenhuongit.qlnhahangapp.View.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nguyenhuongit.qlnhahangapp.R;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentDoanhThu;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentDoanhThuKV;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentGioiThieu;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentHome;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentKhachHang;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentMonAn;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentThucUong;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentNguyenLieu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentMonAn.OnFragmentInteractionListener,
        FragmentThucUong.OnFragmentInteractionListener, FragmentDoanhThu.OnFragmentInteractionListener, FragmentNguyenLieu.OnFragmentInteractionListener ,
        FragmentHome.OnFragmentInteractionListener, FragmentKhachHang.OnFragmentInteractionListener, FragmentGioiThieu.OnFragmentInteractionListener{
    Button btn_Logout;
    Button btn_MonAnBanChay, btn_ThucUongBanChay, btn_DoanhThu, btn_KhachHang,btn_NguyenLieu,btn_DoanhThuKhuVuc;
    Toolbar toolbar;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        setSupportActionBar(toolbar);
        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo!");
                builder.setMessage("Bạn có muốn đăng xuất không?");
                builder.setCancelable(true);
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = FragmentHome.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btn_Logout = findViewById(R.id.btn_Logout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        btn_MonAnBanChay = findViewById(R.id.btn_MonAnBanChay);
        btn_ThucUongBanChay = findViewById(R.id.btn_ThucUongBanChay);
        btn_DoanhThu = findViewById(R.id.btn_DoanhThu);
        btn_KhachHang = findViewById(R.id.btn_KhachHang);
        btn_NguyenLieu = findViewById(R.id.btn_NguyenLieu);
        btn_DoanhThuKhuVuc = findViewById(R.id.btn_DoanhThuKhuVuc);
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo!!!");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        String title = "Appname";
        Class fragmentClass = null;
        if(id == R.id.nav_home){
            fragmentClass = FragmentHome.class;
            title = "Báo cáo thống kê";
        } else if (id == R.id.nav_hotfood) {
            fragmentClass = FragmentMonAn.class;
            title = "Thống kê món ăn";
        } else if (id == R.id.nav_hotdrink) {
            fragmentClass = FragmentThucUong.class;
            title = "Thống kê thức uống";
        } else if (id == R.id.nav_doanhthu) {
            fragmentClass = FragmentDoanhThu.class;
            title = "Thống kê doanh thu";
        } else if (id == R.id.nav_tonkho) {
            fragmentClass = FragmentNguyenLieu.class;
            title = "Thống kê nguyên liệu";
        } else if (id == R.id.nav_customer) {
            fragmentClass = FragmentKhachHang.class;
            title = "Thống kê khách hàng";
        } else if (id == R.id.nav_support) {
            fragmentClass = FragmentGioiThieu.class;
            title = "Giới Thiệu";
        }else if (id == R.id.nav_doanhthukhuvuc) {
            fragmentClass = FragmentDoanhThuKV.class;
            title = "Doanh thu theo khu vực";
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
