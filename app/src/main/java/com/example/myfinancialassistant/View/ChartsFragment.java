package com.example.myfinancialassistant.View;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myfinancialassistant.Model.Expense;
import com.example.myfinancialassistant.Model.Income;
import com.example.myfinancialassistant.Model.Period_type;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChartsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    PieChart pieChart;
    PieChart pieChart2;
    EditText DateStart;
    EditText DateEnd;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    int datenumber=1;
    ArrayList<Expense> ex;
    ArrayList<Income> inc;
    private int key=0;
    public ChartsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiagrammsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChartsFragment newInstance(String param1, String param2) {
        ChartsFragment fragment = new ChartsFragment();
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
        View view=inflater.inflate(R.layout.fragment_charts, container, false);
      //  ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date("");
      //  ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date("");
        Spinner spinner7 = (Spinner) view.findViewById(R.id.spinner7);
        Period_type payment_type=new Period_type();
        payment_type.Period_typeListSelect();
        String[] Payment_type_list=payment_type.Period_type_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Payment_type_list);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter);
        spinner7.setSelection(((MyApplication)((BottomActivity)getActivity()).getApplication()).getdiagramposition());
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                if(key!=0) {
                    int n=spinner7.getSelectedItemPosition();

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


                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).settabidstatistics(0);
                    getView().setVisibility(View.GONE);
                    ((BottomActivity) getActivity()).openStatisticsTabFragment();}
                else{
                    key++;
                }
                // On selecting a spinner item

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pieChart =(PieChart)view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(25,10,10,10);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setDrawHoleEnabled(false);//строка меняет вид диаграммы с тру на фолс
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);




        ArrayList<PieEntry> yValues = new ArrayList<>();

        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        String start= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getStart_date();
        String stop= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getStop_date();

        Income income=new  Income();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);


        income.IncomePeriodSelectCharts(k,start, stop);
        inc=income.Income_list;
        for(int i=0; i<inc.size();i++){
            yValues.add(new PieEntry(inc.get(i).getsum_size(),inc.get(i).getIncome_guide()));
        }

        //Description description = new Description();
        //description.setText("Доходы");
        //description.setTextSize(15);
        //description.setTextAlign(Paint.Align.CENTER);
        //pieChart.setDescription(description);

        //pieChart.setUsePercentValues(false);

        pieChart.animateY(1000, Easing.EasingOption.EaseInCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(1f);
        //dataSet.getSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.TRANSPARENT);

        pieChart.setData(data);
        pieChart.setDrawSliceText(false); // To remove slice text
        pieChart.setDrawMarkers(false); // To remove markers when click
        pieChart.setDrawEntryLabels(false); // To remove labels from piece of pie
        pieChart.getDescription().setEnabled(false); // To remove description of pie
        Legend l = pieChart.getLegend(); // get legend of pie
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER); // set vertical alignment for legend
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // set horizontal alignment for legend
        l.setOrientation(Legend.LegendOrientation.VERTICAL); // set orientation for legend
        l.setDrawInside(false); // set if legend should be drawn inside or not


        pieChart2 =(PieChart)view.findViewById(R.id.piechart2);
        pieChart2.setUsePercentValues(true);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.setExtraOffsets(25,25,25,25);
        pieChart2.setDragDecelerationFrictionCoef(0.99f);
        pieChart2.setDrawHoleEnabled(false);//строка меняет вид диаграммы с тру на фолс
        pieChart2.setHoleColor(Color.WHITE);
        pieChart2.setTransparentCircleRadius(60f);


        ArrayList<PieEntry> yValues2 = new ArrayList<>();

        int k2= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        Expense expense=new  Expense();
        /*Calendar calendar2 = Calendar.getInstance();
        int month2 = calendar2.get(Calendar.MONTH)+1;
        int year2 = calendar2.get(Calendar.YEAR);
        String start2= String.valueOf(year2)+"-"+String.valueOf(month2)+"-01";
        if(month==11){
            month=0;
            year+=1;
        }
        else{
            month+=1;
        }
        String stop2=String.valueOf(year)+"-"+String.valueOf(month)+"-01";
*/

        expense.ExpensePeriodSelectCharts(k2,start, stop);
       //String ex2=((MyApplication)((BottomActivity)getActivity()).getApplication()).getExpense_guide_Id();
       /* if (ex2.equals("")){

            expense.ExpensePeriodSelect(k,start, stop);
        }
        else{
            expense.ExpensePeriodSelectGuide(k,start, stop, ex2);
            ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpense_guide_Id("");

        }*/
        ex=expense.Expense_list;
        for(int i=0; i<ex.size();i++){
            yValues2.add(new PieEntry(ex.get(i).getsum_size(),ex.get(i).getExpense_guide()));
        }

        //Description description2 = new Description();
        //description2.setText("Расходы");
        //description2.setTextSize(15);
        //pieChart2.setDescription(description2);


        pieChart2.animateY(1000, Easing.EasingOption.EaseInCubic);

        //pieChart2.animateY(1000, Easing.EasingOption.EaseInCubic);

        PieDataSet dataSet2 = new PieDataSet(yValues2, " ");
        dataSet2.setSliceSpace(1f);
       // dataSet2.getSelectionShift(5f);
        dataSet2.setColors(ColorTemplate.JOYFUL_COLORS);
        PieData data2 = new PieData(dataSet2);
        data2.setValueTextSize(10f);
        data2.setValueTextColor(Color.TRANSPARENT);

        pieChart2.setData(data2);
        pieChart2.setDrawSliceText(false); // To remove slice text
        pieChart2.setDrawMarkers(false); // To remove markers when click
        pieChart2.setDrawEntryLabels(false); // To remove labels from piece of pie
        pieChart2.getDescription().setEnabled(false); // To remove description of pie
        Legend l2 = pieChart2.getLegend(); // get legend of pie
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER); // set vertical alignment for legend
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); // set horizontal alignment for legend
        l2.setOrientation(Legend.LegendOrientation.VERTICAL); // set orientation for legend
        l2.setDrawInside(false); // set if legend should
       /* SingleListFragmentExpense.MyListAdapter myListAdapter = new SingleListFragmentExpense.MyListAdapter(getActivity(),
                R.layout.listfragment_expense, ex);
        setListAdapter(myListAdapter);*/

        DateStart = (EditText) view.findViewById(R.id.editTextDate13);
        DateStart.setFocusable(false);

        DateEnd = (EditText) view.findViewById(R.id.editTextDate14);
        DateEnd.setFocusable(false);
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


        DateStart.setText(((MyApplication)((BottomActivity)getActivity()).getApplication()).getStart_date());
        DateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenumber=1;
                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });


        DateEnd.setText(((MyApplication)((BottomActivity)getActivity()).getApplication()).getStop_date());
        DateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenumber=2;
                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        CheckBox checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox2.isChecked()){
                   // ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpense_guide_Id("");
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date(DateStart.getText().toString());
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date(DateEnd.getText().toString());
                    getView().setVisibility(View.GONE);
                    ((BottomActivity) getActivity()).openStatisticsTabFragment();
                }



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
            if (datenumber==1){

                DateStart.setText(myYear+"-"+ monthString + "-"+dayString);
            }
            else if (datenumber==2){
                DateEnd.setText(myYear+"-"+ monthString + "-"+dayString);
            }

        }

    };
}