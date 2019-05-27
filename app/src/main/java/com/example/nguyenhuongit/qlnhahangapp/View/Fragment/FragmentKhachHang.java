package com.example.nguyenhuongit.qlnhahangapp.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyenhuongit.qlnhahangapp.Adapter.KhachHangAdapter;
import com.example.nguyenhuongit.qlnhahangapp.Data.KhachHang;
import com.example.nguyenhuongit.qlnhahangapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private OnFragmentInteractionListener mListener;

    public FragmentKhachHang() {
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
        getDataListTest();
        return view;
    }

    private void getDataListTest() {
        KhachHang khachHang = new KhachHang("Nguyễn Văn A","0123123123");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Văn Tèo","0923821123");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Thị B","123123141");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Văn A","112312321");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Văn A","0123123123");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Văn A","0123123123");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Văn A","0123123123");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Văn A","0123123123");
        khachHangArrayList.add(khachHang);
        khachHang = new KhachHang("Nguyễn Văn A","0123123123");
        khachHangArrayList.add(khachHang);

        khachHangAdapter = new KhachHangAdapter(getContext(),R.layout.item_custom_khachhang,khachHangArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(khachHangAdapter);
        khachHangAdapter.notifyDataSetChanged();
    }

    private void getDataList() {
//        String url ="https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + keyword + "&maxResults=50&type=video&key="+API_KEYPLAYLIST;
//        Log.d("BBB",url);
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonItems = response.getJSONArray("items");
//                            String title ="";
//                            String urlvideo ="";
//                            String idvideo = "";
//                            for(int i = 0; i < jsonItems.length();i++)
//                            {
//                                JSONObject jsonObject = jsonItems.getJSONObject(i);
//                                JSONObject jsonSnippet = jsonObject.getJSONObject("snippet");
//                                JSONObject jsonID = jsonObject.getJSONObject("id");
//                                title = jsonSnippet.getString("title");
//                                JSONObject jsonThumbnails = jsonSnippet.getJSONObject("thumbnails");
//                                JSONObject jsonMedium = jsonThumbnails.getJSONObject("medium");
//                                urlvideo = jsonMedium.getString("url");
//                                idvideo = jsonID.getString("videoId");
//
//                                recommendedVideoArrayList.add(new RecommendedVideo(title,urlvideo,idvideo));
//                            }
//                            recommendedVideoAdapter = new RecommendedVideoAdapter(getApplicationContext(),R.layout.item_custom_video,recommendedVideoArrayList);
//
//                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
//                            recyclerView.setLayoutManager(mLayoutManager);
//                            recyclerView.setItemAnimator(new DefaultItemAnimator());
//                            recyclerView.setAdapter(recommendedVideoAdapter);
//                            recommendedVideoAdapter.notifyDataSetChanged();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
////                        Toast.makeText(MusicMovie.this, response.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(RecommendedMovie.this, "Error!!!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
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
