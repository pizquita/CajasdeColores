package basico.android.cftic.edu.cajasdecolores;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    private int t;
    private int color;
    private String usuario;
    private Object tag;
    private long lng_puntuacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = getIntent().getStringExtra("USUARIO");


        //Inicializamos las variables de clase
        t = 0;
        lng_puntuacion = 0;
        color = getResources().getColor(R.color.tocado);


        //Leemos el record
        //String tmp = null;
        //tmp = AlmacenaTiempos.getTiempo(this.getApplicationContext());//vale con poner this
        //Log.d("MIAPP","Record actual: "+tmp);
    }

    /**
     * Método que oculta el botón al pulsarlo
     * */
    public void ocultarBoton (View boton)
    {
        //TODO ocultar o quitar el botón
        //boton.setVisibility(View.INVISIBLE);// oculta el botón
        boton.setVisibility(View.GONE);

        //Un temporizador creado en XML, inicializamos el cronometro a 0 y empieza a contar
        Chronometer c = (Chronometer) findViewById(R.id.chronometer1);
        c.setBase(SystemClock.elapsedRealtime());//Inicializa el temporizador a cero, al momento actual
        ((Chronometer) findViewById(R.id.chronometer1)).start();//comienza el temporizador
    }

    /**
     * Metodo que cambia el color del layaout pulsado.
     * Si el color ya ha sido cambiado no hace nada. Una vez que todos los layout han sido cambiados
     * muestra un mensaje, cierra la aplicación
     * */
    public void cambiaColor(View vista)
    {
        //ColorDrawable viewColor = (ColorDrawable) vista.getBackground();//recogemos el color del layout que se ha pulsado
        //int colorId = viewColor.getColor();

        //Tag recibe como parametro un objeto de tipo Object
        tag = vista.getTag();

        if(tag == null)
        {

            this.t++;
            vista.setTag(true);//añadimos una etiqueta para saber que este Linear ya se ha cambiado de color
            //vista.setVisibility(View.INVISIBLE);//Va combinando los layouts y queda muy gracioso!!!
            vista.setBackgroundColor(color);

        }

        if(t == 12)
        {
            Chronometer c = (Chronometer) findViewById(R.id.chronometer1);
            c.stop();//Paramos el temporizado
            lng_puntuacion = c.getBase();//recogemos el nuevo tiempo del cronometro y lo grabamos
            grabarPuntuacion(lng_puntuacion);
            cerrar();
        }
    }

    private void grabarPuntuacion(long lng_tiempo)
    {
        int id = 0;
        id = AlmacenaTiempos.getUltimoId(this);

        if(id == 0)
        {
            id = 1;
        }
        else{
            id++;
        }
        //Convertimos a JSON el valor usuario - tiempo
        Gson gson = new Gson();
        Puntuacion puntuacion = new Puntuacion(usuario,lng_tiempo,id);//Objeto puntuación para pasarselo a la clase GSON


        String srz_puntuacion = gson.toJson(puntuacion);//Devuelve un objeto serializado
        AlmacenaTiempos.setPuntuacion(srz_puntuacion,this,String.valueOf(id));//Grabamos el registro
        AlmacenaTiempos.setUltimoId(id,this);
    }


    /**
     * Metodo que muestra un mesaje y cierra la aplicación
     * */
    private void cerrar()
    {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            Toast.makeText(getApplicationContext(), "Tu tiempo "+ lng_puntuacion, Toast.LENGTH_SHORT).show();
            this.finishAffinity();//cierra la aplicación
        } else{
            super.onBackPressed();//Lo que haga el padre en su caso
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
