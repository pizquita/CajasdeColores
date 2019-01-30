package basico.android.cftic.edu.cajasdecolores;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.Snackbar;
import com.google.gson.Gson;

import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    private int t;
    private int color;
    private String usuario;
    private Object tag;
    private long lng_puntuacion;
    private  final int returncode=305;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Inicializamos las variables de clase
        usuario = getIntent().getStringExtra("USUARIO");
        ((TextView)findViewById(R.id.nombre)).setText(usuario);
        t = 0;
        lng_puntuacion = 0;
        color = getResources().getColor(R.color.tocado);
        findViewById(R.id.playbutton).setTag(null);

        //Modificar la barra para añadir la flecha de vuelta  atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setSubtitle(usuario);//Ponemos un texto en la barra de título
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowTitleEnabled(false);//Oculta el texto de la barra


    }

    /**
     * Método que oculta el botón al pulsarlo
     * */
    public void star (View play)
    {

        //TODO ocultar o quitar el botón

        play.setTag(true);//Etiqueta para controlar que se pulsa el boton y no dejar cambiar el color de las capas
        play.setVisibility(View.INVISIBLE);//Propiedad que "elimina" el boton

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

        if(findViewById(R.id.playbutton).getTag() != null) {
            //Tag recibe como parametro un objeto de tipo Object
            tag = vista.getTag();
            if (tag == null) {

                this.t++;
                vista.setTag(true);//añadimos una etiqueta para saber que este Linear ya se ha cambiado de color
                //vista.setVisibility(View.INVISIBLE);//Va combinando los layouts y queda muy gracioso!!!
                vista.setBackgroundColor(color);

            }

            if (t == 12) {
                Chronometer c = (Chronometer) findViewById(R.id.chronometer1);
                c.stop();//Paramos el temporizado
                lng_puntuacion = c.getBase();//recogemos el nuevo tiempo del cronometro y lo grabamos
                grabarPuntuacion(lng_puntuacion);
                cerrar();
            }
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

            FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.playbutton);
           // fab.setOnClickListener(new ListenerFAB());

            Snackbar.make(fab,"Tu tiempo "+ lng_puntuacion,Snackbar.LENGTH_INDEFINITE).show();

            //Toast.makeText(getApplicationContext(), "Tu tiempo "+ lng_puntuacion, Toast.LENGTH_SHORT).show();
            this.finishAffinity();//cierra la aplicación
        } else{
            super.onBackPressed();//Lo que haga el padre en su caso
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Metodo llamado al pulsar una opción del menú
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.cambiausu:
                Intent i =  new Intent(this, Inicio.class);
                i.putExtra("DEVUELTA",true);
                startActivityForResult(i,returncode);
                break;
            case android.R.id.home:
                Log.d("MIAPP", "Tocó ir hacia atrás");
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Método llamado cuando retorna de lanzar Intent con la opción startActivityForResult
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == returncode)
        {
            if(resultCode == RESULT_OK)
            {
                usuario = data.getStringExtra("USUARIO");
                ((TextView)findViewById(R.id.nombre)).setText(usuario);
            }
        }
    }
}
