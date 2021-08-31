package com.example.myfinancialassistant.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfinancialassistant.Model.Expense_guide;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddExpense_guideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpense_guideFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddExpense_guideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddExpense_guideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExpense_guideFragment newInstance(String param1, String param2) {
        AddExpense_guideFragment fragment = new AddExpense_guideFragment();
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
        View view= inflater.inflate(R.layout.fragment_add_expense_guide, container, false);

        Button add_expense_guide_button = (Button) view.findViewById(R.id.add_expense_guide_button);
        add_expense_guide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView16 = (EditText) view.findViewById(R.id.textView16);
                String stringtextView16=textView16.getText().toString();


                Expense_guide expense_guide = new Expense_guide();
                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
                expense_guide.insertExpense_guide(stringtextView16, k);

                Toast toast = Toast.makeText(getActivity(),
                        "Категория расхода добавлена!", Toast.LENGTH_LONG);
                toast.show();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openExpenseTabFragment();
            }
        });

        Button cancel_expense_guide_button = (Button) view.findViewById(R.id.cancel_expense_guide_button);
        cancel_expense_guide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openExpenseTabFragment();


            }
        });
        return view;
    }
}