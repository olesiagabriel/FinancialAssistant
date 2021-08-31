package com.example.myfinancialassistant.View;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfinancialassistant.Model.Payment;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class SingleListFragmentPaymentMonthly extends  ListFragment {

    ArrayList<Payment> pay;

    public SingleListFragmentPaymentMonthly() throws ParseException {
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int k = ((MyApplication) ((BottomActivity) getActivity()).getApplication()).getSomeVariable();
        Payment payment = new Payment();


        payment.PaymentSelectCategory(k, 1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pay = payment.Payment_list;

        SingleListFragmentPaymentMonthly.MyListAdapter myListAdapter = new SingleListFragmentPaymentMonthly.MyListAdapter(getActivity(),
                R.layout.listfragment_payment_monthly, pay);
        setListAdapter(myListAdapter);
    }

    public class MyListAdapter extends ArrayAdapter<Payment> {

        private Context mContext;

        public MyListAdapter(Context context, int textViewResourceId,
                             ArrayList<Payment> objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.listfragment_payment_monthly, parent, false);
            TextView textView24 = (TextView) row.findViewById(R.id.textView24);
            TextView textView25 = (TextView) row.findViewById(R.id.textView25);
            TextView textView26 = (TextView) row.findViewById(R.id.textView26);
            TextView textView27 = (TextView) row.findViewById(R.id.textView27);
            TextView textView54 = (TextView) row.findViewById(R.id.textView54);
            TextView textView55 = (TextView) row.findViewById(R.id.textView55);
            TextView textView64 = (TextView) row.findViewById(R.id.textView64);
            textView24.setText(pay.get(position).getPayment_name());

            String status="Не выполнено";
            if(pay.get(position).getExpense_status()==1){
                status="Выполнено";
            }

            textView25.setText(status);
            textView26.setText(pay.get(position).getExpense_date_start());
            textView27.setText(pay.get(position).getExpense_date_end());
            textView54.setText(pay.get(position).getPeriod_reminder());
            textView55.setText(String.valueOf(pay.get(position).getExpense_size()));
            textView64.setText(pay.get(position).getExpense_type_name());
            // TextView textView13 = (TextView) row.findViewById(R.id.textView13);
            // textView13.setText("руб.");
            ImageView iconImageView9 = (ImageView) row.findViewById(R.id.imageView9);
            iconImageView9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id=pay.get(position).getId();
                    CustomDialogFragmentPayment dialog = new CustomDialogFragmentPayment();
                    Bundle args = new Bundle();
                    args.putString("id", String.valueOf(id));
                    dialog.setArguments(args);
                    dialog.show(getActivity().getSupportFragmentManager(), "custom");

                }
            });
           /* DialogFragment dlg2;
            dlg2 = new Dialog1Fragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            // Show:
            dlg2.show(fragmentManager,"dlg2");*/
            //dlg2.show();
            ImageView iconImageView10 = (ImageView) row.findViewById(R.id.imageView10);
            iconImageView10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    /*int id=pay.get(position).getId();
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpenseUpdateId(id);
                    ((BottomActivity)getActivity()).openUpdateExpenseFragment();*/
                }
            });
            ImageView imageView6 = (ImageView) row.findViewById(R.id.imageView6);
            imageView6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id=pay.get(position).getId();
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setPaymentId(id);
                    ((BottomActivity)getActivity()).openUpdatePaymentFragment();
                }
            });

            return row;
        }
    }
}