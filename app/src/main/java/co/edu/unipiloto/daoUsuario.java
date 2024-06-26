package co.edu.unipiloto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd="BDUsuarios";
    String tabla="create table if not exists usuario(id integer primary key autoincrement, usuario text, pass text, correo text, nombre text, cedula text, rol text, genero text)";

    public daoUsuario(Context c){
        this.c=c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u=new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        if (buscar(u.getUsuario())==0){
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("pass", u.getPassword());
            cv.put("correo", u.getCorreo());
            cv.put("nombre", u.getNombre());
            cv.put("cedula", u.getCedula());
            cv.put("rol", u.getRol());
            cv.put("genero", u.getGenero());
            return  (sql.insert("usuario", null, cv)>0);
        }else{
            return false;
        }
    }

    public int buscar(String u){
        int x=0;
        lista=selectUsuarios();
        for (Usuario us:lista) {
            if(us.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista=new ArrayList<Usuario>();
        lista.clear();
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do{
                Usuario u=new Usuario();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setCorreo(cr.getString(3));
                u.setNombre(cr.getString(4));
                u.setCedula(cr.getString(5));
                u.setRol(cr.getString(6));
                u.setGenero(cr.getString(7));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }

    public int Login(String u, String p){
        int a = 0;
        Cursor cr=sql.rawQuery("select * from usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u) && cr.getString(2).equals(p)){
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        lista= selectUsuarios();
        for (Usuario us:lista) {
            if(us.getUsuario().equals(u) && us.getPassword().equals(p)){
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id){
        lista= selectUsuarios();
        for (Usuario us:lista) {
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }

    public int getIdUsuarioPorCedula(String cedula) {
        ArrayList<Usuario> listaDeUsuarios = selectUsuarios();

        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getCedula().equals(cedula)) {
                return usuario.getId();
            }
        }
        return -1;
    }

    public List<String> getCedulasDeConductores() {
        List<String> listaCedulas = new ArrayList<>();

        ArrayList<Usuario> listaDeUsuarios = selectUsuarios();
        for (Usuario usuario : listaDeUsuarios) {
            if (usuario.getRol().equals("Conductor de Camión")) {
                listaCedulas.add(usuario.getCedula());
            }
        }
        return listaCedulas;
    }

    public boolean buscarCedula(String cedu){
        lista= selectUsuarios();
        for (Usuario us:lista) {
            if(us.getCedula().equals(cedu)){
                return true;
            }
        }
        return false;
    }

    public boolean updateUsuario(Usuario u){
        ContentValues cv = new ContentValues();
        cv.put("usuario", u.getUsuario());
        cv.put("pass", u.getPassword());
        cv.put("correo", u.getCorreo());
        cv.put("nombre", u.getNombre());
        cv.put("cedula", u.getCedula());
        return  (sql.update("usuario",cv, "id="+u.getId(), null)>0);
    }

    public boolean deleteUsuario(int id){
        return (sql.delete("usuario", "id="+id, null) > 0);
    }
}
