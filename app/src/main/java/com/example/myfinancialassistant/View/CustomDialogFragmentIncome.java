package com.example.myfinancialassistant.View;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.myfinancialassistant.Model.Income;
import com.example.myfinancialassistant.ViewModel.BottomActivity;

public class CustomDialogFragmentIncome extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

       String id_string = getArguments().getString("id");
       int id=Integer.parseInt(id_string);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Удаление дохода")
               // .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Уверены, что хотите удалить эту запись?" )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Income income=new  Income();
                        income.deleteIncome(id);

                        ((BottomActivity)getActivity()).openIncomeTabFragment();

                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}