package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyenhuongit.qlnhahangapp.Adapter.KhachHangAdapter;
import com.example.nguyenhuongit.qlnhahangapp.Data.KhachHang;
import com.example.nguyenhuongit.qlnhahangapp.Data.MonAn;
import com.example.nguyenhuongit.qlnhahangapp.R;


import org.json.JSONArray;

import java.util.ArrayList;

public class FragmentKhachHang extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    KhachHangAdapter khachHangAdapter;
    ArrayList<KhachHang> khachHangArrayList = new ArrayList<>();

//    String IP_Config = "192.168.1.11";

    private OnFragmentInteractionListener mListener;


    public FragmentKhachHang() {
        // Required empty public constructor
    }

    public static FragmentKhachHang newInstance(String param1, String param2) {
        FragmentKhachHang fragment = new FragmentKhachHang();
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
        View view = inflater.inflate(R.layout.layout_fragment_khachhang, container, false);
        recyclerView = view.findViewById(R.id.recycleview_khachhang);
        getDataKhachHang();
        return view;
    }

    private void getDataKhachHang() {
//        String url="http://www.mocky.io/v2/5cfc81c83200006700ccd3be";
        String url = "http://192.168.1.11/api/ThongKe/ThongKeKhachHang";
        Log.d("ABC", url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                KhachHang khachHang = new KhachHang();
                khachHangArrayList = khachHang.getListKhachHang(response.toString());
                khachHangAdapter = new KhachHangAdapter(getContext(),R.layout.item_custom_khachhang,khachHangArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(khachHangAdapter);
                khachHangAdapter.notifyDataSetChanged();

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Error!!!",Toast.LENGTH_SHORT);
                    }
                }
        );
        Log.d("khachHangArrayList", khachHangArrayList.size() +"");
        requestQueue.add(jsonArrayRequest);
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
