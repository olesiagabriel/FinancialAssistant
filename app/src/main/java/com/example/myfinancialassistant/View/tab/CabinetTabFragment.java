package com.example.myfinancialassistant.View.tab;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.myfinancialassistant.ViewModel.MyApplication;
import com.example.myfinancialassistant.R;
import com.example.myfinancialassistant.User;
import com.example.myfinancialassistant.ViewModel.BottomActivity;
import com.example.myfinancialassistant.ViewModel.MainActivity;
import com.example.myfinancialassistant.databinding.FragmentCabinetTabBinding;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CabinetTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CabinetTabFragment<imageView> extends Fragment {
    private ImageView imageView;
    private final int Pick_image = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button mChooseBtn;
    ImageView mImageView;

    private static final int PERMISSION_CODE = 1001;
    private static final int IMAGE_PICK_CODE = 1000;
    public CabinetTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CabinetTabFragment newInstance(String param1, String param2) {
        CabinetTabFragment fragment = new CabinetTabFragment();
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
        FragmentCabinetTabBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_cabinet_tab, container, false);
        View view = binding.getRoot();
        getActivity().setTitle("Редактировать профиль");
        imageView = (ImageView)view.findViewById(R.id.imageView_1);
        imageView.setImageResource(R.drawable.ic_profile_user);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
               Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                //Тип получаемых объектов - image:
                photoPickerIntent.setType("image/*");
                //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
               getActivityStarterFragment().startActivityForResult(photoPickerIntent, Pick_image);
                startActivityForResult(photoPickerIntent, Pick_image);
            }
        });

        int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();

        User user = new User("","","","",0,k);
        user.GetUserData(k);
        binding.setUser(user);


        Button buttonOk = (Button) view.findViewById(R.id.button_2);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().setVisibility(View.GONE);
                ((BottomActivity)getActivity()).openPasswordFragment();
        }
    });
        TextView editTextTextPersonName_5 = (TextView) view.findViewById(R.id.editTextTextPersonName_5);

        TextView editTextTextPersonName_6 = (TextView) view.findViewById(R.id.editTextTextPersonName_6);

        TextView limit = (TextView) view.findViewById(R.id.editTextNumber);

        Button buttonSave = (Button) view.findViewById(R.id.button2);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int k= ((MyApplication)((BottomActivity)getActivity()).getApplication()).getSomeVariable();
                user.UpdateUserData(user.name, user.surname,Integer.valueOf(limit.getText().toString()), k);
                Toast toast = Toast.makeText(getActivity(),
                        "Изменения сохранены!", Toast.LENGTH_LONG);
                toast.show();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Button button_exit = (Button) view.findViewById(R.id.button_exit);
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication)((BottomActivity)getActivity()).getApplication()).setSomeVariable(0);
                getView().setVisibility(View.GONE);
                ((MainActivity)getActivity()).openAuthorizationFragment();
            }
        });


        return binding.getRoot();
    }
    private Fragment getActivityStarterFragment() {
        if (getParentFragment() != null) {
            return getParentFragment();
        }
        return this;
    }
}