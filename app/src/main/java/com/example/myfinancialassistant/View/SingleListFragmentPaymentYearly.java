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
public class SingleListFragmentPaymentYearly extends ListFragment {


    ArrayList<Payment> pay;

    public SingleListFragmentPaymentYearly() throws ParseException {
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int k = ((MyApplication) ((BottomActivity) getActivity()).getApplication()).getSomeVariable();
        Payment payment = new Payment();


        payment.PaymentSelectCategory(k, 2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pay = payment.Payment_list;

        SingleListFragmentPaymentYearly.MyListAdapter myListAdapter = new SingleListFragmentPaymentYearly.MyListAdapter(getActivity(),
                R.layout.listfragment_payment_yearly, pay);
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
//ПРОВЕРИТЬ ИМЕНА КОМПОНЕНТОВ ПРИ ЗАПУСКЕ
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.listfragment_payment_yearly, parent, false);
            TextView textView30 = (TextView) row.findViewById(R.id.textView30);
            TextView textView31 = (TextView) row.findViewById(R.id.textView31);
            TextView textView32 = (TextView) row.findViewById(R.id.textView32);
            TextView textView33 = (TextView) row.findViewById(R.id.textView33);
            TextView textView56 = (TextView) row.findViewById(R.id.textView56);
            TextView textView57 = (TextView) row.findViewById(R.id.textView57);
            TextView textView63 = (TextView) row.findViewById(R.id.textView63);
            textView30.setText(pay.get(position).getPayment_name());

            String status="Не выполнено";
            if(pay.get(position).getExpense_status()==1){
                status="Выполнено";
            }

            textView31.setText(status);
            textView32.setText(pay.get(position).getExpense_date_start());
            textView33.setText(pay.get(position).getExpense_date_end());
            textView56.setText(pay.get(position).getPeriod_reminder());
            textView63.setText(pay.get(position).getExpense_type_name());
            textView57.setText(String.valueOf(pay.get(position).getExpense_size()));
            // TextView textView13 = (TextView) row.findViewById(R.id.textView13);
            // textView13.setText("руб.");
            ImageView iconImageView11 = (ImageView) row.findViewById(R.id.imageView11);
            iconImageView11.setOnClickListener(new View.OnClickListener() {
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
            ImageView iconImageView12 = (ImageView) row.findViewById(R.id.imageView12);
            iconImageView12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    /*int id=pay.get(position).getId();
                    ((MyApplication)((BottomActivity)getActivity()).getApplication()).setExpenseUpdateId(id);
                    ((BottomActivity)getActivity()).openUpdateExpenseFragment();*/
                }
            });
            ImageView imageView13 = (ImageView) row.findViewById(R.id.imageView13);
            imageView13.setOnClickListener(new View.OnClickListener() {
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