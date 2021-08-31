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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateExpenseFragment extends Fragment {

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
    EditText editTextDate;

    public UpdateExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateExpenseFragment newInstance(String param1, String param2) {
        UpdateExpenseFragment fragment = new UpdateExpenseFragment();
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
        View view=inflater.inflate(R.layout.fragment_update_expense, container, false);
        getActivity().setTitle("Редактирование расхода");
        int idExpense= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getExpenseUpdateId();
        Expense expense = new Expense();
        expense.getExpense(idExpense);
        EditText editTextName = (EditText) view.findViewById(R.id.editTextName);

        EditText editTextNumber2 = (EditText) view.findViewById(R.id.editTextNumber2);

         editTextDate = (EditText) view.findViewById(R.id.editTextDate);

        Spinner spinner4 = (Spinner) view.findViewById(R.id.spinner4);
        editTextName.setText(expense.getExpense_name());
        editTextNumber2.setText(String.valueOf(expense.getExpense_size()));
        editTextDate.setText(expense.getExpense_date());
        Expense_guide expense_guide=new Expense_guide();
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        expense_guide.ExpenseGuideListSelect(k);
        String[] Expense_guide_list=expense_guide.Expense_guide_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Expense_guide_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter);
        int sp=adapter.getPosition(expense.getExpense_guide());
        spinner4.setSelection(sp);

        Button cancel_update_expense = (Button) view.findViewById(R.id.cancel_update_expense);
        cancel_update_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);



                ((BottomActivity)getActivity()).openExpenseTabFragment();


            }
        });
        Calendar calendar = Calendar.getInstance();
        Date dat=new Date();
      //  String dtStart = "2010-10-15";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
             dat = format.parse(editTextDate.getText().toString());
            calendar.setTime(dat);
          //  System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myYear = calendar.get(Calendar.YEAR);
        myMonth = calendar.get(Calendar.MONTH);
        myDay = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        Button update_expense = (Button) view.findViewById(R.id.update_expense);
        update_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = (EditText) view.findViewById(R.id.editTextName);
                String stringeditTextName=editTextName.getText().toString();
                EditText editTextNumber2 = (EditText) view.findViewById(R.id.editTextNumber2);
                int inteditTextNumber2 = Integer.valueOf(editTextNumber2.getText().toString());
                EditText editTextDate = (EditText) view.findViewById(R.id.editTextDate);
                String stringeditTextDate = editTextDate.getText().toString();
                Spinner spinner4 = (Spinner) view.findViewById(R.id.spinner4);
                String stringspinner4 = spinner4.getSelectedItem().toString();
                Expense expense = new Expense();
                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getExpenseUpdateId();
                expense.updateExpense(k,stringeditTextName, inteditTextNumber2, stringeditTextDate,stringspinner4);

                Toast toast = Toast.makeText(getActivity(),
                        "Изменения сохранены!", Toast.LENGTH_LONG);
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
            editTextDate.setText(myYear+"-"+ monthString + "-"+dayString);
        }

    };

}