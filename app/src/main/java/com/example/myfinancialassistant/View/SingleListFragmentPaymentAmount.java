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
public class SingleListFragmentPaymentAmount extends ListFragment {

    ArrayList<Payment> pay;

    public SingleListFragmentPaymentAmount() throws ParseException {
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int k = ((MyApplication) ((BottomActivity) getActivity()).getApplication()).getSomeVariable();
        Payment payment = new Payment();


        payment.PaymentSelectCategory(k, 3);

        pay = payment.Payment_list;

        SingleListFragmentPaymentAmount.MyListAdapter myListAdapter = new SingleListFragmentPaymentAmount.MyListAdapter(getActivity(),
                R.layout.listfragment_payment_amount, pay);
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
            View row = inflater.inflate(R.layout.listfragment_payment_amount, parent, false);
            TextView textView19 = (TextView) row.findViewById(R.id.textView19);
            TextView textView20 = (TextView) row.findViewById(R.id.textView20);
            TextView textView21 = (TextView) row.findViewById(R.id.textView21);
            TextView textView22 = (TextView) row.findViewById(R.id.textView22);
            TextView textView52 = (TextView) row.findViewById(R.id.textView52);
            TextView textView53 = (TextView) row.findViewById(R.id.textView53);
            TextView textView62 = (TextView) row.findViewById(R.id.textView62);
            textView19.setText(pay.get(position).getPayment_name());

            String status="Не выполнено";
            if(pay.get(position).getExpense_status()==1){
                status="Выполнено";
            }

            textView20.setText(status);
            textView21.setText(pay.get(position).getExpense_date_start());
            textView22.setText(pay.get(position).getExpense_date_end());
            textView52.setText(pay.get(position).getPeriod_reminder());
            textView53.setText(String.valueOf(pay.get(position).getExpense_size()));
            textView62.setText(pay.get(position).getExpense_type_name());
            // TextView textView13 = (TextView) row.findViewById(R.id.textView13);
            // textView13.setText("руб.");
            ImageView iconImageView = (ImageView) row.findViewById(R.id.imageView7);
            iconImageView.setOnClickListener(new View.OnClickListener() {
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
            ImageView iconImageView8 = (ImageView) row.findViewById(R.id.imageView8);
            iconImageView8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Payment payment=new  Payment();
                    int newStatus=0;
                    if(pay.get(position).getExpense_status()==0){
                        newStatus=1;
                    }

                    payment.updatePayment(pay.get(position).getId(),newStatus);

                    ((BottomActivity)getActivity()).openPaymentTabFragment();

                    /*int id=pay.get(position).getId();
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpenseUpdateId(id);
                    ((BottomActivity)getActivity()).openUpdateExpenseFragment();*/
                }

            });
            ImageView imageView5 = (ImageView) row.findViewById(R.id.imageView5);
            imageView5.setOnClickListener(new View.OnClickListener() {
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