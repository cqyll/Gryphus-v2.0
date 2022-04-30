package com.example.gryphus;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import kotlin.collections.SlidingWindowKt;

public class SettingsFragment extends Fragment {


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;
//    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, )
//            .setSmallIcon(R.drawable.ic_search)
//            .setContentTitle("notification")
//            .setContentText("a product is restocked. Go and check it!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT);



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings,container,false);

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Switch notification = getView().findViewById(R.id.notiSwitch);
        Switch theme = getView().findViewById(R.id.themeSwitch);
        TextView profile = getView().findViewById(R.id.profile);

        sharedPreferences = Objects.requireNonNull(getActivity()).getApplicationContext()
                .getSharedPreferences("nightMode", 0);




        sharedPreferences = Objects.requireNonNull(getActivity()).getApplicationContext()
                .getSharedPreferences("nightMode", Context.MODE_PRIVATE);


        Boolean isNightModeOn = sharedPreferences.getBoolean("nightMode", false);
        sharedPreferencesEditor = sharedPreferences.edit();


        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPreferencesEditor.putBoolean("nightMode", true);
                    sharedPreferencesEditor.apply();
                    setContentOfTextView(theme, "ON");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPreferencesEditor.putBoolean("nightMode", false);
                    sharedPreferencesEditor.apply();
                    setContentOfTextView(theme, "OFF");
                }
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
            }
        });


    }

    private String getContentOfEditText(View view){
        EditText editText = (EditText) view;
        String str = editText.getText().toString();
        return str;
    }
    private void setContentOfTextView(View view, String newContents){
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }
}
