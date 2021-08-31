package com.example.myfinancialassistant.View;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myfinancialassistant.Model.Expense;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A fragment representing a list of Items.
 */
public class ReportExpense extends ListFragment {

    ArrayList<Expense> ex;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReportExpense() {
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
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
        expense.ExpensePeriodSelectGuideReport(k,start, stop, exp);
        ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpense_guide_Id("");

        ex=expense.Expense_list;

        ReportExpense.MyListAdapter myListAdapter = new ReportExpense.MyListAdapter(getActivity(),
                R.layout.fragment_list_report_expense, ex);
        setListAdapter(myListAdapter);
        TextView textView51=(TextView)getActivity().findViewById(R.id.textView51);
        textView51.setText(String.valueOf(expense.sum_report));
    }


    public class MyListAdapter extends ArrayAdapter<Expense> {

        private Context mContext;

        public MyListAdapter(Context context, int textViewResourceId,
                             ArrayList<Expense> objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.fragment_list_report_expense, parent, false);
            TextView textView44 = (TextView) row.findViewById(R.id.textView44);
            TextView textView45 = (TextView) row.findViewById(R.id.textView45);
            TextView textView46 = (TextView) row.findViewById(R.id.textView46);
            TextView textView47 = (TextView) row.findViewById(R.id.textView47);
            textView44.setText(ex.get(position).getExpense_name());
            textView45.setText(ex.get(position).getExpense_guide());
            textView46.setText(ex.get(position).getExpense_date());
            textView47.setText(String.valueOf(ex.get(position).getExpense_size()));


            return row;
        }
    }
}
