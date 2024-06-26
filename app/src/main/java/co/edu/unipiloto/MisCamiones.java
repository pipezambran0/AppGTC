package co.edu.unipiloto;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import java.util.ArrayList;
import java.util.List;

public class MisCamiones extends AppCompatActivity implements View.OnClickListener{
    Button btnPCMMCAgregarCamion, btnPCMMCEditarCamion, btnPCMMCEliminarCamion,btnPCMMCInformacionCamion, btnPCMMCVolver; // PCMMC - Propietario Camion Menu Mis Camiones
    Spinner spiPCMMCMisCamiones;
    TextView sa;
    int id =0;
    int idc =0;
    String placa;
    Usuario u;
    Camion c;
    daoUsuario daoUPCMMC; //UPCMMC - Usuario Propietario Camion Menu Mis Camiones
    daoCamion daoCPCMMC; //CPCMMC - Camion Propietario Camion Menu Mis Camiones

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_camiones);

        sa = (TextView) findViewById(R.id.textView_PCMMC_SesionActual);
        spiPCMMCMisCamiones = (Spinner) findViewById(R.id.spinner_PCMMC_MisCamiones);
        btnPCMMCAgregarCamion = (Button) findViewById(R.id.button_PCMMC_AgregarCamion);
        btnPCMMCEditarCamion = (Button) findViewById(R.id.button_PCMMC_EditarCamion);
        btnPCMMCEliminarCamion = (Button) findViewById(R.id.button_PCMMC_EliminarCamion);
        btnPCMMCInformacionCamion = (Button) findViewById(R.id.button_PCMMC_InformacionCamion);
        btnPCMMCVolver = (Button) findViewById(R.id.button_PCMMC_Volver);

        btnPCMMCAgregarCamion.setOnClickListener(this);
        btnPCMMCEditarCamion.setOnClickListener(this);
        btnPCMMCEliminarCamion.setOnClickListener(this);
        btnPCMMCInformacionCamion.setOnClickListener(this);
        btnPCMMCVolver.setOnClickListener(this);

        daoUPCMMC = new daoUsuario(this);
        daoCPCMMC = new daoCamion(this);

        Bundle b= getIntent().getExtras();
        id= b.getInt("Id");
        u= daoUPCMMC.getUsuarioById(id);

        List<String> listaDePlacas = daoCPCMMC.getPlacasDeCamionesByIdPropietario(id);
        if (listaDePlacas.isEmpty()) {
            listaDePlacas.add("No hay camiones disponibles");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDePlacas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiPCMMCMisCamiones.setAdapter(adapter);

        sa.setText("Sesión: " + u.getId() + " Nombre: " + u.getNombre() + " Cedula: " + u.getCedula() + "\n" + "Usuario: " + u.getUsuario() + " Rol: " + u.getRol());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_PCMMC_AgregarCamion) {
            Intent iipcmc1 = new Intent(MisCamiones.this, agregarCamion.class);
            iipcmc1.putExtra("Id", id);
            startActivity(iipcmc1);

        }else if (v.getId() == R.id.button_PCMMC_EditarCamion) {
            idc = daoCPCMMC.obtenerIdCamionPorPlaca(spiPCMMCMisCamiones.getSelectedItem().toString());
            Intent iipcmc2 = new Intent(MisCamiones.this, editarCamion.class);
            iipcmc2.putExtra("Id", id);
            iipcmc2.putExtra("IdC", idc);
            startActivity(iipcmc2);

        }else if (v.getId() == R.id.button_PCMMC_EliminarCamion) {
            AlertDialog.Builder b= new AlertDialog.Builder(this);
            b.setMessage("Estas seguro de Eliminar el camión?");
            b.setCancelable(false);
            b.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    idc = daoCPCMMC.obtenerIdCamionPorPlaca(spiPCMMCMisCamiones.getSelectedItem().toString());
                    if(daoCPCMMC.deleteCamion(idc)){
                        Toast.makeText(MisCamiones.this, "Eliminacion Exitosa!", Toast.LENGTH_LONG).show();
                        Intent iipcmc3 = new Intent(MisCamiones.this, MisCamiones.class);
                        iipcmc3.putExtra("Id", id);
                        finish();
                        startActivity(iipcmc3);
                    }else{
                        Toast.makeText(MisCamiones.this, "Eliminacion No Exitosa!", Toast.LENGTH_LONG).show();
                    }
                }
            });
            b.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            b.show();

        }else if (v.getId() == R.id.button_PCMMC_InformacionCamion) {
            String placaSeleccionada = spiPCMMCMisCamiones.getSelectedItem().toString();
            int idCamion = daoCPCMMC.obtenerIdCamionPorPlaca(placaSeleccionada);
            Camion camionSeleccionado = daoCPCMMC.getCamionById(idCamion);
            if (camionSeleccionado != null) {
                Usuario propietario = daoUPCMMC.getUsuarioById(camionSeleccionado.getIdPropietario());
                Usuario conductor = daoUPCMMC.getUsuarioById(camionSeleccionado.getIdConductor());
                if (propietario != null && conductor != null) {
                    String mensaje = "Id Camión: " + camionSeleccionado.getId() +
                        "\nPropietario: " +
                        "\nNombre: " + propietario.getNombre() +
                        "\nCédula: " + propietario.getCedula() +
                        "\nConductor: " +
                        "\nNombre: " + conductor.getNombre() +
                        "\nCédula: " + conductor.getCedula() +
                        "\nViajes: " + camionSeleccionado.getViajes() +
                        "\nPlaca: "  + camionSeleccionado.getPlaca() +
                        "\nMarca: "  + camionSeleccionado.getMarca() +
                        "\nEstado: " + camionSeleccionado.getEstado();
                    Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
                    TextView textView = toast.getView().findViewById(android.R.id.message);
                    if (textView != null) {
                        textView.setTextSize(20);
                    }
                    toast.show();
                    }else {
                    Toast.makeText(this, "No se encontró información del propietario o del conductor del camión seleccionado", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "No se encontró información del camión seleccionado", Toast.LENGTH_SHORT).show();
            }
        }else if (v.getId() == R.id.button_PCMMC_Volver) {
            Intent iipcmc4 = new Intent(MisCamiones.this, InicioPropietarioCamion.class);
            iipcmc4.putExtra("Id", id);
            startActivity(iipcmc4);
            finish();
        }
    }
}