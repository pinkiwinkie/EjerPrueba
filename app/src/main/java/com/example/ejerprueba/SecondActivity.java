package com.example.ejerprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private Button bAccept, bCancel;
    private EditText etName, etAge;
    @Override
    public  void onCreate(Bundle savedInstace) {
        super.onCreate(savedInstace);
        setContentView(R.layout.activity_second);
        Bundle extras = getIntent().getExtras();
        String option = extras.getString("option");

        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {});
        bAccept = findViewById(R.id.aceptarButton);
        bCancel = findViewById(R.id.cancelarButton);


        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                etName = findViewById(R.id.editTextNombre);
                etAge = findViewById(R.id.editTextEdad);
                Usuario usuario = new Usuario(etName.getText().toString(), Integer.parseInt( etAge.getText().toString()),option);
                i.putExtra("user", usuario);
                someActivityResultLauncher.launch(i);
            }
        });
    }
}
