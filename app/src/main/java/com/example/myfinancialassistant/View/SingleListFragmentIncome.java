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

import com.example.myfinancialassistant.Model.Income;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class SingleListFragmentIncome extends ListFragment {
    ArrayList<Income> in;

    public SingleListFragmentIncome() throws ParseException {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        Income income=new  Income();

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

            String inc=((MyApplication)((BottomActivity)getActivity()).getApplication()).getIncome_guide_Id();
            if (inc.equals("")){

            income.IncomePeriodSelect(k,start, stop);
        }
            else{
            income.IncomePeriodSelectGuide(k,start, stop, inc);
            ((MyApplication)((BottomActivity)getActivity()).getApplication()).setIncome_guide_Id("");

        }
          // income.IncomePeriodSelect(k,start, stop);
           in=income.Income_list;

        MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
                R.layout.listfragment_income, in);
        setListAdapter(myListAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Toast.makeText(getActivity(), "Вы выбрали позицию: " + position, Toast.LENGTH_SHORT).show();
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
            View row = inflater.inflate(R.layout.listfragment_income, parent, false);
            TextView textViewName = (TextView) row.findViewById(R.id.textViewName);
            TextView textViewName1 = (TextView) row.findViewById(R.id.textViewName1);
            TextView textViewName2 = (TextView) row.findViewById(R.id.textViewName2);
            TextView textViewName3 = (TextView) row.findViewById(R.id.textViewName3);
            textViewName.setText(in.get(position).getIncome_name());
            textViewName1.setText(in.get(position).getIncome_guide());
            textViewName2.setText(in.get(position).getIncome_date());
            textViewName3.setText(String.valueOf(in.get(position).getIncome_size()));
            //TextView textView5 = (TextView) row.findViewById(R.id.textView5);
            //textView5.setText("руб.");
            ImageView iconImageView = (ImageView) row.findViewById(R.id.imageView);
            iconImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id=in.get(position).getId();
                    CustomDialogFragmentIncome dialog = new CustomDialogFragmentIncome();
                    Bundle args = new Bundle();
                    args.putString("id", String.valueOf(id));
                    dialog.setArguments(args);
                    dialog.show(getActivity().getSupportFragmentManager(), "custom");
                   /* int id=in.get(position).getId();
                    Income income=new  Income();
                    income.deleteIncome(id);

                    ((BottomActivity)getActivity()).openIncomeTabFragment();*/
                }
            });
            ImageView iconimageView2 = (ImageView) row.findViewById(R.id.imageView2);
            iconimageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id=in.get(position).getId();
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setIncomeUpdateId(id);
                    ((BottomActivity)getActivity()).openUpdateIncomeFragment();
                }
            });

            return row;
        }
}

}