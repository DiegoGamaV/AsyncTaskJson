package br.edu.ifpb.cg.info.asynctaskapplication.Activities.AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import br.edu.ifpb.cg.info.asynctaskapplication.Activities.Entities.Pessoa;

/**
 * Created by Diego A. Gama on 05/10/2016.
 */
public class CadastrarAsyncTask extends AsyncTask<Pessoa, Void, String> {

    @Override
    protected String doInBackground(Pessoa... pessoas) {

        String mensagem = "";

        try {
            Gson gson = new Gson();
            String pessoaJson = gson.toJson(pessoas[0]);

            URL url = new URL("http://ladoss.info:8773/Convite_SERVICE/convidado/cadastrar");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());

            dataOutputStream.writeBytes(pessoaJson);
            dataOutputStream.close();

            if(urlConnection.getResponseCode() == 200){
                mensagem = "Cadastro realizado com sucesso.";
            }else{
                mensagem = "Erro no Cadastro... O bolo é uma mentira ;-;";
            }

            urlConnection.disconnect();
        }
        catch (IOException exception){
            Log.e("Exceção encontrada: ", exception.getMessage());
        }
        finally {
            return mensagem;
        }
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
