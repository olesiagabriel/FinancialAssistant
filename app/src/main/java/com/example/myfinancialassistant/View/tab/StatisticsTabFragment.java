package com.example.myfinancialassistant.View.tab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.View.ChartsFragment;
import com.example.myfinancialassistant.ViewModel.adapter.ViewPagerAdapter;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.View.StatisticsExpenseFragment;
import com.example.myfinancialassistant.View.StatisticsIncomeFragment;
import com.example.myfinancialassistant.ViewModel.BottomActivity;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticsTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsTabFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatisticsTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FifthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticsTabFragment newInstance(String param1, String param2) {
        StatisticsTabFragment fragment = new StatisticsTabFragment();
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
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_statistics_tab, container, false);
        getActivity().setTitle("Учет личных финансов");
        TabLayout tb=(TabLayout)inflatedView.findViewById(R.id.statisticstab);
        int r=  ((MyApplication)((BottomActivity)getActivity()).getApplication()).gettabidstatistics();
        tb.getTabAt(r).select();
        return inflatedView;

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // find views by id
        ViewPager statisticspager = view.findViewById(R.id.statisticspager);
        TabLayout statisticstab = view.findViewById(R.id.statisticstab);

        // attach tablayout with viewpager
        statisticstab.setupWithViewPager(statisticspager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // add your fragments
        adapter.addFrag(new ChartsFragment(), "Диаграммы" );
        adapter.addFrag(new StatisticsIncomeFragment(), "Доходы");
        adapter.addFrag(new StatisticsExpenseFragment(), "Расходы");


        // set adapter on viewpager
        statisticspager.setAdapter(adapter);
      /* TabLayout tb=(TabLayout)getActivity().findViewById(R.id.statisticstab);
        int r=  ((MyApplication)((BottomActivity)getActivity()).getApplication()).gettabidstatistics();
        tb.getTabAt(1).select();*/
        //statisticstab
    }
}