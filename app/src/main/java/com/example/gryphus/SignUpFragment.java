package com.example.gryphus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Objects;


public class SignUpFragment extends Fragment {


    public AccountModel account;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        account = new AccountModel();

        sharedPreferences = Objects.requireNonNull(getActivity()).getApplicationContext()
                .getSharedPreferences("accountDB", Context.MODE_PRIVATE);

        sharedPreferencesEditor = sharedPreferences.edit();

        if (sharedPreferences != null){
            Map<String, ?> preferencesMap = sharedPreferences.getAll();
            if (preferencesMap.size() != 0) {
                account.loadAccounts(preferencesMap);
            }
        }



        Button register = getView().findViewById(R.id.signUpConfirm);
        EditText user = getView().findViewById(R.id.inputUsername);
        EditText pass = getView().findViewById(R.id.inputPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUser = user.getText().toString();
                String newPass = pass.getText().toString();

                if(validate(newUser, newPass)){

                    if (account.userExistence(newUser)) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Username unavailable!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        account.addAccount(newUser,newPass);
                        sharedPreferencesEditor.putString(newUser,newPass.toString());
                        sharedPreferencesEditor.apply();
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Registration successful!", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,
                                new LoginFragment()).addToBackStack(null).commit();


                    }

                }
            }
        });
    }

    private boolean validate(String username, String password){
        if (username.isEmpty() || password.length() < 8) {
            Toast.makeText(getActivity().getApplicationContext(), "Invalid user name or password. Ensure password is atleast 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
