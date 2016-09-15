package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal;

import android.content.Context;

import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;


/**
 * Created by AdminUser on 09/09/2016.
 */
public interface IMainView {

    Context getContext();

    void setAdapter(List<Trabajo> listaOferta);

    void showListViewOfertas();

    void showMensaje(String msj);

    void goToCrearOfertaActivity();

}
