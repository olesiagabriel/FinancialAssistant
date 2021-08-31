package com.example.myfinancialassistant.View;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myfinancialassistant.Model.Expense;
import com.example.myfinancialassistant.Model.Expense_guide;
import com.example.myfinancialassistant.Model.Period_type;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticsExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticsExpenseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int key=0;
    private int key2=0;
    EditText DateStart;
    EditText DateEnd;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    int datenumber=1;

    public StatisticsExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticsIncomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticsExpenseFragment newInstance(String param1, String param2) {
        StatisticsExpenseFragment fragment = new StatisticsExpenseFragment();
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
        View view=inflater.inflate(R.layout.fragment_statistics_expense, container, false);

        Spinner spinner13 = (Spinner) view.findViewById(R.id.spinner13);
        Expense_guide expense_guide=new Expense_guide();
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        expense_guide.ExpenseGuideListSelect(k);
        String[] Expense_guide_list=expense_guide.Expense_guide_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Expense_guide_list);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner13.setAdapter(adapter);

        Spinner spinner14 = (Spinner) view.findViewById(R.id.spinner14);
        Period_type payment_type=new Period_type();
        payment_type.Period_typeListSelect();
        String[] Payment_type_list=payment_type.Period_type_list;
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Payment_type_list);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner14.setAdapter(adapter2);
        spinner13.setSelection(((MyApplication)((BottomActivity)getActivity()).getApplication()).getexpenseposition());

        spinner14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if(key!=0) {
                    int n=spinner14.getSelectedItemPosition();

                    if (n==0){
                        Calendar calendar = Calendar.getInstance();
                        int month = calendar.get(Calendar.MONTH)+1;
                        int year = calendar.get(Calendar.YEAR);
                        String start= String.valueOf(year)+"-"+String.valueOf(month)+"-01";
                        if(month==11){
                            month=0;
                            year+=1;
                        }
                        else{
                            month+=1;
                        }
                        String stop=String.valueOf(year)+"-"+String.valueOf(month)+"-01";
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(start);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(stop);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setdiagramposition(0);
                    }
                    else if(n==1){
                        Calendar calendar = Calendar.getInstance();
                        int month = calendar.get(Calendar.MONTH)+1;
                        int year = calendar.get(Calendar.YEAR);
                        String stop=String.valueOf(year)+"-"+String.valueOf(month)+"-01";
                        if(month==0){
                            month=11;
                            year-=1;
                        }
                        else{
                            month-=1;
                        }
                        String start= String.valueOf(year)+"-"+String.valueOf(month)+"-01";


                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(start);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(stop);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setdiagramposition(1);
                    }
                    else if(n==2){
                        Calendar calendar = Calendar.getInstance();

                        int year = calendar.get(Calendar.YEAR);
                        String  start =String.valueOf(year)+"-01-01";

                        String stop= String.valueOf(year)+"-11-31";


                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(start);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(stop);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setdiagramposition(2);
                    }
                    else if(n==3) {
                        Calendar calendar = Calendar.getInstance();

                        int year = calendar.get(Calendar.YEAR);
                        String start =String.valueOf(year-1)+"-01-01";

                        String stop= String.valueOf(year-1)+"-11-31";


                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(start);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(stop);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setdiagramposition(3);
                    }
                    else if(n==4){
                        Calendar calendar1 = Calendar.getInstance();
                        Calendar calendar2 = Calendar.getInstance();
                        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek==1){
                            calendar1.add(Calendar.DAY_OF_MONTH,-6);
                        }
                        else if(dayOfWeek==2){
                            calendar2.add(Calendar.DAY_OF_MONTH,6);
                        }
                        else if(dayOfWeek==3){
                            calendar1.add(Calendar.DAY_OF_MONTH,-1);
                            calendar2.add(Calendar.DAY_OF_MONTH,5);
                        }
                        else if(dayOfWeek==4){
                            calendar1.add(Calendar.DAY_OF_MONTH,-2);
                            calendar2.add(Calendar.DAY_OF_MONTH,4);
                        }
                        else if(dayOfWeek==5){
                            calendar1.add(Calendar.DAY_OF_MONTH,-3);
                            calendar2.add(Calendar.DAY_OF_MONTH,3);
                        }
                        else if(dayOfWeek==6){
                            calendar1.add(Calendar.DAY_OF_MONTH,-4);
                            calendar2.add(Calendar.DAY_OF_MONTH,2);
                        }
                        else if(dayOfWeek==7){
                            calendar1.add(Calendar.DAY_OF_MONTH,-5);
                            calendar2.add(Calendar.DAY_OF_MONTH,1);
                        }

                        int month1 = calendar1.get(Calendar.MONTH);
                        int month2 = calendar2.get(Calendar.MONTH);
                        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
                        int year1 = calendar1.get(Calendar.YEAR);
                        int year2 = calendar2.get(Calendar.YEAR);
                        //int year = calendar.get(Calendar.YEAR);
                        String start= String.valueOf(year1)+"-"+String.valueOf(month1+1)+"-"+String.valueOf(day1);
                        String stop= String.valueOf(year2)+"-"+String.valueOf(month2+1)+"-"+String.valueOf(day2);

                        // String stop=String.valueOf(year)+"-"+String.valueOf(month)+"-01";
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(start);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(stop);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setdiagramposition(4);

                    }
                    else if(n==5){
                        Calendar calendar1 = Calendar.getInstance();
                        Calendar calendar2 = Calendar.getInstance();
                        calendar1.add(Calendar.DAY_OF_MONTH,-7);
                        calendar2.add(Calendar.DAY_OF_MONTH,-7);
                        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek==1){
                            calendar1.add(Calendar.DAY_OF_MONTH,-6);
                        }
                        else if(dayOfWeek==2){
                            calendar2.add(Calendar.DAY_OF_MONTH,6);
                        }
                        else if(dayOfWeek==3){
                            calendar1.add(Calendar.DAY_OF_MONTH,-1);
                            calendar2.add(Calendar.DAY_OF_MONTH,5);
                        }
                        else if(dayOfWeek==4){
                            calendar1.add(Calendar.DAY_OF_MONTH,-2);
                            calendar2.add(Calendar.DAY_OF_MONTH,4);
                        }
                        else if(dayOfWeek==5){
                            calendar1.add(Calendar.DAY_OF_MONTH,-3);
                            calendar2.add(Calendar.DAY_OF_MONTH,3);
                        }
                        else if(dayOfWeek==6){
                            calendar1.add(Calendar.DAY_OF_MONTH,-4);
                            calendar2.add(Calendar.DAY_OF_MONTH,2);
                        }
                        else if(dayOfWeek==7){
                            calendar1.add(Calendar.DAY_OF_MONTH,-5);
                            calendar2.add(Calendar.DAY_OF_MONTH,1);
                        }

                        int month1 = calendar1.get(Calendar.MONTH);
                        int month2 = calendar2.get(Calendar.MONTH);
                        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
                        int year1 = calendar1.get(Calendar.YEAR);
                        int year2 = calendar2.get(Calendar.YEAR);
                        //int year = calendar.get(Calendar.YEAR);
                        String start= String.valueOf(year1)+"-"+String.valueOf(month1+1)+"-"+String.valueOf(day1);
                        String stop= String.valueOf(year2)+"-"+String.valueOf(month2+1)+"-"+String.valueOf(day2);

                        // String stop=String.valueOf(year)+"-"+String.valueOf(month)+"-01";
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(start);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(stop);
                        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setdiagramposition(5);

                    }



                  /*  getView().setVisibility(View.GONE);
                    ((BottomActivity) getActivity()).openStatisticsTabFragment();*/}
                else{
                    key++;
                }
                // On selecting a spinner item

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        CheckBox checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox3.isChecked()){
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpense_guide_Id("");
                }
                else{
                    ((MyApplication) ((BottomActivity) getActivity()).getApplication()).setIncome_guide_Id(spinner13.getSelectedItem().toString());

                }


            }
        });

        spinner14.setSelection(((MyApplication)((BottomActivity)getActivity()).getApplication()).getdiagramposition());
        spinner13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                // On selecting a spinner item
                if(key2!=0) {
                    checkBox3.setChecked(false);
                    ((MyApplication) ((BottomActivity) getActivity()).getApplication()).setIncome_guide_Id(spinner13.getSelectedItem().toString());
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setincomeposition(spinner13.getSelectedItemPosition());
                    //getView().setVisibility(View.GONE);
                    // ((BottomActivity) getActivity()).openIncomeTabFragment();
                }
                else{
                    key2++;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        CheckBox checkBox5 = (CheckBox) view.findViewById(R.id.checkBox5);
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if(checkBox4.isChecked()){
                    // ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpense_guide_Id("");
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(DateStart.getText().toString());
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(DateEnd.getText().toString());
                    getView().setVisibility(View.GONE);
                    ((BottomActivity) getActivity()).openStatisticsTabFragment();
                }*/



            }
        });

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
        DateStart = (EditText) view.findViewById(R.id.editTextDate15);
        DateStart.setFocusable(false);

        String dstart=((MyApplication)((BottomActivity)getActivity()).getApplication()).getStart_date();
        if (dstart.equals("")){
            DateStart.setText(myYear+"-"+ monthString + "-"+dayString);
        }
        else{
            DateStart.setText(dstart);
        }
        DateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenumber=1;
                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        DateEnd = (EditText) view.findViewById(R.id.editTextDate16);
        DateEnd.setFocusable(false);

        String dend=((MyApplication)((BottomActivity)getActivity()).getApplication()).getStop_date();
        if (dend.equals("")){
            DateEnd.setText(myYear+"-"+ monthString + "-"+dayString);
        }
        else{
            DateEnd.setText(dend);
        }


        DateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenumber=2;
                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });

        Button button6 = (Button) view.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox5.isChecked()) {
                    ((MyApplication) ((BottomActivity) getActivity()).getApplication()).setStart_date(DateStart.getText().toString());
                    ((MyApplication) ((BottomActivity) getActivity()).getApplication()).setStop_date(DateEnd.getText().toString());


                }
                ((MyApplication)((BottomActivity)getActivity()).getApplication()).settabidstatistics(1);

                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openStatisticsTabFragment();
            }
        });



        int k2= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        String start=((MyApplication)((BottomActivity)getActivity()).getApplication()).getStart_date();
        String stop=((MyApplication)((BottomActivity)getActivity()).getApplication()).getStop_date();
        if(start.equals("")||(stop.equals(""))){
            Calendar calendar = Calendar.getInstance();


            int month = calendar.get(Calendar.MONTH)+1;
            int year = calendar.get(Calendar.YEAR);


            start= String.valueOf(year)+"-"+String.valueOf(month)+"-01";
            if(month==11){
                month=0;
                year+=1;
            }
            else{
                month+=1;
            }
            stop=String.valueOf(year)+"-"+String.valueOf(month)+"-01";

        }

        Expense expense=new Expense();

        String exp=((MyApplication)((BottomActivity)getActivity()).getApplication()).getExpense_guide_Id();
       /* if (inc.equals("")){

            income.IncomePeriodSelect(k,start, stop);
        }
        else{*/
        expense.ExpensePeriodSelectGuideReport(k2,start, stop, exp);
        //((MyApplication)((BottomActivity)getActivity()).getApplication()).setIncome_guide_Id("");
        //  ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date("");
        // ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date("");

        //}




        TextView textView51 = (TextView) view.findViewById(R.id.textView51);
        //  int sum=((MyApplication)((BottomActivity)getActivity()).getApplication()).getReportIncomeSize();
        textView51.setText(String.valueOf(expense.sum_report));

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
            if (datenumber==1){

                DateStart.setText(myYear+"-"+ monthString + "-"+dayString);
            }
            else if (datenumber==2){
                DateEnd.setText(myYear+"-"+ monthString + "-"+dayString);
            }

        }

    };
}