package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
    static ArrayList<Integer> arrayListSoMA  = new ArrayList<>();
    static ArrayList<String> arrayListTenMA = new ArrayList<>();

    TextView tv_ngayBDMA, tv_ngayKTMA;
    Button btn_HienthiMA;

    //Lấy dữ liệu ngày bắt đầu và ngày kết thúc
    int ngayBDMA,thangBDMA, namBDMA;
    int ngayKTMA,thangKTMA, namKTMA;
    String resultNgayBDMA,resultNgayKTMA;
    Calendar calendarBDMA, calendarKTMA;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_monan, container, false);

        tv_ngayBDMA = view.findViewById(R.id.tv_ngayBDMA);
        tv_ngayKTMA = view.findViewById(R.id.tv_ngayKTMA);
        tv_ngayBDMA.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
        tv_ngayKTMA.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
        resultNgayBDMA = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        resultNgayKTMA = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        tv_ngayBDMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonTuNgay();
            }
        });

        tv_ngayKTMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonDenNgay();
            }
        });
        //ánh xạ biểu đồ
        combinedchart_monan = (CombinedChart) view.findViewById(R.id.combinedchart_monan);
        //custom lại giao diện
        combinedchart_monan.getDescription().setEnabled(false);
        combinedchart_monan.setBackgroundColor(Color.WHITE);
        combinedchart_monan.setDrawGridBackground(false);
        combinedchart_monan.setDrawBarShadow(false);
        combinedchart_monan.setHighlightFullBarEnabled(false);
        combinedchart_monan.setOnChartValueSelectedListener(this);
        btn_HienthiMA = view.findViewById(R.id.btn_HienthiMA);
        btn_HienthiMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayListSoMA.clear();
                arrayListTenMA.clear();
                GetDataMonAn();
            }
        });

        return view;
    }

    private void ChonTuNgay(){
        calendarBDMA = Calendar.getInstance();
        ngayBDMA = calendarBDMA.get(Calendar.DATE);
        thangBDMA = calendarBDMA.get(Calendar.MONTH);
        namBDMA = calendarBDMA.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarBDMA.set(year,month,dayOfMonth);
                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat resultDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                tv_ngayBDMA.setText(displayDateFormat.format(calendarBDMA.getTime()));
                resultNgayBDMA = String.valueOf(resultDateFormat.format(calendarBDMA.getTime()));
                Log.d("resultNgayBD",resultNgayBDMA);
            }
        },namBDMA,thangBDMA,ngayBDMA);
        datePickerDialog.show();
    }
    private void ChonDenNgay(){
        calendarKTMA = Calendar.getInstance();
        ngayKTMA = calendarKTMA.get(Calendar.DATE);
        thangKTMA = calendarKTMA.get(Calendar.MONTH);
        namKTMA = calendarKTMA.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarKTMA.set(year,month,dayOfMonth);
                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat resultDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                tv_ngayKTMA.setText(displayDateFormat.format(calendarKTMA.getTime()));
                resultNgayKTMA = String.valueOf(resultDateFormat.format(calendarKTMA.getTime()));
                Log.d("resultNgayKT",resultNgayKTMA);
            }
        },namKTMA,thangKTMA,ngayKTMA);
        datePickerDialog.show();
    }

    private void GetDataMonAn()
    {
        String endcoderesultNgayBDMA = null;
        String endcoderesultNgayKTMA = null;
        try {
            endcoderesultNgayBDMA = URLEncoder.encode(resultNgayBDMA,"utf-8");
            endcoderesultNgayKTMA = URLEncoder.encode(resultNgayKTMA,"utf-8");
            Log.d("endcoderesultNgayBDMA",endcoderesultNgayBDMA);
            Log.d("endcoderesultNgayKTMA",endcoderesultNgayKTMA);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://192.168.1.11/api/ThongKe/ThongKeMonAnBanChay?ngayBD="+ endcoderesultNgayBDMA +"&ngayKT="+endcoderesultNgayKTMA;
        //String url = "http://192.168.1.11/api/ThongKe/ThongKeMonAnBanChay?ngayBD=2019%2F05%2F19&ngayKT=2019%2F05%2F22";
//        String url= "http://www.mocky.io/v2/5cf91ec53400000e8901b3eb";
        Log.d("ABC", url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                MonAn monAn = new MonAn();
                monAnArrayList = monAn.getListMonAn(response.toString());
                for(int i = 0; i< response.length(); i++)
                {
                    arrayListSoMA.add(Integer.parseInt(monAnArrayList.get(i).getTongDat()));
                    arrayListTenMA.add(monAnArrayList.get(i).getTenMonAn());
                }
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
                for (int index = 0; index < arrayListTenMA.size(); index++) {
                    xLabel.add(arrayListTenMA.get(index));
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
                BarData barData  = new BarData();
                barData.addDataSet((IBarDataSet) dataChart());

                data.setData(barData);

                xAxis.setAxisMaximum(data.getXMax() + 0.25f);

                combinedchart_monan.setData(data);
                combinedchart_monan.invalidate();

                Log.d("ABCC",arrayListSoMA.size()+"");
                Log.d("ABCD",arrayListTenMA.size()+"");
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

    private  DataSet dataChart() {

        BarData d = new BarData();
        //data hiển thị trên đường

        //add data vào list
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        for (int index = 0; index < arrayListSoMA.size(); index++) {
            entries.add(new BarEntry(index, arrayListSoMA.get(index)));
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
