package masson.diiage.org.partiel2018;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.ConsumerIrManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import masson.diiage.org.partiel2018.Adapter.ReleaseAdapter;
import masson.diiage.org.partiel2018.Database.DatabaseHelper;
import masson.diiage.org.partiel2018.Entities.Release;

public class MainActivity extends AppCompatActivity {
    private ListView listViewRelease;
    private ArrayList<Release> listRelease;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewRelease = (ListView) findViewById(R.id.listViewRelease);
        listRelease = new ArrayList<Release>();
        context = this;
        String tagRelease = "Release";

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<Release> lstRe = helper.getRelease(db);
        if (lstRe.size() == 0) {
            loadApi();
        }
        else {
            displayReleases(lstRe);
        }
    }

    private void loadApi() {
        String baseUrlApi = getResources().getString(R.string.urlApi);
        URL baseUrl = null;

        try {
            baseUrl = new URL(baseUrlApi); // Création de l'URL dans les ressources
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        AsyncTask<URL, Integer, ArrayList<Release>> task = new AsyncTask<URL, Integer, ArrayList<Release>>() {
            @Override
            protected ArrayList<Release> doInBackground(URL... urls) {
                ArrayList<Release> listRelease = new ArrayList<Release>();

                try {
                    InputStream inputStream = urls[0].openStream(); // Ouverture de la connexion avec l'URL
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  // Création d'un bufferReader pour faciliter la lecture

                    StringBuilder stringBuilder = new StringBuilder(); // Permet de concaténer plus rapidement les strings.
                    String lineBuffer = null;

                    while ((lineBuffer = bufferedReader.readLine()) != null){ // Tant qu'il y a des choses à lire.
                        stringBuilder.append(lineBuffer); // Ajout des lignes.
                    }

                    String data = stringBuilder.toString(); // toutes les données.
                    final JSONArray jsonArray = new JSONArray(data);
                    for ( int i = 0; i < jsonArray.length() ; i++)
                    {
                        final JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Release release = Release.ReleaseBuilder.builder(jsonObject); // Creation du comic grâce au builder Json déclaré dans la classe

                        listRelease.add(release);

                        addRelease(release);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return listRelease;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                // Ajout d'une progressBar
//                visibleProgressBar();
            }

            @Override
            protected void onPostExecute(ArrayList<Release> lRelease) {
                super.onPostExecute(lRelease);

                try {
                    displayReleases(lRelease);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute(baseUrl);
    }

    public void displayReleases(ArrayList<Release> lstRelease) {
        ReleaseAdapter adapter = new ReleaseAdapter(lstRelease, this);
        listViewRelease.setAdapter(adapter);
    }

    public void addRelease(Release release) {
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        helper.addRelease(db, release);
    }
}
