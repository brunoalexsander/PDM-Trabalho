package br.edu.unicid.pdmtrabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculos extends AppCompatActivity {

    // Create elements
    TextView txtNotaA1;
    EditText notaA1;
    TextView txtNotaA2;
    EditText notaA2;
    Button calcular;
    Button voltar;
    TextView resultado;
    TextView txtNomeDisciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculos);

        // Initialize elements
        txtNomeDisciplina = findViewById(R.id.txtNomeDisciplina);
        txtNotaA1 = findViewById(R.id.txtNotaA1);
        notaA1 = findViewById(R.id.edtNotaA1);
        txtNotaA2 = findViewById(R.id.txtNotaA2);
        notaA2 = findViewById(R.id.edtNotaA2);
        calcular = findViewById(R.id.btnCalcular);
        voltar = findViewById(R.id.btnVoltar);
        resultado = findViewById(R.id.txtResultado);

        // Recovery data
        Intent intent = getIntent();
        Bundle parameters = intent.getExtras();
        final String nomeDisciplina = parameters.getString("disciplina");

        // Set name of discipline
        txtNomeDisciplina.setText(nomeDisciplina);

        // Button calculate
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Double a1 = Double.parseDouble(notaA1.getText().toString());
                    Double a2 = Double.parseDouble(notaA2.getText().toString());
                    if ((a1 >= 0) && (a1 <= 5) && (a2 >= 0) && (a2 <= 5)) {
                        if (calcular.getText().equals("Calcular")) {
                            calcular(a1, a2);
                        } else {
                            calcularAF(a1, a2);
                        }
                    } else {
                        Toast.makeText(Calculos.this, "Informe uma nota de 0 a 5!", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException nfe) {
                    Toast.makeText(Calculos.this, "Informe uma nota válida!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(Calculos.this, "Algo deu errado!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Button backpage
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Method calculate
    public void calcular(Double a1, Double a2) {
        Double total = a1 + a2;
        if (total >= 6) {
            resultado.setTextColor(Color.GREEN);
            resultado.setText("O Aluno foi aprovado com a nota " + total + "!");
            calcular.setVisibility(View.GONE);
        } else {
            resultado.setTextColor(Color.RED);
            resultado.setText("O Aluno precisa fazer AF, devido a nota " + total + "!");
            if (a1 > a2) {
                txtNotaA2.setText("Nota AF:");
                notaA2.setText("");
                notaA1.setEnabled(false);
            } else {
                txtNotaA1.setText("Nota AF:");
                notaA1.setText("");
                notaA2.setEnabled(false);
            }
            calcular.setText("Calcular AF");
        }
    }

    // Method calculate AF
    public void calcularAF(Double a1, Double a2) {
        Double total = a1 + a2;
        if (total >= 6) {
            resultado.setTextColor(Color.GREEN);
            resultado.setText("O Aluno foi aprovado com a nota " + total + "!");
        } else {
            resultado.setTextColor(Color.RED);
            resultado.setText("O Aluno foi reprovado, devido a nota " + total + "!");
        }
        calcular.setVisibility(View.GONE);
        notaA1.setEnabled(false);
        notaA2.setEnabled(false);
    }
}