package fr.eni.courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import fr.eni.courses.bo.Configuration;
import fr.eni.module2tp.R;

public class ConfigurationActivity extends AppCompatActivity {

    private SwitchCompat swTriParPrix;
    private EditText etPrixParDefaut;

    private static Configuration config = Configuration.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        swTriParPrix = findViewById(R.id.sw_tri_par_prix);
        etPrixParDefaut = findViewById(R.id.et_prix_par_defaut);

        swTriParPrix.setChecked(config.isTri_par_prix());
        etPrixParDefaut.setText(String.valueOf(config.getPrix_par_defaut()));
    }

    @Override
    protected void onPause() {
        Log.d(ListeArticlesActivity.TAG,"ConfigurationActivity.onPause");
        super.onPause();

        config.setTri_par_prix(swTriParPrix.isChecked());
        config.setPrix_par_defaut(Double.valueOf(etPrixParDefaut.getText().toString()));
        config.save(this);
    }
}