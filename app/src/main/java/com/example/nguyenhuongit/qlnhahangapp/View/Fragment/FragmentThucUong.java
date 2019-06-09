package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.android.volley.toolbox.Volley;
import com.example.nguyenhuongit.qlnhahangapp.Data.MonAn;
import com.example.nguyenhuongit.qlnhahangapp.Data.ThucUong;
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

import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentThucUong extends Fragment implements OnChartValueSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
//    private BarChart barchart_thucuong;
    private CombinedChart combinedchart_thucuong;

    ArrayList<ThucUong> thucUongArrayList = new ArrayList<>();
    static ArrayList<Integer> arrayListSoTU = new ArrayList<>();
    static ArrayList<String> arrayListTenTU = new ArrayList<>();
    private OnFragmentInteractionListener mListener;
    TextView tv_ngayBDTU, tv_ngayKTTU;
    Button btn_HienthiTU;

    //Lấy dữ liệu ngày bắt đầu và ngày kết thúc
    int ngayBDTU,thangBDTU, namBDTU;
    int ngayKTTU,thangKTTU, namKTTU;
    String resultNgayBDTU,resultNgayKTTU;
    Calendar calendarBDTU, calendarKTTU;

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

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_thucuong, container, false);
        tv_ngayBDTU = view.findViewById(R.id.tv_ngayBDTU);
        tv_ngayKTTU = view.findViewById(R.id.tv_ngayKTTU);
        btn_HienthiTU = view.findViewById(R.id.btn_HienthiTU);
        tv_ngayBDTU.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
        tv_ngayKTTU.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
        resultNgayBDTU = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        resultNgayKTTU = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        tv_ngayBDTU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonTuNgay();
            }
        });

        tv_ngayKTTU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonDenNgay();
            }
        });

        //ánh xạ biểu đồ
        combinedchart_thucuong = (CombinedChart) view.findViewById(R.id.combinedchart_thucuong);
        //custom lại giao diện
        combinedchart_thucuong.getDescription().setEnabled(false);
        combinedchart_thucuong.setBackgroundColor(Color.WHITE);
        combinedchart_thucuong.setDrawGridBackground(false);
        combinedchart_thucuong.setDrawBarShadow(false);
        combinedchart_thucuong.setHighlightFullBarEnabled(false);
        combinedchart_thucuong.setOnChartValueSelectedListener(this);

        btn_HienthiTU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    GetDataThucUong();
            }
        });

        return view;
    }

    private void GetDataThucUong()
    {
        String endcoderesultNgayBDTU = null;
        String endcoderesultNgayKTTU = null;
        try {
            endcoderesultNgayBDTU = URLEncoder.encode(resultNgayBDTU,"utf-8");
            endcoderesultNgayKTTU = URLEncoder.encode(resultNgayKTTU,"utf-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://192.168.1.11/api/ThongKe/ThongKeThucUongBanChay?ngayBD="+ endcoderesultNgayBDTU +"&ngayKT="+endcoderesultNgayKTTU;
        //String url = "http://192.168.1.14/api/ThongKe/ThongKeMonAnBanChay?ngayBD=2019%2F05%2F19&ngayKT=2019%2F05%2F22";
//        String url = "http://www.mocky.io/v2/5cfc99aa3200005900ccd3da";
        Log.d("ABC", url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ThucUong thucUong = new ThucUong();
                thucUongArrayList.clear();
                arrayListSoTU.clear();
                arrayListTenTU.clear();
                thucUongArrayList = thucUong.getListThucUong(response.toString());
                for(int i = 0; i< response.length(); i++)
                {
                    arrayListSoTU.add(Integer.parseInt(thucUongArrayList.get(i).getTongDat()));
                    arrayListTenTU.add(thucUongArrayList.get(i).getTenThucUong());
//                    Log.d("ma",maMon);
//                    Log.d("tenmon",tenMon);
//                    Log.d("tongdat",tongDat);
//                    Toast.makeText(getContext(), maMon + tenMon +tongDat, Toast.LENGTH_SHORT).show();
                }
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
                for (int index = 0; index < arrayListTenTU.size(); index++) {
                    xLabel.add(arrayListTenTU.get(index));
                }
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
                Log.d("Xlable", xLabel.size()+"");


                CombinedData data = new CombinedData();
                BarData barData  = new BarData();
                barData.addDataSet((IBarDataSet) dataChart());

                data.setData(barData);

                xAxis.setAxisMaximum(data.getXMax() + 0.25f);

                combinedchart_thucuong.setData(data);
                combinedchart_thucuong.invalidate();

                Log.d("ABCC",arrayListSoTU.size()+"");
                Log.d("ABCD",arrayListTenTU.size()+"");


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
    private static DataSet dataChart() {

        BarData d = new BarData();
        //data hiển thị trên đường
//        int[] data = new int[] { 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 9 };

        //add data vào list
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
//        for (int index = 0; index < 12; index++) {
//            entries.add(new BarEntry(index, data[index]));
//        }
        for (int index = 0; index < arrayListSoTU.size(); index++) {
            entries.add(new BarEntry(index, arrayListSoTU.get(index)));
        }
        //set label cho biểu đồ
        BarDataSet set = new BarDataSet(entries, "Thức uống thịnh hành");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        set.setDrawValues(true);
        set.setValueTextSize(14f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        return set;
    }

    private void ChonTuNgay(){
        calendarBDTU = Calendar.getInstance();
        ngayBDTU = calendarBDTU.get(Calendar.DATE);
        thangBDTU = calendarBDTU.get(Calendar.MONTH);
        namBDTU = calendarBDTU.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarBDTU.set(year,month,dayOfMonth);
                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat resultDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                tv_ngayBDTU.setText(displayDateFormat.format(calendarBDTU.getTime()));
                resultNgayBDTU = String.valueOf(resultDateFormat.format(calendarBDTU.getTime()));
                Log.d("resultNgayBD",resultNgayBDTU);
            }
        },namBDTU,thangBDTU,ngayBDTU);
        datePickerDialog.show();
    }

    private void ChonDenNgay(){
        calendarKTTU = Calendar.getInstance();
        ngayKTTU = calendarKTTU.get(Calendar.DATE);
        thangKTTU = calendarKTTU.get(Calendar.MONTH);
        namKTTU = calendarKTTU.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarKTTU.set(year,month,dayOfMonth);
                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat resultDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                tv_ngayKTTU.setText(displayDateFormat.format(calendarKTTU.getTime()));
                resultNgayKTTU = String.valueOf(resultDateFormat.format(calendarKTTU.getTime()));
                Log.d("resultNgayKT",resultNgayKTTU);
            }
        },namKTTU,thangKTTU,ngayKTTU);
        datePickerDialog.show();
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
//        Toast.makeText(getContext(), "Value: " + e.getY() + ", index: " + h.getX() + ", DataSet index: " + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
