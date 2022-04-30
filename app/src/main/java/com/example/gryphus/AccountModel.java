package com.example.gryphus;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AccountModel {


    public String username;
    public HashMap<String, String> accountMap= new HashMap<>();
    public HashMap<String, ArrayList> favMap = new HashMap<>();

    public boolean valid = false;//check whether or not username, or password is valid.

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void addAccount(String username, String password) {
        accountMap.put(username, password);
    }

    public boolean userExistence(String username){
        return accountMap.containsKey(username);
    }

    public boolean valid(String username, String password) {

        // Checks for username existence by using `userExistence` method.
        if(userExistence(username)) {
            // Checks if password matches username input
            return password.equals(accountMap.get(username));
        } else {

        }
        return false;
    }

    public void loadAccounts(Map<String, ?> preferencesMap){
        for (Map.Entry<String, ?> entries: preferencesMap.entrySet()){
            accountMap.put(entries.getKey(), entries.getValue().toString());
        }
    }

//    public void loadFavs(Map<String,?> favMap) {
//        for (Map.Entry<String, ?> entries: favMap.entrySet()){
//            this.favMap.put(entries.getKey(), (ArrayList) entries.getValue());
//        }
//    }

//    public void addFav(String username, ArrayList<Product> product){
//        if (!(favMap.containsKey(username))) {
//           favMap.put(username, product);
//        } else if (favMap.containsKey(username)) {
//            favMap.get(username).add(product);
//        }
//    }

    public HashMap<String, ArrayList> getFavMap() {
        return favMap;
    }

    public HashMap<String, String> getAccountMap() {
        return accountMap;
    }
}
