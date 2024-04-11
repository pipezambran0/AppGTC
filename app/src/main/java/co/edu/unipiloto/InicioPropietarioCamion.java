package co.edu.unipiloto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InicioPropietarioCamion extends AppCompatActivity implements View.OnClickListener{
    Button btnMIPCVerSolicitudes, btnMIPCMisSolicitudes, btnMIPCMisCamiones, btnMIPCOpciones, btnMIPCSalir; //MIPC - Menu Inicio Propietario Camion
    TextView sa;
    int id =0;
    Usuario u;
    daoUsuario daoUMIPC; //UMIPC - Usuario Menu Inicio Propietario Camion

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_propietario_camion);

        sa = (TextView) findViewById(R.id.textView_MIPC_SesionActual);
        btnMIPCVerSolicitudes = (Button) findViewById(R.id.button_MIPC_VerSolicitudes);
        btnMIPCMisSolicitudes = (Button) findViewById(R.id.button_MIPC_MisSolicitudes);
        btnMIPCMisCamiones = (Button) findViewById(R.id.button_MIPC_MisCamiones);
        btnMIPCOpciones = (Button) findViewById(R.id.button_MIPC_Opciones);
        btnMIPCSalir = (Button) findViewById(R.id.button_MIPC_Salir);

        btnMIPCVerSolicitudes.setOnClickListener(this);
        btnMIPCMisSolicitudes.setOnClickListener(this);
        btnMIPCMisCamiones.setOnClickListener(this);
        btnMIPCOpciones.setOnClickListener(this);
        btnMIPCSalir.setOnClickListener(this);

        Bundle b= getIntent().getExtras();
        id= b.getInt("Id");
        daoUMIPC = new daoUsuario(this);
        u= daoUMIPC.getUsuarioById(id);

        sa.setText("Sesión: " + u.getId() + " Nombre: " + u.getNombre() + " Cedula: " + u.getCedula() + "\n" + "Usuario: " + u.getUsuario() + " Rol: " + u.getRol());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_MIPC_VerSolicitudes) {

        }else if (v.getId() == R.id.button_MIPC_MisSolicitudes) {

        }else if (v.getId() == R.id.button_MIPC_MisCamiones) {
            Intent iipc3 = new Intent(InicioPropietarioCamion.this, MisCamiones.class);
            iipc3.putExtra("Id", id);
            startActivity(iipc3);
        }else if (v.getId() == R.id.button_MIPC_Opciones) {
            Intent iipc4 = new Intent(InicioPropietarioCamion.this, Opciones.class);
            iipc4.putExtra("Id", id);
            startActivity(iipc4);
        }else if (v.getId() == R.id.button_MIPC_Salir) {
            Intent iipc5 = new Intent(InicioPropietarioCamion.this, Main.class);
            startActivity(iipc5);
            finish();
        }
    }
}