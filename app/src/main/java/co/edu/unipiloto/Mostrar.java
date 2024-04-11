package co.edu.unipiloto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {
    ListView lstMostrarUsuarios;
    daoUsuario daoMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);
        lstMostrarUsuarios = (ListView) findViewById(R.id.listView_mostrar);

        daoMostrar= new daoUsuario(this);
        ArrayList<Usuario> l= daoMostrar.selectUsuarios();

        ArrayList<String> list = new ArrayList<String>();
        for (Usuario u: l) {
            list.add("Nombre: " + u.getNombre() + " Correo: " + u.getCorreo());
            list.add("Usuario: " + u.getUsuario() + " Contraseña: " + u.getPassword() + " id: " + u.getId());
            list.add("Cédula: " + u.getCedula() + " Rol: " + u.getRol() + "Genero: " + u.getGenero());
        }
        ArrayAdapter<String> a=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        lstMostrarUsuarios.setAdapter(a);
    }

}