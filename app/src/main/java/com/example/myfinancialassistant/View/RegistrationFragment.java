package com.example.myfinancialassistant.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.User;
import com.example.myfinancialassistant.ViewModel.MainActivity;
import com.example.myfinancialassistant.databinding.FragmentRegistrationBinding;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistrationFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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

        FragmentRegistrationBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_registration, container, false);
        View view = binding.getRoot();
        LinearLayout lineral1=(LinearLayout) view.findViewById(R.id.lineral1);
        lineral1.setVisibility(View.VISIBLE);
        getActivity().setTitle("Регистрация");
        Button RegistrationButton = (Button) view.findViewById(R.id.RegistrationButton);
        Button CancelButton = (Button) view.findViewById(R.id.CancelButton);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getView().setVisibility(View.GONE);
                ((MainActivity)getActivity()).openAuthorizationFragment();
            }
        });


        RegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv6 = (TextView) view.findViewById(R.id.textView6);
                tv6.setVisibility(View.INVISIBLE);
                tv6.setText("Логин не может быть пустым.");
                TextView tv9 = (TextView) view.findViewById(R.id.textView9);
                tv9.setVisibility(View.INVISIBLE);
                TextView tv8 = (TextView) view.findViewById(R.id.textView8);
                tv8.setVisibility(View.INVISIBLE);
                TextView tv15 = (TextView) view.findViewById(R.id.textView15);
                tv15.setVisibility(View.INVISIBLE);
                TextView tv = (TextView) view.findViewById(R.id.textView);
                tv.setVisibility(View.INVISIBLE);


                TextView password2 = (TextView) view.findViewById(R.id.pass2_reg);
                String textpassword2 = password2.getText().toString();
                TextView name = (TextView) view.findViewById(R.id.name_reg);
                String textname = name.getText().toString();
                TextView surname = (TextView) view.findViewById(R.id.surname_reg);
                String textsurname = surname.getText().toString();
                TextView login = (TextView) view.findViewById(R.id.login_reg);
                String textlogin = login.getText().toString();
                TextView password1 = (TextView) view.findViewById(R.id.pass1_reg);
                String textpassword1 = password1.getText().toString();

                if (textlogin.equals("")){
                    tv6.setVisibility(View.VISIBLE);
                }
                else{
                    String email = login.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (!email.matches(emailPattern))
                    {
                        tv6.setText("Не верный формат Email.");
                        tv6.setVisibility(View.VISIBLE);
                    }
                }

                 if(textname.equals("")){
                    tv9.setVisibility(View.VISIBLE);
                }
                if(textsurname.equals("")){
                    tv8.setVisibility(View.VISIBLE);
                }
                 if(!textpassword1.equals(textpassword2)){
                    tv.setVisibility(View.VISIBLE);
                }
               if(textpassword1.equals("")){
                    tv15.setVisibility(View.VISIBLE);
                }
                 if(tv6.getVisibility()==View.INVISIBLE && tv9.getVisibility()==View.INVISIBLE&&tv8.getVisibility()==View.INVISIBLE&&tv15.getVisibility()==View.INVISIBLE&&tv.getVisibility()==View.INVISIBLE ){
                    User u=binding.getUserReg();
                    TextView limit_reg=(TextView) view.findViewById(R.id.limit_reg);
                    int limit=Integer.valueOf(limit_reg.getText().toString());
                    u.UserRegistration(u.name, u.surname, u.login, u.password, limit);


                    binding.setUserReg(u);
                    User u2 = binding.getUserReg();
                    String s1 = "1";
                    if (s1.equals(u2.status)) {
                        Toast toast = Toast.makeText(getActivity(),
                                "Вы успешно зарегистрированы!", Toast.LENGTH_LONG);
                        toast.show();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        lineral1.setVisibility(View.INVISIBLE);

                        getView().setVisibility(View.GONE);
                        ((MainActivity) getActivity()).openAuthorizationFragment();
                    } else {
                        tv6.setText("Пользователь с таким логином уже существует!");
                        tv6.setVisibility(View.VISIBLE);

                    }

                }

            }
        });

        User user = new User("","","","",1000,0);
        binding.setUserReg(user);
        return binding.getRoot();

    }
}