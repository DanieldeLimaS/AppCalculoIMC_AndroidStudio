package com.example.appcnh;

import android.graphics.Region;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtAltura, edtPeso;
    Button btnCalcular;
    TextView txtResultado;

    //onCreate É ONDE TUDO SERÁ CRIADO NO MOMENTO QUE O APP SER EXECUTADO, SERIA ONDE OS COMPONENTES DEVEM SER INICIALIZADOS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarregarComponentes();
        configurarBotaoCalcular();
    }

    //CARREGA OS COMPONENTES EM UMA VARIAVEL DECALRADA NO INICIO DESSA CLASSE
    protected void CarregarComponentes() {
        edtAltura = findViewById(R.id.edtAltura);
        edtPeso = findViewById(R.id.edtPeso);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);
    }

    //MÉTODO PRINCIPAL QUE CONTEM TODA A ESTRUTURA DE EVENTO QUE O BOTÃO CALCULAR IRÁ REALIZAR
    protected void configurarBotaoCalcular() {
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado = realizaCalculoRetornaMensagem();
                Log.d("resultado", resultado);
                exibiResultado(resultado);
                limpaCampos();
            }

            //REALIZA O CALCULO E RETORNA A MENSAGEM FILTRADA PARA A TELA
            protected String realizaCalculoRetornaMensagem() {
                double peso = Double.parseDouble(edtPeso.getText().toString().replace(",", "."));
                double altura = Double.parseDouble(edtAltura.getText().toString().replace(",", "."));
                double resultado = peso / (altura * altura);
                return RetornaMensagemIMC(resultado);

            }

            /*RETORNA A MENSAGEM PARA O USUÁRIO DE ACORDO  COM O RESULTADO*/
            private String RetornaMensagemIMC(double resultado) {
                String texto = "Valores inválidos, tente novamente!";
                if (resultado < 16)
                    texto = "Magreza grave";
                else if (resultado >= 16 && resultado < 17)
                    texto = "Magreza moderada";
                else if (resultado >= 17 && resultado < 18.5)
                    texto = "Magreza leve";
                else if (resultado >= 18.5 && resultado < 25)
                    texto = "Saudável";
                else if (resultado >= 25 && resultado < 30)
                    texto = "Sobrepeso";
                else if (resultado >= 30 && resultado < 35)
                    texto = "Obesidade Grau I";
                else if (resultado >= 35 && resultado < 40)
                    texto = "Obesidade Grau I";
                else if (resultado >= 40)
                    texto = "Obesidade Grau I";
                else texto = "Valores inválidos, tente novamente!";
                return texto;
            }

            //RESPONSÁVEL POR APENAS EXIBIR A MENSAGEM/RESULTADO NA TELA
            protected void exibiResultado(String resultado) {
                txtResultado.setText(resultado);
            }

            //LIMPA OS CAMPOS DE PESO E ALTURA APOS APRESENTAR O RESULTADO NA TELA
            protected void limpaCampos() {
                edtPeso.setText("");
                edtAltura.setText("");
                edtPeso.requestFocus();
            }

        });
    }
}