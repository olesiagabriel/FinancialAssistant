package com.example.myfinancialassistant.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.User;
import com.example.myfinancialassistant.ViewModel.MainActivity;
import com.example.myfinancialassistant.databinding.FragmentAuthorizationBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class AuthorizationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AuthorizationFragment() {

    }
    // TODO: Rename and change types and number of parameters
    public static AuthorizationFragment newInstance(String param1, String param2) {
        AuthorizationFragment fragment = new AuthorizationFragment();

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


        FragmentAuthorizationBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_authorization, container, false);


        View view = binding.getRoot();
        TextView textv = (TextView) view.findViewById(R.id.textView4);

        textv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().setVisibility(View.GONE);
                ((MainActivity)getActivity()).openRegistrationFragment();
            }
        });

        Button buttonFragment2 = (Button) view.findViewById(R.id.onLogIn);
       User user = new User("alla","gabriel","gabriel.olesya@mail.ru","123456",100000,0);
        buttonFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u=binding.getUser();
                u.UserAuthorization(u.login,u.password);
                binding.setUser(user);
               User u2=binding.getUser();
                String s1="0";
                if (!s1.equals(user.status.trim())){
                    ((MyApplication)((MainActivity)getActivity()).getApplication()).setSomeVariable(user.id);
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
                    ((MyApplication)((MainActivity)getActivity()).getApplication()).setStart_date(start);
                    ((MyApplication)((MainActivity)getActivity()).getApplication()).setStop_date(stop);
                    ((MyApplication)((MainActivity)getActivity()).getApplication()).setdiagramposition(0);
                    getView().setVisibility(View.GONE);
                    ((MainActivity)getActivity()).openCabinetFragment();
                }
                else{
                    Toast toast = Toast.makeText(getActivity(),
                            "Неверный логин/пароль!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        //here data must be an instance of the class MarsDataProvider
        binding.setUser(user);
        return binding.getRoot();
    }
}