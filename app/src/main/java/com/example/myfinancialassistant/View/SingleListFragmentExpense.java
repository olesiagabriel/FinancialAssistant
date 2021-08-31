package com.example.myfinancialassistant.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import com.example.myfinancialassistant.Model.Expense;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class SingleListFragmentExpense extends ListFragment {
    ArrayList<Expense> ex;

    public SingleListFragmentExpense() throws ParseException {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        Expense expense=new  Expense();

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



        String ex2=((MyApplication)((BottomActivity)getActivity()).getApplication()).getExpense_guide_Id();
        if (ex2.equals("")){

            expense.ExpensePeriodSelect(k,start, stop);
        }
        else{
            expense.ExpensePeriodSelectGuide(k,start, stop, ex2);
            ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpense_guide_Id("");

        }
        ex=expense.Expense_list;

        MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
                R.layout.listfragment_expense, ex);
        setListAdapter(myListAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

       // Toast.makeText(getActivity(), "Вы выбрали позицию: " + position, Toast.LENGTH_SHORT).show();
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

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.listfragment_expense, parent, false);
            TextView textView7 = (TextView) row.findViewById(R.id.textView7);
            TextView textView10 = (TextView) row.findViewById(R.id.textView10);
            TextView textView11 = (TextView) row.findViewById(R.id.textView11);
            TextView textView12 = (TextView) row.findViewById(R.id.textView12);
            textView7.setText(ex.get(position).getExpense_name());
            textView10.setText(ex.get(position).getExpense_guide());
            textView11.setText(ex.get(position).getExpense_date());
            textView12.setText(String.valueOf(ex.get(position).getExpense_size()));
           // TextView textView13 = (TextView) row.findViewById(R.id.textView13);
           // textView13.setText("руб.");
            ImageView iconImageView = (ImageView) row.findViewById(R.id.imageView3);
            iconImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id=ex.get(position).getId();
                    CustomDialogFragmentExpense dialog = new CustomDialogFragmentExpense();
                    Bundle args = new Bundle();
                    args.putString("id", String.valueOf(id));
                    dialog.setArguments(args);
                    dialog.show(getActivity().getSupportFragmentManager(), "custom");
                   /* int id=ex.get(position).getId();

                    Expense expense=new  Expense();
                    expense.deleteExpense(id);


                    ((BottomActivity)getActivity()).openExpenseTabFragment();*/
                }
            });
           /* DialogFragment dlg2;
            dlg2 = new Dialog1Fragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            // Show:
            dlg2.show(fragmentManager,"dlg2");*/
            //dlg2.show();
            ImageView iconImageView4 = (ImageView) row.findViewById(R.id.imageView4);
            iconImageView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id=ex.get(position).getId();
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpenseUpdateId(id);
                    ((BottomActivity)getActivity()).openUpdateExpenseFragment();
                }
            });

            return row;
        }
    }





}
