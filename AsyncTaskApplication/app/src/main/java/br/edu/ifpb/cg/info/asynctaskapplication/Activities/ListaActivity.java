package br.edu.ifpb.cg.info.asynctaskapplication.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import br.edu.ifpb.cg.info.asynctaskapplication.Activities.AsyncTask.ListaAsyncTask;
import br.edu.ifpb.cg.info.asynctaskapplication.Activities.Entities.Pessoa;
import br.edu.ifpb.cg.info.asynctaskapplication.R;

public class ListaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ListView listViewPessoa = (ListView) findViewById(R.id.listaPessoa);
        ListaAsyncTask listaTask = new ListaAsyncTask();
        ArrayList<Pessoa> listaPessoa = new ArrayList<>();

        try {
            listaPessoa = listaTask.execute("http://ladoss.info:8773/Convite_SERVICE/convidado/listar").get();
            ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, listaPessoa);
            listViewPessoa.setAdapter(adapter);
        }
        catch (InterruptedException | ExecutionException exception){
            exception.printStackTrace();
        }
    }

    public void voltar2(View view){
        Intent intent = new Intent(this, MainActivity.class);
        String chave = "chave";
        String mensagem = "";
        intent.putExtra(chave, mensagem);
        startActivity(intent);
    }

}
