package com.example.gryphus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import android.widget.EditText;
import android.widget.Toast;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class LoginFragment extends Fragment {

    public AccountModel account;
    boolean isValid = false;

    public ArrayList<Product> favsList;


    SharedPreferences sharedPreferences;
    SharedPreferences favPref;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = Objects.requireNonNull(getContext())
                .getSharedPreferences("accountDB", Context.MODE_PRIVATE);

        sharedPreferencesEditor = sharedPreferences.edit();

        account = new AccountModel();



        Button accountCreation = Objects.requireNonNull(getView()).findViewById(R.id.createAccount);
        accountCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SignUpFragment()).addToBackStack(null).commit();
            }
        });

        Button logIn =  getView().findViewById(R.id.confirm);
        EditText userInput = getView().findViewById(R.id.editTextUsername);
        EditText password = getView().findViewById(R.id.editTextPassword);

        if (sharedPreferences != null){
            Map<String, ?> preferencesMap = sharedPreferences.getAll();
            if (preferencesMap.size() != 0) {
                account.loadAccounts(preferencesMap);
            }

        }

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String inputUser = userInput.getText().toString();
                String inputPass = password.getText().toString();

                if (inputUser.isEmpty() || inputPass.isEmpty()) {
                    Toast.makeText(getContext(), "Invalid entry!", Toast.LENGTH_SHORT).show();
                }
                else {
                    isValid = validationMethod(inputUser, inputPass);
                    if (!isValid) {
                        Toast.makeText(getContext(), "Invalid entry v2.0!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        account.setUsername(inputUser);


                        sharedPreferences.edit().putString("username", inputUser).apply();


                        Toast.makeText(getContext(),
                                "Login successful!", Toast.LENGTH_SHORT).show();

                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container,
                                        new HomeFragment()).addToBackStack(null).commit();


                    }
                }

            }
        });

    }

    private boolean validationMethod(String username, String password) {
        return account.valid(username, password);
    }

//    private void loadFavs() {
//        favPref = getContext().getSharedPreferences("favDB", Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = favPref.getString(account.getUsername(), null);
//        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
//        favsList = gson.fromJson(json, type);
//
//        if (favsList == null) {
//            favsList = new ArrayList<Product>();
//        }
    }



