package br.edu.unicid.pdmtrabalho;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Disciplina extends ListActivity {

    public static ArrayList<String> disciplinas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina);

        final EditText edtDisciplina = (EditText) findViewById(R.id.edtDisciplina);
        final Button btnGravar = (Button) findViewById(R.id.btnGravar);
        final Button btnVoltar = (Button) findViewById(R.id.btnVoltar);

        limpaDisciplina();
        atualizaLista();

        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String disciplina = edtDisciplina.getText().toString();
                if ((disciplina != null) && (!disciplina.equals(""))) {
                    disciplinas.add(disciplina);
                    limpaDisciplina();
                    atualizaLista();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void atualizaLista() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, disciplinas);
        setListAdapter(arrayAdapter);
    }

    public void limpaDisciplina() {
        EditText edtDisciplina = (EditText)findViewById(R.id.edtDisciplina);
        edtDisciplina.setText("");
        edtDisciplina.requestFocus();
    }

    public void btnInsertOnClick(View v) {
        EditText edtDisciplina = (EditText) findViewById(R.id.edtDisciplina);
        String tarefa = edtDisciplina.getText().toString();
        if ((tarefa != null) && (!tarefa.equals(""))) {
            disciplinas.add(edtDisciplina.getText().toString());
            limpaDisciplina();
            atualizaLista();
        }
    }

    @Override
    protected void onListItemClick(ListView lista, View v, int position, long id) {
        super.onListItemClick(lista, v, position, id);
        Object obj = this.getListAdapter().getItem(position);
        String disciplina = obj.toString();
        novaTela(disciplina);
    }

    public void novaTela(String disciplina) {
        Intent novaActivity = new Intent(this, Calculo.class);

        // Passando parâmetros
        Bundle bundle = new Bundle();
        bundle.putString("disciplina", disciplina);
        novaActivity.putExtras(bundle);

        startActivity(novaActivity);
    }
}