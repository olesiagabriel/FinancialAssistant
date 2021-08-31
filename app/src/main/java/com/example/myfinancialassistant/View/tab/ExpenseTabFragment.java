package com.example.myfinancialassistant.View.tab;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toolbar;

import com.example.myfinancialassistant.ViewModel.adapter.ViewPagerAdapter;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.View.ExpenseFragment;
import com.example.myfinancialassistant.View.Expense_guideFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseTabFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    PagerAdapter adapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    Context context;
    Toolbar toolbar;
    ImageView bizMenu;
    PopupMenu popup;

    public ExpenseTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpenseTabFragment newInstance(String param1, String param2) {
        ExpenseTabFragment fragment = new ExpenseTabFragment();
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
        View inflatedView = inflater.inflate(R.layout.fragment_expense_tab, container, false);
        getActivity().setTitle("Учет личных финансов");
        return inflatedView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // find views by id
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        ViewPager viewPager = view.findViewById(R.id.viewPager2);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout2);

        // attach tablayout with viewpager
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // add your fragments
        adapter.addFrag(new ExpenseFragment(), "Добавление");
        adapter.addFrag(new Expense_guideFragment(), "Управление категориями");
        // adapter.addFrag(new AddIncomeFragment(), "Список доходов");

        // set adapter on viewpager
        viewPager.setAdapter(adapter);
    }

}