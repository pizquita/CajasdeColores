package basico.android.cftic.edu.cajasdecolores;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int t;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t = 0;
        color = getResources().getColor(R.color.tocado);
        //Leemos el record
        String tmp = null;
        tmp = AlmacenaTiempos.getTiempo(this.getApplicationContext());//vale con poner this
        Log.d("MIAPP","Record actual: "+tmp);
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
        ColorDrawable viewColor = (ColorDrawable) vista.getBackground();//recogemos el color del layout que se ha pulsado
        int colorId = viewColor.getColor();

        Object tag = vista.getTag();

        if(tag == null)
        {
            this.t++;
            vista.setTag(true);
            //vista.setVisibility(View.INVISIBLE);//Va combinando los layouts y queda muy gracioso!!!
            vista.setBackgroundColor(color);
        }

        if(t == 12)
        {
            Chronometer c = (Chronometer) findViewById(R.id.chronometer1);
            c.stop();//Paramos el temporizado
            long time = c.getBase();
            String tmp = c.getContentDescription().toString();//recogemos el nuevo tiempo del cronometro y lo grabamos
            grabaRegistro(tmp);
            cerrar();
        }
    }

    private void grabaRegistro(String tmp)
    {
        Log.d("MIAPP",tmp);
        AlmacenaTiempos.setTiempo(tmp,this.getApplicationContext());//basta con poner this
    }


    /**
     * Metodo que muestra un mesaje y cierra la aplicación
     * */
    private void cerrar()
    {
        String tmp = null;
        tmp = AlmacenaTiempos.getTiempo(this.getApplicationContext()); //leemos el registro que hemos almacenado para mostrarlo en el mensaje

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            Toast.makeText(getApplicationContext(), "Tu tiempo "+ tmp, Toast.LENGTH_SHORT).show();
            this.finishAffinity();//cierra la aplicación
        } else{
            super.onBackPressed();//Lo que haga el padre en su caso
        }
    }
}
