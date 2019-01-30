package basico.android.cftic.edu.cajasdecolores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

public class Inicio extends AppCompatActivity {

    public String usuario ;
    public boolean devuelta;
    public final int FOTO_OK = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        devuelta = false;


        //Comprobamos si es la primera vez que entra en la aplicación o vuelve de Main para cambiar el usuario
        this.devuelta = getIntent().getBooleanExtra("DEVUELTA",false);


        //List<Puntuacion> ptc = AlmacenaTiempos.cargarPuntuaciones(this);
        findViewById(R.id.lnr_opciones).setVisibility(View.GONE);
        findViewById(R.id.lnr_nuevousu).setVisibility(View.VISIBLE);

        //FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.img_foto);
        //fab.setOnClickListener(new);
    }

    /**
     * Método que guarda el usuario cuando se pulsa el botón
     * */
    public void guardaUsuario(View view)
    {
        //Recogemos el usuario de la caja de texto
        usuario = ((EditText)findViewById(R.id.editUsuario)).getText().toString().trim();
        iraMain();

    }

    /**
     * Método que redirije a la página de MainActivity
     * */
    private void iraMain()
    {

        Intent myInt = new Intent(this , MainActivity.class);
        myInt.putExtra("USUARIO",usuario);

        if(this.devuelta)//Si viene de Main pidiendo el cmabio de usuario devolvemos RESULT_OK
        {
            setResult(RESULT_OK,myInt);
            finish();
        }else {
            startActivity(myInt);
        }
    }

    /**
     * Método para mostrar una lista de usuarios y poder elegir entre uno de ellos
     * */
    public void cargarUsuario(View view) {
    }


    /**
     * Método para crear un usuario nuevo
     * */
    public void usuarioNuevo(View view) {
        findViewById(R.id.lnr_nuevousu).setVisibility(View.VISIBLE);
        findViewById(R.id.lnr_opciones).setVisibility(View.INVISIBLE);
    }


    /**
     * Metodo que llama a la cámara para hacer una foto
     * Hay que modificar el manifest e incluir la siguiente instrucción para dar permiso a la aplicación para usar la cámara:
     * <uses-feature android:name="android.hardware.camera" android:required="true" />
     * */
    public void hacerFoto(View view)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {//Comprueba si hay alguna aplicación
            startActivityForResult(takePictureIntent, FOTO_OK);
        }
    }

    /**
     * Método que recoge la respuesta de la cámara una vez realizada la foto
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FOTO_OK && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");//Recogemos la imagen en un bitmap
            ((ImageView)( findViewById(R.id.img_foto))).setImageBitmap(imageBitmap);
        }
    }
}
