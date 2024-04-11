package co.edu.unipiloto;

public class Camion {
    int id;
    Integer IdPropietario, IdConductor, Viajes;
    String Placa, Marca, Estado;

    public Camion() {
    }

    public Camion(int idPropietario, int idConductor, int viajes, String placa, String marca, String estado) {
        IdPropietario = idPropietario;
        IdConductor = idConductor;
        Viajes = viajes;
        Placa = placa;
        Marca = marca;
        Estado = estado;
    }

    public boolean isNull(){
        if (IdPropietario == null || IdConductor == null || Placa.equals("") || Marca.equals("")){
            return  false;
        }else{
            return true;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdPropietario() {
        return IdPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        IdPropietario = idPropietario;
    }

    public Integer getIdConductor() {
        return IdConductor;
    }

    public void setIdConductor(Integer idConductor) {
        IdConductor = idConductor;
    }

    public Integer getViajes() {
        return Viajes;
    }

    public void setViajes(Integer viajes) {
        Viajes = viajes;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", IdPropietario=" + IdPropietario +
                ", IdConductor=" + IdConductor +
                ", Viajes=" + Viajes +
                ", Placa='" + Placa + '\'' +
                ", Marca='" + Marca + '\'' +
                ", Estado='" + Estado + '\'' +
                '}';
    }
}
