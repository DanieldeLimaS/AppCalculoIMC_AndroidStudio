package com.example.appcnh;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    EditText edtAltura,edtPeso;
    Button btnCalcular;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarregarComponentes();
        configurarBotaoCalcular();
    }

    protected void CarregarComponentes(){
        edtAltura = findViewById(R.id.edtAltura);
        edtPeso = findViewById(R.id.edtPeso);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);
    }

    protected void configurarBotaoCalcular() {
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado = realizaCalculoRetornaMensagem();
                Log.d("resultado",resultado);
                exibiResultado(resultado);
                limpaCampos();
            }

            protected String realizaCalculoRetornaMensagem(){
                double peso = Double.parseDouble(edtPeso.getText().toString());
                double altura = Double.parseDouble(edtAltura.getText().toString());
                double resultado = peso/(altura*altura);
                String texto ="Valores inválidos, tente novamente!";
                if (resultado <16)
                    texto = "Magreza grave";
                else if(resultado >=16 && resultado<17)
                    texto = "Magreza moderada";
                else if(resultado >=17 && resultado<18.5)
                    texto = "Magreza leve";
                else if(resultado >=18.5 && resultado<25)
                    texto = "Saudável";
                else if(resultado >=25 && resultado<30)
                    texto = "Sobrepeso";
                else if(resultado >=30 && resultado<35)
                    texto = "Obesidade Grau I";
                else if(resultado >=35 && resultado<40)
                    texto = "Obesidade Grau I";
                else if(resultado >=40)
                    texto = "Obesidade Grau I";
                else texto="Valores inválidos, tente novamente!";
                return texto;
            }

            protected void exibiResultado(String resultado) {
                txtResultado.setText(resultado);
            }

            protected void limpaCampos() {
                edtPeso.setText("");
                edtAltura.requestFocus();
            }

        });}
}