package ar.edu.utn.frsf.dmg.teamflash.lab03.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AdminUser on 09/09/2016.
 */
public class Categoria implements Parcelable {

    private Integer id;
    private String descripcion;
    private ArrayList<Trabajo> trabajos;

    public Categoria(){
        this.trabajos=new ArrayList<Trabajo>();
    }

    public Categoria(Integer id, String desc){
        this();
        this.id = id;
        this.descripcion = desc;
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

    public ArrayList<Trabajo> getTrabajo() {
        return trabajos;
    }

    public void setTrabajo(ArrayList<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public void addTrabajo(Trabajo t){
        this.trabajos.add(t);
    }

    public static final Categoria[] CATEGORIAS_MOCK= new Categoria[]{
            new Categoria(1,"Arquitecto"),
            new Categoria(2,"Desarrollador"),
            new Categoria(3, "Tester"),
            new Categoria(4,"Analista"),
            new Categoria(5,"Mobile Developer")
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.descripcion);
        dest.writeTypedList(this.trabajos);
    }

    protected Categoria(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.descripcion = in.readString();
        this.trabajos = in.createTypedArrayList(Trabajo.CREATOR);
    }

    public static final Creator<Categoria> CREATOR = new Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel source) {
            return new Categoria(source);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };
}