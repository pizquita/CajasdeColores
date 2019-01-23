package basico.android.cftic.edu.cajasdecolores;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        String usu = null;
        usu = AlmacenaTiempos.getUsuario(this);
        //Comprobamos si ya está introducido el usuario
        if(usu != null)
        {
            Intent myInt = new Intent();
            myInt = new Intent(this , MainActivity.class);
            startActivity(myInt);
        }

    }

    /**
     * Método que guarda el usuario cuando se pulsa el botón
     * */
    public void guardaUsuario(View view) {

        String usuario = null;
        usuario = ((EditText)findViewById(R.id.editUsuario)).getText().toString().trim();
        AlmacenaTiempos.setUsuario(usuario,this);
        Intent myInt = new Intent();
        myInt = new Intent(this , MainActivity.class);
        startActivity(myInt);

    }
}
