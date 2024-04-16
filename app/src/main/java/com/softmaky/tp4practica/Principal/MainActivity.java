package com.softmaky.tp4practica.Principal;

import static android.Manifest.permission.CALL_PHONE;
import static java.lang.Integer.parseInt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softmaky.tp4practica.Conectado;
import com.softmaky.tp4practica.R;
import com.softmaky.tp4practica.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private  MainActivityViewModel mv;
    private Conectado conectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        solicitarPermisos();
        registrarBroadcast();


        binding.btEntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //String usu=binding.tvUsuario.getText().toString();
               // int cont=parseInt(binding.tvContrasena.getText().toString());

                mv.entra(binding.tvUsuario.getText().toString(),parseInt(binding.tvContrasena.getText().toString()));
            }
        });
        mv.getMutableError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMsjError.setText(s);
            }
        });
    }
    private void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{CALL_PHONE},1000);
        }
    }
    private void registrarBroadcast(){
        this.conectado=new Conectado();
        registerReceiver(conectado,new IntentFilter("android.net.wifi.supplicant.CONNECTION_CHANGE"));
    }

}
