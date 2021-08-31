package com.example.myfinancialassistant.View;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myfinancialassistant.Model.Income;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A fragment representing a list of Items.
 */
public class ReportIncome extends ListFragment {
    ArrayList<Income> in;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReportIncome() {
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

        Income income=new  Income();

        String inc=((MyApplication)((BottomActivity)getActivity()).getApplication()).getIncome_guide_Id();
       /* if (inc.equals("")){

            income.IncomePeriodSelect(k,start, stop);
        }
        else{*/
            income.IncomePeriodSelectGuideReport(k,start, stop, inc);
            ((MyApplication)((BottomActivity)getActivity()).getApplication()).setIncome_guide_Id("");
      //  ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStart_date("");
       // ((MyApplication)((BottomActivity)getActivity()).getApplication()).setStop_date("");

        //}





        // income.IncomePeriodSelect(k,start, stop);
        in=income.Income_list;
      //  TextView textView29=(TextView)getActivity().findViewById(R.id.textView29);
       // textView29.setText(String.valueOf(income.sum_report));
      //  ((MyApplication)((BottomActivity)getActivity()).getApplication()).setReportIncomeSize(income.sum_report);
        ReportIncome.MyListAdapter myListAdapter = new ReportIncome.MyListAdapter(getActivity(),
                R.layout.fragment_list_report_income, in);
        setListAdapter(myListAdapter);



      //  textView29.setText("xbgdfhdfgh");
    }


    public class MyListAdapter extends ArrayAdapter<Income> {

        private Context mContext;

        public MyListAdapter(Context context, int textViewResourceId,
                             ArrayList<Income> objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.fragment_list_report_income, parent, false);
            TextView textView40 = (TextView) row.findViewById(R.id.textView40);
            TextView textView41 = (TextView) row.findViewById(R.id.textView41);
            TextView textView42 = (TextView) row.findViewById(R.id.textView42);
            TextView textView43 = (TextView) row.findViewById(R.id.textView43);
            textView40.setText(in.get(position).getIncome_name());
            textView41.setText(in.get(position).getIncome_guide());
            textView42.setText(in.get(position).getIncome_date());
            textView43.setText(String.valueOf(in.get(position).getIncome_size()));
            //TextView textView5 = (TextView) row.findViewById(R.id.textView5);
            //textView5.setText("руб.");


            return row;
        }
    }

}
