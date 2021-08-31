package com.example.myfinancialassistant.View;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfinancialassistant.Model.Expense;
import com.example.myfinancialassistant.Model.Expense_guide;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpenseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    EditText DateExpense;
    public AddExpenseFragment() {
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
    public static AddExpenseFragment newInstance(String param1, String param2) {
        AddExpenseFragment fragment = new AddExpenseFragment();
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
        Calendar calendar = Calendar.getInstance();

        myYear = calendar.get(Calendar.YEAR);
        myMonth = calendar.get(Calendar.MONTH);
        myDay = calendar.get(Calendar.DAY_OF_MONTH);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_expense, container, false);
        Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner3);
        Expense_guide expense_guide=new Expense_guide();
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        expense_guide.ExpenseGuideListSelect(k);
        String[] Expense_guide_list=expense_guide.Expense_guide_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Expense_guide_list);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        // Locale.setDefault(Locale.GERMANY);
        DateExpense = (EditText) view.findViewById(R.id.DateExpense);
        DateExpense.setFocusable(false);
        String monthString="";
        if((myMonth+1)<10){
            monthString="0"+String.valueOf(myMonth+1);
        }
        else{
            monthString=String.valueOf(myMonth+1);
        }
        String dayString="";
        if(myDay<10){
            dayString="0"+String.valueOf(myDay);
        }
        else{
            dayString=String.valueOf(myDay);
        }


        DateExpense.setText(myYear+"-"+ monthString + "-"+dayString);
        DateExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        Button cancel_button = (Button) view.findViewById(R.id.cancel_expense_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openExpenseTabFragment();


            }
        });
        Button add_expense_button = (Button) view.findViewById(R.id.add_expense_button);
        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText NameExpense = (EditText) view.findViewById(R.id.NameExpense);
                String stringNameExpense=NameExpense.getText().toString();
                EditText SizeExpense = (EditText) view.findViewById(R.id.SizeExpense);
                int intSizeExpense = Integer.valueOf(SizeExpense.getText().toString());
                EditText DateExpense = (EditText) view.findViewById(R.id.DateExpense);
                String stringDateExpense = DateExpense.getText().toString();
                Spinner spinner3 = (Spinner) view.findViewById(R.id.spinner3);
                String stringspinner3 = spinner3.getSelectedItem().toString();
                Expense expense = new Expense();
                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
                expense.insertExpense(k,stringNameExpense, intSizeExpense, stringDateExpense,stringspinner3);

                Toast toast = Toast.makeText(getActivity(),
                        "Расход добавлен!", Toast.LENGTH_LONG);
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
        return view;
    }
    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            String monthString="";
            if((myMonth+1)<10){
                monthString="0"+String.valueOf(myMonth+1);
            }
            else{
                monthString=String.valueOf(myMonth+1);
            }
            String dayString="";
            if(myDay<10){
                dayString="0"+String.valueOf(myDay);
            }
            else{
                dayString=String.valueOf(myDay);
            }

            DateExpense.setText(myYear+"-"+ monthString + "-"+dayString);
        }

    };
}