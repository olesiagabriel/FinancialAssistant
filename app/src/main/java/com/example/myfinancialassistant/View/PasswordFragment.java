package com.example.myfinancialassistant.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.User;
import com.example.myfinancialassistant.ViewModel.BottomActivity;
import com.example.myfinancialassistant.databinding.FragmentPasswordBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PasswordFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PasswordFragment newInstance(String param1, String param2) {
        PasswordFragment fragment = new PasswordFragment();
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
        FragmentPasswordBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_password, container, false);
        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();

        User user;
        user = new User("","","","",0,k);
        user.GetUserData(k);
        binding.setUser(user);

        View view = binding.getRoot();
        Button buttonCancel = (Button) view.findViewById(R.id.button_3);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openCabinetTabFragment();
            }
        });

        Button buttonSave = (Button) view.findViewById(R.id.button_33);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv20 = (TextView) view.findViewById(R.id.textView_20);
                tv20.setVisibility(View.INVISIBLE);

                TextView tv16 = (TextView) view.findViewById(R.id.textView_16);
                tv16.setVisibility(View.INVISIBLE);

                TextView editTextTextPersonName7 = (TextView) view.findViewById(R.id.editTextTextPersonName7);
                String oldpassword = editTextTextPersonName7.getText().toString();
                TextView editTextTextPersonName8 = (TextView) view.findViewById(R.id.editTextTextPersonName8);
                String newpassword1 = editTextTextPersonName8.getText().toString();
                TextView editTextTextPersonName9 = (TextView) view.findViewById(R.id.editTextTextPersonName9);
                String newpassword2 = editTextTextPersonName9.getText().toString();

                if(!newpassword1.equals(newpassword2)){
                  tv16.setVisibility(View.VISIBLE);
                }
                else{
                    User u=binding.getUser();
                    int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
                    u.NewPassword(oldpassword, newpassword1, k);
                    binding.setUser(u);
                    if (u.status.equals("0")){
                        tv20.setVisibility(View.VISIBLE);
                    }
                    else{

                        Toast toast = Toast.makeText(getActivity(),
                                "Пароль изменен!", Toast.LENGTH_LONG);
                        toast.show();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                         getView().setVisibility(View.GONE);
                        ((BottomActivity)getActivity()).openCabinetTabFragment();
                    }
                }
            }
        });

        return binding.getRoot();

    }
}