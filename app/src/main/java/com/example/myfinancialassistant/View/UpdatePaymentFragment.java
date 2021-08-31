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

import com.example.myfinancialassistant.Model.Expense_guide;
import com.example.myfinancialassistant.Model.Payment;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdatePaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdatePaymentFragment extends Fragment {

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
    EditText editTextDate5;
    EditText editTextDate6;
    EditText editTextDate19;

    int datenumber=1;

    public UpdatePaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdatePaymentAmountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdatePaymentFragment newInstance(String param1, String param2) {
        UpdatePaymentFragment fragment = new UpdatePaymentFragment();
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
        View view= inflater.inflate(R.layout.fragment_update_payment, container, false);

        getActivity().setTitle("Редактирование платежа");
        int idPayment= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getPaymentId();
        Payment payment = new Payment();
        payment.getPayment(idPayment);
        EditText textView35 = (EditText) view.findViewById(R.id.textView35);
        textView35.setText(payment.getPayment_name());

        EditText editTextNumber5 = (EditText) view.findViewById(R.id.editTextNumber5);
        editTextNumber5.setText(String.valueOf(payment.getExpense_size()));

        editTextDate5 = (EditText) view.findViewById(R.id.editTextDate5);
        editTextDate5.setText(payment.getExpense_date_start());

        editTextDate6 = (EditText) view.findViewById(R.id.editTextDate6);
        editTextDate6.setText(payment.getExpense_date_end());

        editTextDate19 = (EditText) view.findViewById(R.id.editTextDate19);
        editTextDate19.setText(payment.getPeriod_reminder());




        Spinner spinner9 = (Spinner) view.findViewById(R.id.spinner9);
        Expense_guide expense_guide=new Expense_guide();
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        expense_guide.ExpenseGuideListSelect(k);
        String[] Expense_guide_list=expense_guide.Expense_guide_list;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Expense_guide_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter);
        int sp=adapter.getPosition(payment.getExpense_type_name());
        spinner9.setSelection(sp);

        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);



                ((BottomActivity)getActivity()).openPaymentTabFragment();


            }
        });

        editTextDate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenumber=1;
                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        editTextDate6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenumber=1;
                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });
        editTextDate19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datenumber=1;
                new DatePickerDialog(getActivity(),
                        myCallBack, myYear, myMonth, myDay)
                        .show();
            }
        });


        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment payment = new Payment();
                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getPaymentId();
                payment.updatePaymentAll(k,editTextDate5.getText().toString(),
                        editTextDate6.getText().toString(),
                        textView35.getText().toString(),
                        Integer.valueOf(editTextNumber5.getText().toString()),
                                editTextDate19.getText().toString(),
                        spinner9.getSelectedItem().toString()
                        );

                Toast toast = Toast.makeText(getActivity(),
                        "Изменения сохранены!", Toast.LENGTH_LONG);
                toast.show();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getView().setVisibility(View.GONE);


                ((BottomActivity)getActivity()).openPaymentTabFragment();
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

                editTextDate5.setText(myYear+"-"+ monthString + "-"+dayString);
            }
            else if (datenumber==2){
                editTextDate6.setText(myYear+"-"+ monthString + "-"+dayString);
            }
            else if(datenumber==3){
                editTextDate19.setText(myYear+"-"+ monthString + "-"+dayString);
            }

        }

    };
}