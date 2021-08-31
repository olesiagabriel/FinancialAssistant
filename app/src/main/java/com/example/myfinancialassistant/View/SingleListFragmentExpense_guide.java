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

import com.example.myfinancialassistant.Model.Expense_guide;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class SingleListFragmentExpense_guide extends ListFragment {
    ArrayList<Expense_guide> exp;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SingleListFragmentExpense_guide() throws ParseException {
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Expense_guide expense_guide=new Expense_guide();

        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
        expense_guide.ExpenseGuideListSelect(k);

        // income.IncomePeriodSelect(k,start, stop);
        exp=expense_guide.Expense_guide_list_object;
        MyListAdapterExpense_guide myListAdapter = new MyListAdapterExpense_guide(getActivity(),
                R.layout.listfragment_expense, exp);

        setListAdapter(myListAdapter);
    }
    // TODO: Customize parameter initialization

    /*public static SingleListFragmentExpense_guide newInstance(int columnCount) {
        SingleListFragmentExpense_guide fragment = new SingleListFragmentExpense_guide();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }*/

    public class MyListAdapterExpense_guide extends ArrayAdapter<Expense_guide> {

        private Context mContext;

        public MyListAdapterExpense_guide(Context context, int textViewResourceId,
                                         ArrayList<Expense_guide> objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.listfragment_expense_guide, parent, false);
            TextView textView13 = (TextView) row.findViewById(R.id.textView13);

            textView13.setText(exp.get(position).getExpense_type());
            //textView5.setText("руб.");

            ImageView imageViewExpense_guide_delete = (ImageView) row.findViewById(R.id.imageViewExpense_guide_delete);
            int User_Id_FK=exp.get(position).getUser_Id_FK();
            if(User_Id_FK==0){
                imageViewExpense_guide_delete.setVisibility(View.INVISIBLE);
            }
            imageViewExpense_guide_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id=exp.get(position).getId();
                    CustomDialogFragmentExpense_guide dialog = new CustomDialogFragmentExpense_guide();
                    Bundle args = new Bundle();
                    args.putString("id", String.valueOf(id));
                    dialog.setArguments(args);
                    dialog.show(getActivity().getSupportFragmentManager(), "custom");
                    /*int id=exp.get(position).getId();

                    Expense_guide expense_guide=new Expense_guide();
                    expense_guide.deleteExpense_guide(id);


                    ((BottomActivity)getActivity()).openExpenseTabFragment();*/
                }
            });

            return row;
        }
    }
}