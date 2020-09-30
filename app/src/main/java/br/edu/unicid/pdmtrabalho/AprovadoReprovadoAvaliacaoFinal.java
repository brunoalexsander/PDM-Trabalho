package br.edu.unicid.pdmtrabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AprovadoReprovadoAvaliacaoFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovado_reprovado_avaliacao_final);

        TextView txtAprovadoReprovado = (TextView) findViewById(R.id.txtAprovadoReprovado);
        TextView txtNomeDisciplina = (TextView) findViewById(R.id.txtNomeDisciplina);
        TextView txtNotaFinalNumero = (TextView) findViewById(R.id.txtNotaFinalNumero);
        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);

        // Recuperando os parâmetros passados
        Intent disciplina = getIntent();
        Bundle parametros = disciplina.getExtras();
        final String disciplinaNome = parametros.getString("disciplina");
        final double notaFinal = parametros.getDouble("notaFinal");

        // Setar valores
        txtNomeDisciplina.setText(disciplinaNome);
        txtNotaFinalNumero.setText(String.valueOf(notaFinal));

        if (notaFinal >= 6) {
            txtAprovadoReprovado.setTextColor(Color.GREEN);
            txtAprovadoReprovado.setText("Parábens, Você foi aprovado!");
        } else {
            txtAprovadoReprovado.setTextColor(Color.RED);
            txtAprovadoReprovado.setText("Infelizmente você está reprovado!");
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                novaTela();
            }
        });
    }

    public void novaTela() {
        Intent novaActivity = new Intent(this, Principal.class);
        startActivity(novaActivity);
    }
}