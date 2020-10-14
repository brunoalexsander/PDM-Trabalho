package br.edu.unicid.pdmtrabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Pegar componentes
        final EditText edtRA = (EditText) findViewById(R.id.edtRA);
        final EditText edtNome = (EditText) findViewById(R.id.edtNome);
        final EditText edtTurma = (EditText) findViewById(R.id.edtTurma);

        final Spinner spnCurso = (Spinner) findViewById(R.id.spnCurso);
        Button btnFechar = (Button) findViewById(R.id.btnFechar);
        Button btnAvancar = (Button) findViewById(R.id.btnAvancar);

        // Carregar spinner
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.cursos, R.layout.style_spinner_layout);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCurso.setAdapter(adaptador);

        // Botão desistir/fechar
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        // Botão avançar
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtRA.getText().toString().isEmpty() && !edtNome.getText().toString().isEmpty() && !spnCurso.isSelected() && !edtTurma.getText().toString().isEmpty()) {
                    novaTela();
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos para continuar!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void novaTela() {
        Intent novaActivity = new Intent(this, Disciplina.class);
        startActivity(novaActivity);
    }
}