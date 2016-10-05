package br.edu.ifpb.cg.info.asynctaskapplication.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ifpb.cg.info.asynctaskapplication.R;

public class MainActivity extends Activity{

    Button cadastrarUsuario = (Button) findViewById(R.id.castrarUsuario);
    Button verLista = (Button) findViewById(R.id.verLista);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irCadastrarUsuario (View view){
        Intent intent = new Intent (this, CadastrarActivity.class);
        String chave = "chave";
        String mensagem = "";
        intent.putExtra(chave, mensagem);
        startActivity(intent);
    }

    public void irVerLista (View view){
        Intent intent = new Intent (this, ListaActivity.class);
        String chave = "chave";
        String mensagem = "";
        intent.putExtra(chave, mensagem);
        startActivity(intent);
    }

}
