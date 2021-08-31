package com.example.myfinancialassistant.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfinancialassistant.Model.Income_guide;
import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddIncome_guideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddIncome_guideFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddIncome_guideFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddIncome_guideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddIncome_guideFragment newInstance(String param1, String param2) {
        AddIncome_guideFragment fragment = new AddIncome_guideFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_income_guide, container, false);

        Button add_income_guide_button = (Button) view.findViewById(R.id.add_income_guide_button);
        add_income_guide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView17 = (EditText) view.findViewById(R.id.textView17);
                String stringtextView17=textView17.getText().toString();

                Income_guide income_guide = new Income_guide();
                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
                income_guide.insertIncome_guide(stringtextView17, k);

                Toast toast = Toast.makeText(getActivity(),
                        "Категория дохода добавлена!", Toast.LENGTH_LONG);
                toast.show();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openIncomeTabFragment();
            }
        });

        Button cancel_income_guide_button = (Button) view.findViewById(R.id.cancel_income_guide_button);
        cancel_income_guide_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openIncomeTabFragment();


            }
        });
        return view;
    }
}