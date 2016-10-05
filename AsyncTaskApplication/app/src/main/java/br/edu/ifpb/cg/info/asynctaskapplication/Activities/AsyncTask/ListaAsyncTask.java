package br.edu.ifpb.cg.info.asynctaskapplication.Activities.AsyncTask;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import br.edu.ifpb.cg.info.asynctaskapplication.Activities.Entities.Pessoa;

/**
 * Created by Diego A. Gama on 05/10/2016.
 */
public class ListaAsyncTask extends AsyncTask<String, Void, ArrayList<Pessoa>> {
    @Override
    protected ArrayList<Pessoa> doInBackground(String... strings) {
        String urlString = strings[0];
        ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();

        try {
            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            connection.connect();

            InputStream instream = connection.getInputStream();
            JsonReader reader = new JsonReader(new InputStreamReader(instream, "UTF-8"));

            listaPessoa = getPessoas(reader);
            instream.close();

            return listaPessoa;
        }
        catch (MalformedURLException exception) {
            exception.printStackTrace();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
        } catch (ProtocolException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Pessoa getPessoa(JsonReader reader) throws IOException {
        String nome = null;
        String email = null;
        String endereco = null;
        String cpf = null;

        reader.beginObject();

        while (reader.hasNext()){
            String name = reader.nextName();
            if (name.equals("nome")){
                nome = reader.nextString();
            } else if (name.equals("email")){
                email = reader.nextString();
            } else if (name.equals("endereco")){
                endereco = reader.nextString();
            } else if (name.equals("cpf")){
                cpf = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Pessoa(nome, email, endereco, cpf);
    }

    public ArrayList<Pessoa> getPessoas(JsonReader reader) throws IOException {
        ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();
        reader.beginArray();
        while (reader.hasNext()){
            listaPessoa.add(getPessoa(reader));
        }
        reader.endArray();
        return listaPessoa;
    }

    @Override
    protected void onPostExecute (ArrayList<Pessoa> result){
        super.onPostExecute(result);
    }

}
