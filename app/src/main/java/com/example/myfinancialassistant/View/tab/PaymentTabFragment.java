package com.example.myfinancialassistant.View.tab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfinancialassistant.View.Payment_amountFragment;
import com.example.myfinancialassistant.ViewModel.adapter.ViewPagerAdapter;
import com.example.myfinancialassistant.View.Payment_yearlyFragment;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.View.Payment_monthlyFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentTabFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public PaymentTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentTabFragment newInstance(String param1, String param2) {
        PaymentTabFragment fragment = new PaymentTabFragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_payment_tab, container, false);
        getActivity().setTitle("Учет личных финансов");
        return inflatedView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // find views by id
        ViewPager viewPager_payment = view.findViewById(R.id.viewPager_payment);
        TabLayout tab_payment = view.findViewById(R.id.tab_payment);
        //tabLayout.getTabAt(0).setCustomView(tabOne);
        // attach tablayout with viewpager
        tab_payment.setupWithViewPager(viewPager_payment);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // add your fragments
        adapter.addFrag(new Payment_amountFragment(), "Единичные");
        adapter.addFrag(new Payment_monthlyFragment(), "Ежемесячные");
        adapter.addFrag(new Payment_yearlyFragment(), "Ежегодные");

        // set adapter on viewpager
        viewPager_payment.setAdapter(adapter);
    }
}