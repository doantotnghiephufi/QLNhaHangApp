package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nguyenhuongit.qlnhahangapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentDoanhThu extends Fragment implements OnChartValueSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LineChart chart_doanhthu;


    private OnFragmentInteractionListener mListener;

    public FragmentDoanhThu() {
        // Required empty public constructor
    }

    public static FragmentDoanhThu newInstance(String param1, String param2) {
        FragmentDoanhThu fragment = new FragmentDoanhThu();
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
        View view = inflater.inflate(R.layout.layout_fragment_doanhthu, container, false);

        chart_doanhthu = view.findViewById(R.id.chart_doanhthu);
        //add line data
        LineDataSet dataSet = new LineDataSet(dataValue(), "Company 1");
        LineDataSet dataSet1 = new LineDataSet(dataValue2(),"Company 2");
        //thêm vào array list
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet);
        dataSets.add(dataSet1);

        //set giá trị lên biểu đồ
        LineData lineData = new LineData(dataSets);
        chart_doanhthu.setData(lineData);
        chart_doanhthu.invalidate();

    //custom lại view biểu đồ
        //set title cho biểu đồ
        Description description = new Description();
        description.setText("Revenue by month");
        description.setTextColor(Color.BLUE);
        description.setTextSize(16);
        chart_doanhthu.setDescription(description);

        //set background cho biểu đồ
        chart_doanhthu.setDrawGridBackground(true);
        chart_doanhthu.setDrawBorders(true);
        chart_doanhthu.setBorderColor(Color.GREEN);

        //set lengend cho biểu đồ
        Legend legend = chart_doanhthu.getLegend();
        legend.setEnabled(true);
        legend.setTextColor(Color.RED);
        legend.setTextSize(12);
        legend.setForm(Legend.LegendForm.LINE);
        legend.setFormSize(14);
        legend.setXEntrySpace(15);
        legend.setFormToTextSpace(5);

        //set line
        dataSet.setLineWidth(2);
        dataSet.setColor(Color.GREEN);
        dataSet.setValueTextSize(10);
        dataSet1.setLineWidth(2);
        dataSet1.setColor(Color.GREEN);
        dataSet1.setValueTextSize(10);

        //custom axisvalue
        XAxis xAxis = chart_doanhthu.getXAxis();
        YAxis yAxisLeft = chart_doanhthu.getAxisLeft();
        YAxis yAxisRight = chart_doanhthu.getAxisRight();

       xAxis.setValueFormatter(new MyAxisValueFormatter());

        return view;
    }

    public class MyAxisValueFormatter implements IAxisValueFormatter
    {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "Tháng" + value;
        }
    }
    public ArrayList<Entry> dataValue()
    {
        ArrayList<Entry> dataVal = new ArrayList<Entry>();
        dataVal.add(new Entry(0,20));
        dataVal.add(new Entry(2,25));
        dataVal.add(new Entry(3,5));
        dataVal.add(new Entry(5,10));
        dataVal.add(new Entry(6,15));
        return dataVal;
    }

    public ArrayList<Entry> dataValue2()
    {
        ArrayList<Entry> dataVal = new ArrayList<Entry>();
        dataVal.add(new Entry(0,3));
        dataVal.add(new Entry(1,14));
        dataVal.add(new Entry(2,7));
        dataVal.add(new Entry(3,11));
        dataVal.add(new Entry(4,9));
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


    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
