package com.example.ejerprueba;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RadioButton bCiclismo, bNatacion, bJuegos, bNada;
    private RadioGroup rgEleccion;
    private Button bEnviar;
    private String optionIda;
    private TextView resultado;
    private Spinner spinner;
    private ArrayList<Usuario> users;
    private Usuario usu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {});

        rgEleccion = findViewById(R.id.radioGroup);
        bEnviar = findViewById(R.id.enviarBoton);
        bCiclismo = findViewById(R.id.radioButtonCiclismo);
        bNatacion = findViewById(R.id.radioButtonNatacion);
        bJuegos = findViewById(R.id.radioButtonGames);
        bNada = findViewById(R.id.radioButtonNa);
        resultado = findViewById(R.id.ResultadotextView);
        spinner = findViewById(R.id.spinner);


        if (savedInstanceState != null)
            users = (ArrayList<Usuario>) savedInstanceState.getSerializable("user");
        else {
            users = new ArrayList<>();
            Bundle extras = getIntent().getExtras();
            if (extras != null){
//            String name = extras.getString("name");
//            String edad = extras.getString("age");
                usu = (Usuario)extras.getSerializable("user");
                resultado.setText(usu.toString());
                users.add(usu);
            }
        }
        ArrayAdapter<Usuario> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,users);
        spinner.setAdapter(myAdapter);
        //para ver si hay algun item seleccionado en el spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rgEleccion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == bCiclismo.getId()){
                    optionIda = bCiclismo.getText().toString();
                } else if (i == bNatacion.getId()){
                    optionIda = bNatacion.getText().toString();
                } else if (i == bJuegos.getId()) {
                    optionIda = bJuegos.getText().toString();
                } else if (i == bNada.getId()) {
                    optionIda=bNada.getText().toString();
                }
            }
        });

        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SecondActivity.class);
                i.putExtra("option",optionIda);
                someActivityResultLauncher.launch(i);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("user", users);
    }
}