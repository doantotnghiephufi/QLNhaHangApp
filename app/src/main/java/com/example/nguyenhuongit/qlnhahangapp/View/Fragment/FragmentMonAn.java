package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenhuongit.qlnhahangapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class FragmentMonAn extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ArrayList NoOfEmp = new ArrayList();
    private ArrayList year = new ArrayList();
    private BarChart barchart_monan;
    private OnFragmentInteractionListener mListener;

    public FragmentMonAn() {
        // Required empty public constructor
    }

    public static FragmentMonAn newInstance(String param1, String param2) {
        FragmentMonAn fragment = new FragmentMonAn();
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
        View view = inflater.inflate(R.layout.layout_fragment_monan, container, false);
        barchart_monan = view.findViewById(R.id.barchart_monan);

        BarDataSet barDataSet = new BarDataSet(dataValue(),"Món ăn thịnh hành");
        barDataSet.setColor(Color.GREEN);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barchart_monan.setData(barData);
        barchart_monan.invalidate();

        return view;
    }

    //dữ liệu barchart
    public ArrayList<BarEntry> dataValue()
    {
        ArrayList<BarEntry> dataVal = new ArrayList<BarEntry>();
        dataVal.add(new BarEntry(0,20));
        dataVal.add(new BarEntry(1,25));
        dataVal.add(new BarEntry(2,5));
        dataVal.add(new BarEntry(3,10));
        dataVal.add(new BarEntry(4,15));
        return dataVal;
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
