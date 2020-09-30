package br.edu.unicid.pdmtrabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculoAvaliacaoFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_avaliacao_final);

        TextView txtNomeDisciplina = (TextView) findViewById(R.id.txtNomeDisciplina);
        TextView txtNotaMaiorNumero = (TextView) findViewById(R.id.txtNotaMaiorNumero);
        Button btnCalcularAF = (Button) findViewById(R.id.btnCalcularAF);
        final EditText edtNotaAF = (EditText) findViewById(R.id.edtNotaAF);

        // Recuperando os parâmetros passados
        Intent disciplina = getIntent();
        Bundle parametros = disciplina.getExtras();
        final String disciplinaNome = parametros.getString("disciplina");
        final double notaMaior = parametros.getDouble("notaMaior");

        // Setar valores
        txtNomeDisciplina.setText(disciplinaNome);
        txtNotaMaiorNumero.setText(String.valueOf(notaMaior));

        // Botão Calcular AF
        btnCalcularAF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtNotaAF.getText().toString().isEmpty()) {
                    try {
                        double notaAF = Double.parseDouble(edtNotaAF.getText().toString());
                        if (notaAF >= 0 && notaAF <= 5) {
                            double notaFinal = notaMaior + notaAF;
                            novaTela(disciplinaNome, notaFinal);
                        } else {
                            Toast.makeText(getApplicationContext(), "Informe uma nota de 0 a 5!", Toast.LENGTH_LONG).show();
                        }
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(getApplicationContext(), "Informe notas válidas!", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Algo deu errado!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos para continuar!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void novaTela(String disciplina, double notaFinal) {
        Intent novaActivity = new Intent(this, AprovadoReprovadoAvaliacaoFinal.class);

        // Passando parâmetros
        Bundle bundle = new Bundle();
        bundle.putString("disciplina", disciplina);
        bundle.putDouble("notaFinal", notaFinal);
        novaActivity.putExtras(bundle);

        startActivity(novaActivity);
    }
}