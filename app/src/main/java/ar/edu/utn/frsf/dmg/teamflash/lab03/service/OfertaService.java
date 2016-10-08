package ar.edu.utn.frsf.dmg.teamflash.lab03.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;

/**
 * Created by AdminUser on 09/09/2016.
 */
public interface OfertaService {

    interface OfertaServiceCallBack {

        void onError(String msg);

        void onSuccess(Trabajo trabajo);
    }

    ArrayList<Trabajo> getListaOferta();

    List<Categoria> getListaCategoria();

    void persitir(Trabajo trabajo, OfertaServiceCallBack callBack);

}
