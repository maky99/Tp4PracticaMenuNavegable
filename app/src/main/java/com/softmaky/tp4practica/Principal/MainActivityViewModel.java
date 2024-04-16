package com.softmaky.tp4practica.Principal;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softmaky.tp4practica.MenuActivity;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {
    private ArrayList<Ingreso> login;
    private MutableLiveData<String> error;
    public class Ingreso {
        public String usuario;
        public int contrasena;

        public Ingreso(String usuario, int contrasena) {
            this.usuario = usuario;
            this.contrasena = contrasena;
        }
    }
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        login = new ArrayList<>();
        login.add(new Ingreso("pepe", 123));
        login.add(new Ingreso("pepa", 111));
    }
    public LiveData<String> getMutableError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void entra(String usu, int cont) {
            for (Ingreso ingreso : login) {
                if (ingreso.usuario.equals(usu)) {
                    Log.d("salida3",ingreso.usuario);
                    if (ingreso.contrasena == cont) {
                        Log.d("salida3",ingreso.contrasena+"");
                        Intent intent = new Intent(getApplication(), MenuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(intent);
                    } else {
                        error.setValue("Contraseña Incorrecta");
                    }
                } else {
                    error.setValue("Usuario popo incorrecto");
                }

            }
    }
}
