package com.example.nan.h_app.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nan.h_app.R;
import com.github.mikephil.charting.charts.PieChart;

/**
 * Created by Nan on 18/11/2560.
 */

public class MainWalletFragment extends Fragment {
    private PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //  showPiechaet();
        View view = inflater.inflate(R.layout.fragment_main_wallet, container, false);
        return view;
    }

}



