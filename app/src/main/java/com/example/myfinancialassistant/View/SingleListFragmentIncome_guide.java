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

import com.example.myfinancialassistant.Model.Income_guide;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class SingleListFragmentIncome_guide extends ListFragment {
    ArrayList<Income_guide> in;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SingleListFragmentIncome_guide() throws ParseException {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Income_guide income_guide=new  Income_guide();

            int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
            income_guide.IncomeGuideListSelect(k);

        // income.IncomePeriodSelect(k,start, stop);
        in=income_guide.Income_guide_list_object;
        MyListAdapterIncome_guide myListAdapter = new MyListAdapterIncome_guide(getActivity(),
        R.layout.listfragment_income, in);

        setListAdapter(myListAdapter);
    }
    // TODO: Customize parameter initialization





    public class MyListAdapterIncome_guide extends ArrayAdapter<Income_guide> {

        private Context mContext;

        public MyListAdapterIncome_guide(Context context, int textViewResourceId,
                             ArrayList<Income_guide> objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.listfragment_income_guide, parent, false);
            TextView textView5 = (TextView) row.findViewById(R.id.textView5);

            textView5.setText(in.get(position).getIncome_type());
            //textView5.setText("руб.");


            ImageView imageViewIncome_guide_delete = (ImageView) row.findViewById(R.id.imageViewIncome_guide_delete);
            int User_Id_FK=in.get(position).getUser_Id_FK();
            if(User_Id_FK==0){
                imageViewIncome_guide_delete.setVisibility(View.INVISIBLE);
            }
            imageViewIncome_guide_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int id=in.get(position).getId();
                    CustomDialogFragmentIncome_guide dialog = new CustomDialogFragmentIncome_guide();
                    Bundle args = new Bundle();
                    args.putString("id", String.valueOf(id));
                    dialog.setArguments(args);
                    dialog.show(getActivity().getSupportFragmentManager(), "custom");
                    /*int id=in.get(position).getId();

                    Income_guide income_guide=new Income_guide();
                    income_guide.deleteIncome_guide(id);


                    ((BottomActivity)getActivity()).openIncomeTabFragment();*/
                }
            });

            return row;
        }
    }

}