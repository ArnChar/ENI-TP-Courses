package fr.eni.courses.bo;

import android.content.Context;
import android.content.SharedPreferences;

import fr.eni.module2tp.R;

import static android.content.Context.MODE_PRIVATE;

public class Configuration {

    private static Configuration instance = null;

    public static Configuration getInstance() {
        if(instance==null) {
            instance = new Configuration();
        }
        return instance;
    }

    public static final String CLE_TRI_PAR_PRIX = "cle_tri_par_prix";
    public static final String CLE_PRIX_PAR_DEFAUT = "cle_prix_par_defaut";

    private boolean tri_par_prix = false;
    private double prix_par_defaut = 0.0;

    public void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.app_name),
                MODE_PRIVATE);
        if( sp!=null ) {
            tri_par_prix = sp.getBoolean(CLE_TRI_PAR_PRIX, false);
            prix_par_defaut = sp.getFloat(CLE_PRIX_PAR_DEFAUT,0);
        }
    }

    public void save(Context context) {
        SharedPreferences sp = context.getSharedPreferences(
                context.getString(R.string.app_name),
                MODE_PRIVATE);
        if( sp!=null ) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(CLE_TRI_PAR_PRIX, tri_par_prix);
            editor.putFloat(CLE_PRIX_PAR_DEFAUT, Double.valueOf(prix_par_defaut).floatValue());
            editor.commit();
        }
    }


    public boolean isTri_par_prix() {
        return tri_par_prix;
    }

    public void setTri_par_prix(boolean tri_par_prix) {
        this.tri_par_prix = tri_par_prix;
    }

    public double getPrix_par_defaut() {
        return prix_par_defaut;
    }

    public void setPrix_par_defaut(double prix_par_defaut) {
        this.prix_par_defaut = prix_par_defaut;
    }
}
