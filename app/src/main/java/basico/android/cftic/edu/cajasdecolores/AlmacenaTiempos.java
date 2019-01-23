package basico.android.cftic.edu.cajasdecolores;

import android.content.Context;
import android.content.SharedPreferences;

public class AlmacenaTiempos {

    //Declaramos las variables
    public final static String USUARIO = "NombreUsuario";
    public final static String TIEMPO = "Tiempo";
    public final static String FICHERO = "Marcas";


    /**
     * Método que devuelve el valor del usuario
     * */
    public static String getUsuario(Context contex)
    {
        String usuarioN = null;
        SharedPreferences pref = contex.getSharedPreferences(FICHERO, contex.MODE_PRIVATE);//Abre el fichero y si no existe lo crea
        usuarioN = pref.getString(AlmacenaTiempos.USUARIO,"");//Devuelve el valor de usuario y si no existe devuelve ""
        return usuarioN;
    }

    /**
     * Método que devuelve el tiempo
     * */
    public static String getTiempo(Context cont)
    {

        String tmp = null;
        SharedPreferences pref = cont.getSharedPreferences(FICHERO, cont.MODE_PRIVATE);
        tmp = pref.getString(AlmacenaTiempos.TIEMPO,"");
        return  tmp;
    }

    /**
     * Método que modifica el valor de usuario
     * */
    public static void setUsuario(String usu ,Context c)
    {
        SharedPreferences preferences = c.getSharedPreferences(FICHERO,c.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();//objeto de tipo Editor para modificar el fichero
        editor.putString(AlmacenaTiempos.USUARIO,usu);
        editor.commit();
    }

    /**
     * Método que modifica el tiempo
     * */
    public static void setTiempo(String tiempo ,Context c)
    {
        SharedPreferences preferences = c.getSharedPreferences(FICHERO,c.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(AlmacenaTiempos.TIEMPO,tiempo);
        editor.commit();
    }

}

