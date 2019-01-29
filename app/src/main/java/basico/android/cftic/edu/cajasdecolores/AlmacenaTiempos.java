package basico.android.cftic.edu.cajasdecolores;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public final static String ULTIMO_ID = "Indice";


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

    /**
     * Pasamos del fichero de puntuaciones a una Lista de puntuaciones
     * @param context
     * @return
     */
    public static List<Puntuacion> cargarPuntuaciones (Context context)
    {
        List<Puntuacion> lp = null;
        Gson gson = null;
        Map<String, String> mapa_records = null;
        String str_json = null;
        Puntuacion puntacion_aux = null;


        SharedPreferences sp = context.getSharedPreferences(FICHERO, Context.MODE_PRIVATE);
        mapa_records = (Map<String, String>)sp.getAll();//cargo todo el contenido en un pama
        Set<String> cjto_claves = mapa_records.keySet();//obtengo las claves para recorrer el mapa
        gson = new Gson();//
        lp = new ArrayList<Puntuacion>();

        for (String clave:cjto_claves)
        {
            if(clave != "Indice") {
                str_json = mapa_records.get(clave);//obtengo el registro puntuacion en formato JSON
                puntacion_aux = gson.fromJson(str_json, Puntuacion.class);////paso de JSON a Puntuaciones
                lp.add(puntacion_aux);//agrego a lista
            }
        }


        return lp;
    }

}

