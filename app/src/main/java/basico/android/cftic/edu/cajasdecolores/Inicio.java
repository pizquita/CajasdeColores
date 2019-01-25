package basico.android.cftic.edu.cajasdecolores;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {

    public String usuario ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        /*Comprobación de si el usuario esta guardado en preferences y saltar a la página de Main
        String usu = null;
        usu = AlmacenaTiempos.getUsuario(this);
        //Comprobamos si ya está introducido el usuario
        if(usu != "")
        {
            usuario = usu;
            iraMain();
        }*/
    }

    /**
     * Método que guarda el usuario cuando se pulsa el botón
     * */
    public void guardaUsuario(View view) {

        //String usuario = null;
        usuario = ((EditText)findViewById(R.id.editUsuario)).getText().toString().trim();
        //Guardamos el usuario
       // AlmacenaTiempos.setUsuario(usuario,this);

        //redirigimos a la página MainActivity
        iraMain();

    }

    /**
     * Método que redirije a la página de MainActivity
     * */
    private void iraMain()
    {

        Intent myInt = new Intent(this , MainActivity.class);
        myInt.putExtra("USUARIO",usuario);
        startActivity(myInt);
    }
}
