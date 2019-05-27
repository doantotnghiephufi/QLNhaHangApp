package com.example.nguyenhuongit.qlnhahangapp.View.Activity;

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

import com.example.nguyenhuongit.qlnhahangapp.R;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentDoanhThu;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentHoTro;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentHoaDon;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentKhachHang;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentMonAn;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentThucUong;
import com.example.nguyenhuongit.qlnhahangapp.View.Fragment.FragmentNguyenLieu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentMonAn.OnFragmentInteractionListener,
        FragmentThucUong.OnFragmentInteractionListener, FragmentDoanhThu.OnFragmentInteractionListener, FragmentNguyenLieu.OnFragmentInteractionListener ,
        FragmentHoaDon.OnFragmentInteractionListener, FragmentKhachHang.OnFragmentInteractionListener, FragmentHoTro.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Fragment fragment = null;
            Class fragmentClass = null;
            fragmentClass = FragmentMonAn.class;
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if (id == R.id.nav_hotfood) {
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
            title = "Thống kê tồn kho";
        } else if (id == R.id.nav_customer) {
            fragmentClass = FragmentKhachHang.class;
            title = "Thống kê khách hàng";
        } else if (id == R.id.nav_invoice) {
            fragmentClass = FragmentHoaDon.class;
            title = "Thống kê hóa đơn";
        } else if (id == R.id.nav_support) {
            fragmentClass = FragmentHoTro.class;
            title = "Hỗ trợ";
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
