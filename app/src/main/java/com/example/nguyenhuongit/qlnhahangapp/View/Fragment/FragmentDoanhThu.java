package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyenhuongit.qlnhahangapp.Data.DoanhThu;
import com.example.nguyenhuongit.qlnhahangapp.Data.MonAn;
import com.example.nguyenhuongit.qlnhahangapp.R;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
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

public class FragmentDoanhThu extends Fragment implements OnChartValueSelectedListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;


//    private LineChart chart_doanhthu;
    ArrayList<DoanhThu> doanhThuArrayList = new ArrayList<>();
    static ArrayList<Integer> arrayListSoDoanhThu  = new ArrayList<>();
    static ArrayList<String> arrayListTenDoanhThu = new ArrayList<>();
    CombinedChart chart;

    TextView tv_ngayBD, tv_ngayKT;
    Button btn_Hienthi;

    //Lấy dữ liệu ngày bắt đầu và ngày kết thúc
    int ngayBD,thangBD, namBD;
    int ngayKT,thangKT, namKT;
    String resultNgayBD,resultNgayKT;
    Calendar calendarBD, calendarKT;

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

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_fragment_doanhthu, container, false);
        tv_ngayBD = view.findViewById(R.id.tv_ngayBD);
        tv_ngayKT = view.findViewById(R.id.tv_ngayKT);

        tv_ngayBD.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
        tv_ngayKT.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString());
        resultNgayBD = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        resultNgayKT = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();

        tv_ngayBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonTuNgay();
            }
        });
        tv_ngayKT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonDenNgay();
            }
        });


        chart = view.findViewById(R.id.chart_doanhthu);
        chart.getDescription().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);
        chart.setHighlightFullBarEnabled(false);


        btn_Hienthi = view.findViewById(R.id.btn_Hienthi);
        btn_Hienthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataDoanhThu();
            }
        });


        return view;
    }

    private void ChonTuNgay(){
         calendarBD = Calendar.getInstance();
         ngayBD = calendarBD.get(Calendar.DATE);
         thangBD = calendarBD.get(Calendar.MONTH);
         namBD = calendarBD.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarBD.set(year,month,dayOfMonth);
                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat resultDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                tv_ngayBD.setText(displayDateFormat.format(calendarBD.getTime()));
                resultNgayBD = String.valueOf(resultDateFormat.format(calendarBD.getTime()));
                Log.d("resultNgayBD",resultNgayBD);
            }
        },namBD,thangBD,ngayBD);
        datePickerDialog.show();
    }

    private void ChonDenNgay(){
        calendarKT = Calendar.getInstance();
        ngayKT = calendarKT.get(Calendar.DATE);
        thangKT = calendarKT.get(Calendar.MONTH);
        namKT = calendarKT.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendarKT.set(year,month,dayOfMonth);
                SimpleDateFormat displayDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat resultDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                tv_ngayKT.setText(displayDateFormat.format(calendarKT.getTime()));
                resultNgayKT = String.valueOf(resultDateFormat.format(calendarKT.getTime()));
                Log.d("resultNgayKT",resultNgayKT);
            }
        },namKT,thangKT,ngayKT);
        datePickerDialog.show();
    }

    private void GetDataDoanhThu()
    {
        String endcoderesultNgayBDMA = null;
        String endcoderesultNgayKTMA = null;
        try {
            endcoderesultNgayBDMA = URLEncoder.encode(resultNgayBD,"utf-8");
            endcoderesultNgayKTMA = URLEncoder.encode(resultNgayKT,"utf-8");
            Log.d("endcoderesultNgayBDMA",endcoderesultNgayBDMA);
            Log.d("endcoderesultNgayKTMA",endcoderesultNgayKTMA);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String url = "http://192.168.1.11/api/ThongKe/ThongKeHDTheoNgayLap?ngayBD="+ endcoderesultNgayBDMA +"&ngayKT="+endcoderesultNgayKTMA;
        String url = "http://192.168.1.11/api/ThongKe/ThongKeHDTheoNgayLap?ngayBD=2019%2F05%2F22&ngayKT=2019%2F05%2F26";
//                String url = "192.168.1.11/api/ThongKe/ThongKeHDTheoNgayLap?ngayBD="+endcoderesultNgayBD+"&ngayKT="+endcoderesultNgayKT;
        Log.d("ABC", url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
                DoanhThu doanhThu = new DoanhThu();
//                doanhThuArrayList.clear();
                doanhThuArrayList =doanhThu.getListDoanhThu(response.toString());
                Log.d("teoteo",doanhThuArrayList.size()+"");
                for(int i = 0; i< response.length(); i++)
                {
                    arrayListSoDoanhThu.add(Integer.parseInt(doanhThuArrayList.get(i).getThanhTien()));
                    arrayListTenDoanhThu.add(doanhThuArrayList.get(i).getGioLapHD());
                }

                LineData d = new LineData();
                //add data vào list
                ArrayList<Entry> entries = new ArrayList<Entry>();
                for (int index = 0; index < arrayListSoDoanhThu.size(); index++) {
                    entries.add(new Entry(index,arrayListSoDoanhThu.get(index)));
                }
//                //set label cho biểu đồ
                LineDataSet set = new LineDataSet(entries, "Doanh Thu");
                set.setColor(Color.GREEN);
                set.setLineWidth(2.5f);
                set.setCircleColor(Color.GREEN);
                set.setCircleRadius(3f);
                set.setFillColor(Color.GREEN);
                set.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
                set.setDrawValues(true);
                set.setValueTextSize(10f);
                set.setValueTextColor(Color.GREEN);

                set.setAxisDependency(YAxis.AxisDependency.LEFT);
                d.addDataSet(set);

                //data 2 bên cột của biểu đồ
                YAxis leftAxis = chart.getAxisLeft();
                leftAxis.setDrawGridLines(false);
                leftAxis.setAxisMinimum(0f);

                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setAxisMinimum(0f);
                xAxis.setGranularity(1f);

//                //tên hiển thị ở dưới
                final List<String> xLabel = new ArrayList<>();
                for (int id = 0; id< arrayListTenDoanhThu.size(); id++)
                {
                    xLabel.add(arrayListTenDoanhThu.get(id));
                }
                Log.d("teoteo1111",arrayListTenDoanhThu.size()+"");

                //set lên biểu đồ
                CombinedData data = new CombinedData();
                LineData lineData = new LineData();
                lineData.addDataSet(set);
//
                data.setData(lineData);
//
                xAxis.setAxisMaximum(data.getXMax() + 0.25f);
//
                chart.setData(data);
                chart.invalidate();
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
