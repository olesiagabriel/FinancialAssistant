package com.example.myfinancialassistant.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myfinancialassistant.Model.Expense_guide;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int key=0;

    public ExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExpenseFragment newInstance(String param1, String param2) {
        ExpenseFragment fragment = new ExpenseFragment();
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
        View view=inflater.inflate(R.layout.fragment_expense, container, false);
       // ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpense_guide_Id("");

        Spinner spinner5 = (Spinner) view.findViewById(R.id.spinner5);
        Expense_guide expense_guide=new Expense_guide();
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        expense_guide.ExpenseGuideListSelect(k);
        String[] Expense_guide_list=expense_guide.Expense_guide_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Expense_guide_list);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter);
        spinner5.setSelection(((MyApplication)((BottomActivity)getActivity()).getApplication()).getexpenseposition());

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                // On selecting a spinner item
                if(key!=0) {
                    ((MyApplication) ((BottomActivity) getActivity()).getApplication()).setExpense_guide_Id(spinner5.getSelectedItem().toString());
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setexpenseposition(spinner5.getSelectedItemPosition());
                    getView().setVisibility(View.GONE);
                    ((BottomActivity) getActivity()).openExpenseTabFragment();
                }
                else{
                     key++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FloatingActionButton floatingActionButtonExpense = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonExpense);
        floatingActionButtonExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openAddExpenseFragment();
            }
        });
        return view;
    }


}