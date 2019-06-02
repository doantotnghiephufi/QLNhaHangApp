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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyenhuongit.qlnhahangapp.Data.KhachHang;
import com.example.nguyenhuongit.qlnhahangapp.Data.MonAn;
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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentMonAn extends Fragment implements OnChartValueSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ArrayList NoOfEmp = new ArrayList();
    private ArrayList year = new ArrayList();
    private OnFragmentInteractionListener mListener;

    private CombinedChart combinedchart_monan;
    ArrayList<MonAn> monAnArrayList = new ArrayList<>();
    static ArrayList<Integer> arrayListSo  = new ArrayList<>();
    static ArrayList<String> arrayListTen = new ArrayList<>();

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
        //ánh xạ biểu đồ
        combinedchart_monan = (CombinedChart) view.findViewById(R.id.combinedchart_monan);
        //custom lại giao diện
        combinedchart_monan.getDescription().setEnabled(false);
        combinedchart_monan.setBackgroundColor(Color.WHITE);
        combinedchart_monan.setDrawGridBackground(false);
        combinedchart_monan.setDrawBarShadow(false);
        combinedchart_monan.setHighlightFullBarEnabled(false);
        combinedchart_monan.setOnChartValueSelectedListener(this);

        GetDataMonAn();
        BieuDoMonAn();


        return view;
    }

    private void GetDataMonAn()
    {
//        String url = "http://192.168.1.14/api/ThongKe/ThongKeMonAnBanChay?ngayBD=2019%2F05%2F19&ngayKT=2019%2F05%2F22";
        String url = "http://www.mocky.io/v2/5cf36732330000c512758526";
        Log.d("ABC", url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                MonAn monAn = new MonAn();
                monAnArrayList.clear();
                arrayListSo.clear();
                arrayListTen.clear();
                monAnArrayList = monAn.getListMonAn(response.toString());
                for(int i = 0; i< response.length(); i++)
                {
                    String maMon = "";
                    String tenMon = "";
                    String tongDat = "";
                    maMon = monAnArrayList.get(i).getMaMonAn().toString();
                    tenMon = monAnArrayList.get(i).getTenMonAn().toString();
                    tongDat = monAnArrayList.get(i).getTongDat().toString();
                    arrayListSo.add(Integer.parseInt(monAnArrayList.get(i).getTongDat()));
                    arrayListTen.add(monAnArrayList.get(i).getTenMonAn());
//                    Log.d("ma",maMon);
//                    Log.d("tenmon",tenMon);
//                    Log.d("tongdat",tongDat);
//                    Toast.makeText(getContext(), maMon + tenMon +tongDat, Toast.LENGTH_SHORT).show();
                }
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(),"Error!!!",Toast.LENGTH_SHORT);
                }
            }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void BieuDoMonAn()
    {
        //data 2 bên cột của biểu đồ
        YAxis rightAxis = combinedchart_monan.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);
        //data 2 bên cột của biểu đồ
        YAxis leftAxis = combinedchart_monan.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        //tên hiển thị ở dưới
        final List<String> xLabel = new ArrayList<>();
        for (int index = 0; index < arrayListTen.size(); index++) {
            xLabel.add(arrayListTen.get(index));
        }
        XAxis xAxis = combinedchart_monan.getXAxis();
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

        combinedchart_monan.setData(data);
        combinedchart_monan.invalidate();
    }
    private  DataSet dataChart() {

        BarData d = new BarData();
        //data hiển thị trên đường


        //add data vào list
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        for (int index = 0; index < arrayListSo.size(); index++) {
            entries.add(new BarEntry(index, arrayListSo.get(index)));
        }

        //set label cho biểu đồ

        BarDataSet set = new BarDataSet(entries, "Món ăn thịnh hành");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);
        set.setValueTextSize(16f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
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
