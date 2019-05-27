package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nguyenhuongit.qlnhahangapp.Adapter.NguyenLieuAdapter;
import com.example.nguyenhuongit.qlnhahangapp.Data.NguyenLieu;
import com.example.nguyenhuongit.qlnhahangapp.R;

import java.util.ArrayList;

public class FragmentNguyenLieu extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    NguyenLieuAdapter nguyenLieuAdapter;
    ArrayList<NguyenLieu> nguyenLieuArrayList = new ArrayList<>();


    private OnFragmentInteractionListener mListener;

    public FragmentNguyenLieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentOne.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNguyenLieu newInstance(String param1, String param2) {
        FragmentNguyenLieu fragment = new FragmentNguyenLieu();
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
        View view = inflater.inflate(R.layout.layout_fragment_tonkho, container, false);
        recyclerView = view.findViewById(R.id.recycleview_nguyenlieu);
        getDataTest();
        return view;
    }

    private void getDataTest() {
        NguyenLieu nguyenLieu = new NguyenLieu("SP01", "Sản phẩm 01","Linh kiện","10");
        nguyenLieuArrayList.add(nguyenLieu);
        nguyenLieu = new NguyenLieu("SP02", "Sản phẩm 02","Bàn Phím","15");
        nguyenLieuArrayList.add(nguyenLieu);
        nguyenLieu = new NguyenLieu("SP03", "Sản phẩm 03","Chuột","20");
        nguyenLieuArrayList.add(nguyenLieu);
        nguyenLieu = new NguyenLieu("SP04", "Sản phẩm 04","Tai nghe","25");
        nguyenLieuArrayList.add(nguyenLieu);
        nguyenLieu = new NguyenLieu("SP05", "Sản phẩm 05","Ram","5");
        nguyenLieuArrayList.add(nguyenLieu);
        nguyenLieu = new NguyenLieu("SP06", "Sản phẩm 06","CPU","8");
        nguyenLieuArrayList.add(nguyenLieu);

        nguyenLieuAdapter = new NguyenLieuAdapter(getContext(), R.layout.item_custom_tonkho, nguyenLieuArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(nguyenLieuAdapter);
        nguyenLieuAdapter.notifyDataSetChanged();
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
