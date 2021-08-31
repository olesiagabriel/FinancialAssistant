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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateIncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateIncomeFragment extends Fragment {

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
    EditText editTextDate2;

    public UpdateIncomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateIncomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateIncomeFragment newInstance(String param1, String param2) {
        UpdateIncomeFragment fragment = new UpdateIncomeFragment();
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
        View view=inflater.inflate(R.layout.fragment_update_income, container, false);
        getActivity().setTitle("Редактирование дохода");
        int idIncome= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getIncomeUpdateId();
        Income income = new Income();
        income.getIncome(idIncome);
        EditText editTextName2 = (EditText) view.findViewById(R.id.editTextName2);

        EditText editTextNumber3 = (EditText) view.findViewById(R.id.editTextNumber3);

         editTextDate2 = (EditText) view.findViewById(R.id.editTextDate2);

        Spinner spinner6 = (Spinner) view.findViewById(R.id.spinner6);
        editTextName2.setText(income.getIncome_name());
        editTextNumber3.setText(String.valueOf(income.getIncome_size()));
        editTextDate2.setText(income.getIncome_date());
        Income_guide income_guide=new Income_guide();
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        income_guide.IncomeGuideListSelect(k);
        String[] Income_guide_list=income_guide.Income_guide_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Income_guide_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter);
        int sp=adapter.getPosition(income.getIncome_guide());
        spinner6.setSelection(sp);

        Button cancel_update_income = (Button) view.findViewById(R.id.cancel_update_income);
        cancel_update_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openIncomeTabFragment();


            }
        });
        Calendar calendar = Calendar.getInstance();
        Date dat=new Date();
        //  String dtStart = "2010-10-15";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dat = format.parse(editTextDate2.getText().toString());
            calendar.setTime(dat);
            //  System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myYear = calendar.get(Calendar.YEAR);
        myMonth = calendar.get(Calendar.MONTH);
        myDay = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        Button update_income = (Button) view.findViewById(R.id.update_income);
        update_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName2 = (EditText) view.findViewById(R.id.editTextName2);
                String stringeditTextName2=editTextName2.getText().toString();
                EditText editTextNumber3 = (EditText) view.findViewById(R.id.editTextNumber3);
                int inteditTextNumber3 = Integer.valueOf(editTextNumber3.getText().toString());
                EditText editTextDate2 = (EditText) view.findViewById(R.id.editTextDate2);
                String stringeditTextDate2 = editTextDate2.getText().toString();
                Spinner spinner6 = (Spinner) view.findViewById(R.id.spinner6);
                String stringspinner6 = spinner6.getSelectedItem().toString();
                Income income = new Income();
                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getIncomeUpdateId();
                income.updateIncome(k,stringeditTextName2, inteditTextNumber3, stringeditTextDate2,stringspinner6);

                Toast toast = Toast.makeText(getActivity(),
                        "Изменения сохранены!", Toast.LENGTH_LONG);
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
            editTextDate2.setText(myYear+"-"+ monthString + "-"+dayString);
        }

    };

}