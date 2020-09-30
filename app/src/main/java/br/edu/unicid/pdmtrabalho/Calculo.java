package br.edu.unicid.pdmtrabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo);

        final TextView txtDisciplinaNome = (TextView) findViewById(R.id.txtNomeDisciplina);
        final EditText edtNotaA1 = (EditText) findViewById(R.id.edtNotaA1);
        final EditText edtNotaA2 = (EditText) findViewById(R.id.edtNotaA2);
        final Button btnCalcular = (Button) findViewById(R.id.btnCalcular);

        // Recuperando os parâmetros passados
        Intent disciplina = getIntent();
        Bundle parametros = disciplina.getExtras();
        final String disciplinaNome = parametros.getString("disciplina");

        // Setar nome da disciplina recuperada
        txtDisciplinaNome.setText(disciplinaNome);

        // Botão calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtNotaA1.getText().toString().isEmpty() && !edtNotaA2.getText().toString().isEmpty()) {
                    try {
                        double notaA1 = Double.parseDouble(edtNotaA1.getText().toString());
                        double notaA2 = Double.parseDouble(edtNotaA2.getText().toString());
                        if (notaA1 >= 0 && notaA1 <= 5 && notaA2 >= 0 && notaA2 <= 5) {
                            double notaFinal = notaA1 + notaA2;
                            double notaMaior = 0;
                            if (notaA1 > notaA2) {
                                notaMaior = notaA1;
                            } else {
                                notaMaior = notaA2;
                            }
                            //Toast.makeText(getApplicationContext(), "Sua nota foi: " + notaFinal, Toast.LENGTH_LONG).show();
                            novaTela(disciplinaNome, notaFinal, notaMaior);
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

    public void novaTela(String disciplina, double notaFinal, double notaMaior) {
        Intent novaActivity = new Intent(this, AprovadoReprovado.class);

        // Passando parâmetros
        Bundle bundle = new Bundle();
        bundle.putString("disciplina", disciplina);
        bundle.putDouble("notaFinal", notaFinal);
        bundle.putDouble("notaMaior", notaMaior);
        novaActivity.putExtras(bundle);

        startActivity(novaActivity);
    }
}