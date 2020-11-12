package com.example.privacyparanoid;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesGetSet {
    static Context emptycontext;
    static SharedPreferences wipeKeyStorage;


 public SharedPreferencesGetSet(Context context){
     emptycontext=context;
     wipeKeyStorage = emptycontext.getSharedPreferences("WipeKeyStorage",MODE_PRIVATE);

 }
    public SharedPreferencesGetSet(){
        wipeKeyStorage = emptycontext.getSharedPreferences("WipeKeyStorage",MODE_PRIVATE);

    }

public void setKey(Long l){
    SharedPreferences.Editor wipeKeyEditor = wipeKeyStorage.edit();
     wipeKeyEditor.putLong("dangerous_wipe_key",l);
 }
public long getKey(){
    long dangerous_wipe_key=wipeKeyStorage.getLong("dangerous_wipe_key",0);
    return dangerous_wipe_key;

}
}
