package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta;

import android.content.Context;
import android.util.SparseBooleanArray;

import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;

/**
 * Created by AdminUser on 09/09/2016.
 */
public interface IOfertaView {

    Context getContext();

    void setAdapter(List<Categoria> categorias);

    void showSpinner();

    void finish(Trabajo trabajo);

    void upDateHsSelecionada(Integer hsSel);

    void upDateMonedaSelec(int moneda);

    void setErrores(SparseBooleanArray errores);

    void showMensaje(String msj);

}
