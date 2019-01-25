package basico.android.cftic.edu.cajasdecolores;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Clase deonde almacenamos datos en las prefencias de la aplicación
 * Las Preferences son un fichero XML donde asocia clave-valor
 * */
public class AlmacenaTiempos {

    //Declaramos las variables
    public final static String USUARIO = "Nombre";
    public final static String TIEMPO = "Tiempo";
    public final static String FICHERO = "marcas"; //Nombre del Fichero
    public final static String PUNTUACION = "Puntuacion";
    public static String ID = "0";
    public final static String ULTIMO_ID = "0";


    /**
     * Método que devuelve el valor del usuario, si no hay usuario por defecto devuelve ""
     * */
    public static String getUsuario(Context contex)
    {
        String usuarioN = null;
        SharedPreferences pref = contex.getSharedPreferences(FICHERO, contex.MODE_PRIVATE);//Abre el fichero y si no existe lo crea
        usuarioN = pref.getString(AlmacenaTiempos.USUARIO,"");//Devuelve el valor de usuario y si no existe devuelve ""
        return usuarioN;
    }

    /**
     * Método que devuelve el tiempo, si no hay tiempo guardado devuelve ""
     * */
    public static long getTiempo(Context cont)
    {

        long tmp = 0;
        SharedPreferences pref = cont.getSharedPreferences(FICHERO, cont.MODE_PRIVATE);
        tmp = pref.getLong(AlmacenaTiempos.TIEMPO,0);
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
    public static void setTiempo(long tiempo ,Context c)
    {
        SharedPreferences preferences = c.getSharedPreferences(FICHERO,c.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong(AlmacenaTiempos.TIEMPO,tiempo);
        editor.commit();
    }

    /**
     * Método que limpia las preferences de la aplicación
     * */
    public static void LimpiaPreferences(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(FICHERO,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();
    }


    public static void setPuntuacion(String puntuación,Context context,String id)
    {
        SharedPreferences preferences = context.getSharedPreferences(FICHERO,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();//objeto de tipo Editor para modificar el fichero
        editor.putString(id,puntuación);
        editor.commit();
    }

    public static void setUltimoId(int ultimoId,Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(FICHERO,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ULTIMO_ID,ultimoId);
        editor.commit();
    }
    public static int getUltimoId(Context cont)
    {
        int ultimo_id = 0;
        SharedPreferences pref = cont.getSharedPreferences(FICHERO, cont.MODE_PRIVATE);
        ultimo_id = pref.getInt(ULTIMO_ID,0);
        return  ultimo_id;
    }

}

