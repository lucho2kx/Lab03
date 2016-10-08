package ar.edu.utn.frsf.dmg.teamflash.lab03.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * Created by AdminUser on 09/09/2016.
 */
public class Trabajo implements Parcelable {

    private Integer id;
    private String descripcion;
    private Integer horasPresupuestadas;
    private Categoria categoria;
    private Double precioMaximoHora;
    private Date fechaEntrega;
    private Integer monedaPago; //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
    private Boolean requiereIngles;

    public Trabajo(){
        Random r =new Random();
        this.monedaPago=1+r.nextInt(4);
        this.requiereIngles=r.nextInt()%2==0;
        Integer dias = (7+r.nextInt(35));
        long ts =(long) (System.currentTimeMillis()+dias*1000*60*60*24);
        this.fechaEntrega = new Date(ts);
        this.precioMaximoHora=r.nextDouble()*(10+r.nextInt(100));
        this.horasPresupuestadas = dias/ 4+r.nextInt(6);
        this.categoria= Categoria.CATEGORIAS_MOCK[r.nextInt(5)];
    }

    public Trabajo(Integer id,String desc){
        this();
        this.id = id;
        this.descripcion = desc;
    }

    public Trabajo(Integer id,String desc,Categoria cat){
        this();
        this.id = id;
        this.descripcion = desc;
        this.categoria = cat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getHorasPresupuestadas() {
        return horasPresupuestadas;
    }

    public void setHorasPresupuestadas(Integer horasPresupuestadas) {
        this.horasPresupuestadas = horasPresupuestadas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecioMaximoHora() {
        return precioMaximoHora;
    }

    public void setPrecioMaximoHora(Double precioMaximoHora) {
        this.precioMaximoHora = precioMaximoHora;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getMonedaPago() {
        return monedaPago;
    }

    public void setMonedaPago(Integer monedaPago) {
        this.monedaPago = monedaPago;
    }

    public Boolean getRequiereIngles() {
        return requiereIngles;
    }

    public void setRequiereIngles(Boolean requiereIngles) {
        this.requiereIngles = requiereIngles;
    }

    public static final Trabajo[] TRABAJOS_MOCK= new Trabajo[]{
            new Trabajo(1,"Proyecto ABc"),
            new Trabajo(2,"Sistema de Gestión"),
            new Trabajo(3, "Aplicación XYZ"),
            new Trabajo(4,"Módulo NN1"),
            new Trabajo(5,"Sistema de administración TDF"),
            new Trabajo(6,"Aplicación OYU 66"),
            new Trabajo(7,"Gestión de laboratorios"),
            new Trabajo(8,"Sistema Universidades"),
            new Trabajo(9,"Portal Compras"),
            new Trabajo(10,"Gestión RRHH"),
            new Trabajo(11,"Traductor Automático"),
            new Trabajo(12,"Alquileres online"),
            new Trabajo(13,"Gestión financiera"),
            new Trabajo(14,"Módulo Seguridad"),
            new Trabajo(15,"consultoria performance"),
            new Trabajo(16,"Ecommerce Uah"),
            new Trabajo(17,"Portal Web Htz"),
            new Trabajo(18,"Sitio Coroporativo"),
            new Trabajo(19,"Aplicación www1")
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.descripcion);
        dest.writeValue(this.horasPresupuestadas);
        dest.writeParcelable(this.categoria, flags);
        dest.writeValue(this.precioMaximoHora);
        dest.writeLong(this.fechaEntrega != null ? this.fechaEntrega.getTime() : -1);
        dest.writeValue(this.monedaPago);
        dest.writeValue(this.requiereIngles);
    }

    protected Trabajo(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.descripcion = in.readString();
        this.horasPresupuestadas = (Integer) in.readValue(Integer.class.getClassLoader());
        this.categoria = in.readParcelable(Categoria.class.getClassLoader());
        this.precioMaximoHora = (Double) in.readValue(Double.class.getClassLoader());
        long tmpFechaEntrega = in.readLong();
        this.fechaEntrega = tmpFechaEntrega == -1 ? null : new Date(tmpFechaEntrega);
        this.monedaPago = (Integer) in.readValue(Integer.class.getClassLoader());
        this.requiereIngles = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Trabajo> CREATOR = new Creator<Trabajo>() {
        @Override
        public Trabajo createFromParcel(Parcel source) {
            return new Trabajo(source);
        }

        @Override
        public Trabajo[] newArray(int size) {
            return new Trabajo[size];
        }
    };
}