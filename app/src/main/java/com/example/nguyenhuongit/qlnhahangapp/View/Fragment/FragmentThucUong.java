package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nguyenhuongit.qlnhahangapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class FragmentThucUong extends Fragment implements OnChartValueSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
//    private BarChart barchart_thucuong;
    private CombinedChart combinedchart_thucuong;
    private OnFragmentInteractionListener mListener;

    //Lấy dữ liệu ngày bắt đầu và ngày kết thúc
    String ngayBD, ngayKT;
    String dayBD, dayKT, monthBD, monthKT, yearBD, yearKT;
    String resultNgayBD,resultNgayKT;

    public FragmentThucUong() {
        // Required empty public constructor
    }

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

        //ánh xạ biểu đồ
        combinedchart_thucuong = (CombinedChart) view.findViewById(R.id.combinedchart_thucuong);
        //custom lại giao diện
        combinedchart_thucuong.getDescription().setEnabled(false);
        combinedchart_thucuong.setBackgroundColor(Color.WHITE);
        combinedchart_thucuong.setDrawGridBackground(false);
        combinedchart_thucuong.setDrawBarShadow(false);
        combinedchart_thucuong.setHighlightFullBarEnabled(false);
        combinedchart_thucuong.setOnChartValueSelectedListener(this);

        //data 2 bên cột của biểu đồ
        YAxis rightAxis = combinedchart_thucuong.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);
        //data 2 bên cột của biểu đồ
        YAxis leftAxis = combinedchart_thucuong.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        //tên hiển thị ở dưới
        final List<String> xLabel = new ArrayList<>();
        xLabel.add("Jan");
        xLabel.add("Feb");
        xLabel.add("Mar");
        xLabel.add("Apr");
        xLabel.add("May");
        xLabel.add("Jun");
        xLabel.add("Jul");
        xLabel.add("Aug");
        xLabel.add("Sep");
        xLabel.add("Oct");
        xLabel.add("Nov");
        xLabel.add("Dec");

        XAxis xAxis = combinedchart_thucuong.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }
        });

        CombinedData data = new CombinedData();
        BarData lineDatas = new BarData();
        lineDatas.addDataSet((IBarDataSet) dataChart());

        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        combinedchart_thucuong.setData(data);
        combinedchart_thucuong.invalidate();
        //add line data
//        LineDataSet dataSet = new LineDataSet(dataValue(), "Company 1");
//        LineDataSet dataSet1 = new LineDataSet(dataValue2(),"Company 2");
//        //thêm vào array list
//        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//        dataSets.add(dataSet);
//        dataSets.add(dataSet1);
//
//        //set giá trị lên biểu đồ
//        LineData lineData = new LineData(dataSets);
//        chart_doanhthu.setData(lineData);
//        chart_doanhthu.invalidate();
//
//    //custom lại view biểu đồ
//        //set title cho biểu đồ
//        Description description = new Description();
//        description.setText("Revenue by month");
//        description.setTextColor(Color.BLUE);
//        description.setTextSize(16);
//        chart_doanhthu.setDescription(description);
//
//        //set background cho biểu đồ
//        chart_doanhthu.setDrawGridBackground(true);
//        chart_doanhthu.setDrawBorders(true);
//        chart_doanhthu.setBorderColor(Color.GREEN);
//
//        //set lengend cho biểu đồ
//        Legend legend = chart_doanhthu.getLegend();
//        legend.setEnabled(true);
//        legend.setTextColor(Color.RED);
//        legend.setTextSize(12);
//        legend.setForm(Legend.LegendForm.LINE);
//        legend.setFormSize(14);
//        legend.setXEntrySpace(15);
//        legend.setFormToTextSpace(5);
//
//        //set line
//        dataSet.setLineWidth(2);
//        dataSet.setColor(Color.GREEN);
//        dataSet.setValueTextSize(10);
//        dataSet1.setLineWidth(2);
//        dataSet1.setColor(Color.GREEN);
//        dataSet1.setValueTextSize(10);
//
//        //custom axisvalue
//        XAxis xAxis = chart_doanhthu.getXAxis();
//        YAxis yAxisLeft = chart_doanhthu.getAxisLeft();
//        YAxis yAxisRight = chart_doanhthu.getAxisRight();
//        xAxis.setValueFormatter(new MyAxisValueFormatter());
        return view;
    }

    private static DataSet dataChart() {

        BarData d = new BarData();
        //data hiển thị trên đường
        int[] data = new int[] { 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 9 };

        //add data vào list
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        for (int index = 0; index < 12; index++) {
            entries.add(new BarEntry(index, data[index]));
        }

        //set label cho biểu đồ

        BarDataSet set = new BarDataSet(entries, "Thức uống thịnh hành");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
    }


    private void getDataList()
    {
        yearBD = "2019";
        yearKT = "2019";
        monthBD = "5";
        monthKT = "5";
        dayBD = "19";
        dayKT = "22";

        resultNgayBD = yearBD +"%2F"+ monthBD + "%2F" + dayBD;
        resultNgayKT = yearKT +"%2F"+ monthKT + "%2F" + dayKT;
        String url = "http://192.168.1.2/api/ThongKe/ThongKeHDTheoNgayLap?ngayBD="+ resultNgayBD +"&ngayKT="+resultNgayKT;
        Log.d("BBB", url);
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
        Toast.makeText(getContext(), "Value: " + e.getY() + ", index: " + h.getX() + ", DataSet index: " + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
