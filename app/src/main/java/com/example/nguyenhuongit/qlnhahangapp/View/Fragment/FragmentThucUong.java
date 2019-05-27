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
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class FragmentThucUong extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private BarChart barchart_thucuong;



    private OnFragmentInteractionListener mListener;

    public FragmentThucUong() {
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
    public static FragmentThucUong newInstance(String param1, String param2) {
        FragmentThucUong fragment = new FragmentThucUong();
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
        View view = inflater.inflate(R.layout.layout_fragment_thucuong, container, false);

        barchart_thucuong = view.findViewById(R.id.barchart_thucuong);

        BarDataSet barDataSet = new BarDataSet(dataValue(),"Món ăn thịnh hành");
        barDataSet.setColor(Color.GREEN);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barchart_thucuong.setData(barData);
        barchart_thucuong.invalidate();

        return view;
    }

    //dữ liệu barchart
    public ArrayList<BarEntry> dataValue()
    {
        ArrayList<BarEntry> dataVal = new ArrayList<BarEntry>();
        dataVal.add(new BarEntry(0,12));
        dataVal.add(new BarEntry(1,9));
        dataVal.add(new BarEntry(2,20));
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
