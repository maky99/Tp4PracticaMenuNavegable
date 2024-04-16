package com.softmaky.tp4practica.ui.home;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;


    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getText() {
        if (mText==null){
            mText=new MutableLiveData<>();
        }
        return mText;
    }

    public void hacerLlamada(String numero){

        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+numero));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }

    }