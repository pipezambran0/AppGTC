package co.edu.unipiloto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InicioCliente extends AppCompatActivity implements View.OnClickListener{
    Button btnMICMisSolicitudes, btnMICOpciones, btnMICSalir; //MIC - Menu Inicio Cliente
    TextView sa;
    int id =0;
    Usuario u;
    daoUsuario daoUMIC; //UMIC - Usuario Menu Inicio Cliente

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_cliente);

        sa = (TextView) findViewById(R.id.textView_MIC_SesionActual);
        btnMICMisSolicitudes = (Button) findViewById(R.id.button_MIC_MisSolicitudes);
        btnMICOpciones = (Button) findViewById(R.id.button_MIC_Opciones);
        btnMICSalir = (Button) findViewById(R.id.button_MIC_Salir);

        btnMICMisSolicitudes.setOnClickListener(this);
        btnMICOpciones.setOnClickListener(this);
        btnMICSalir.setOnClickListener(this);

        Bundle b= getIntent().getExtras();
        id= b.getInt("Id");
        daoUMIC = new daoUsuario(this);
        u= daoUMIC.getUsuarioById(id);

        sa.setText("Sesión: " + u.getId() + " Nombre: " + u.getNombre() + " Cedula: " + u.getCedula() + "\n" + "Usuario: " + u.getUsuario() + " Rol: " + u.getRol());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_MIC_MisSolicitudes) {
            Intent iic1 = new Intent(InicioCliente.this, MisSolicitudesCliente.class);
            iic1.putExtra("Id", id);
            startActivity(iic1);
        }else if (v.getId() == R.id.button_MIC_Opciones) {
            Intent iic2 = new Intent(InicioCliente.this, Opciones.class);
            iic2.putExtra("Id", id);
            startActivity(iic2);
        }else if (v.getId() == R.id.button_MIC_Salir) {
            Intent iic3 = new Intent(InicioCliente.this, Main.class);
            startActivity(iic3);
            finish();
        }
    }
}