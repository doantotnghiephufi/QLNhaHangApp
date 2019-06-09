package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nguyenhuongit.qlnhahangapp.R;

public class FragmentHome extends Fragment
        implements OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    Button btn_MonAnBanChay, btn_ThucUongBanChay, btn_DoanhThu, btn_KhachHang,btn_NguyenLieu,btn_GioiThieu;
    public FragmentHome() {
        // Required empty public constructor
    }

    public static FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_home, container, false);

        btn_MonAnBanChay = view.findViewById(R.id.btn_MonAnBanChay);
        btn_ThucUongBanChay = view.findViewById(R.id.btn_ThucUongBanChay);
        btn_DoanhThu = view.findViewById(R.id.btn_DoanhThu);
        btn_KhachHang = view.findViewById(R.id.btn_KhachHang);
        btn_NguyenLieu = view.findViewById(R.id.btn_NguyenLieu);
        btn_GioiThieu = view.findViewById(R.id.btn_DoanhThuKhuVuc);

        btn_MonAnBanChay.setOnClickListener(this);
        btn_ThucUongBanChay.setOnClickListener(this);
        btn_DoanhThu.setOnClickListener(this);
        btn_KhachHang.setOnClickListener(this);
        btn_NguyenLieu.setOnClickListener(this);
        btn_GioiThieu.setOnClickListener(this);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        String title = "Appname";
        Class fragmentClass = null;
        switch (v.getId())
        {
            case R.id.btn_MonAnBanChay:
            {
//                Toast.makeText(getContext(), "btn_MonAnBanChay", Toast.LENGTH_SHORT).show();
                fragmentClass = FragmentMonAn.class;
                title = "Thống kê món ăn";
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
                // set the toolbar title
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }
                break;
            }
            case R.id.btn_ThucUongBanChay:
            {
                fragmentClass = FragmentThucUong.class;
                title = "Thống kê thức uống";
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
                // set the toolbar title
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }
//                Toast.makeText(getContext(), "btn_ThucUongBanChay", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_DoanhThu:
            {
                fragmentClass = FragmentDoanhThu.class;
                title = "Thống kê doanh thu";
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
                // set the toolbar title
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }

//                Toast.makeText(getContext(), "btn_DoanhThu", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_KhachHang:
            {
                fragmentClass = FragmentKhachHang.class;
                title = "Thống kê khách hàng";
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
                // set the toolbar title
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }
//                Toast.makeText(getContext(), "btn_KhachHang", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_NguyenLieu:
            {
                fragmentClass = FragmentNguyenLieu.class;
                title = "Thống kê nguyên liệu";
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
                // set the toolbar title
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }
//                Toast.makeText(getContext(), "btn_NguyenLieu", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btn_DoanhThuKhuVuc:
            {
                fragmentClass = FragmentDoanhThuKV.class;
                title = "Doanh Thu Theo Khu Vực";
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                ActionBar actionBar =((AppCompatActivity) getActivity()).getSupportActionBar();
                // set the toolbar title
                if (actionBar != null) {
                    actionBar.setTitle(title);
                }
//                Toast.makeText(getContext(), "btn_GioiThieu", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
