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

import com.example.myfinancialassistant.Model.Income;
import com.example.myfinancialassistant.Model.Income_guide;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddIncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddIncomeFragment extends Fragment {

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
    EditText DateIncome;
    public AddIncomeFragment() {
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
    public static AddIncomeFragment newInstance(String param1, String param2) {
        AddIncomeFragment fragment = new AddIncomeFragment();
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
        View view=inflater.inflate(R.layout.fragment_add_income, container, false);
        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
        Income_guide income_guide=new Income_guide();
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        income_guide.IncomeGuideListSelect(k);
        String[] Income_guide_list=income_guide.Income_guide_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Income_guide_list);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
       // Locale.setDefault(Locale.GERMANY);
        DateIncome = (EditText) view.findViewById(R.id.DateIncome);
        DateIncome.setFocusable(false);
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

        DateIncome.setText(myYear+"-"+ monthString + "-"+dayString);
        DateIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        Button cancel_button = (Button) view.findViewById(R.id.cancel_income_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openIncomeTabFragment();


            }
        });
        Button add_income_button = (Button) view.findViewById(R.id.add_income_button);
        add_income_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText NameIncome = (EditText) view.findViewById(R.id.NameIncome);
                String stringNameIncome=NameIncome.getText().toString();
                EditText SizeIncome = (EditText) view.findViewById(R.id.SizeIncome);
                int intSizeIncome = Integer.valueOf(SizeIncome.getText().toString());
                EditText DateIncome = (EditText) view.findViewById(R.id.DateIncome);
                String stringDateIncome = DateIncome.getText().toString();
                Spinner spinner2 = (Spinner) view.findViewById(R.id.spinner2);
                String stringspinner2 = spinner2.getSelectedItem().toString();
                Income income = new Income();
                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
                income.insertIncome(k,stringNameIncome, intSizeIncome, stringDateIncome,stringspinner2);


                Toast toast = Toast.makeText(getActivity(),
                        "Доход добавлен!", Toast.LENGTH_LONG);
                toast.show();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openIncomeTabFragment();
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

            DateIncome.setText(myYear+"-"+ monthString + "-"+dayString);
        }

    };
}
