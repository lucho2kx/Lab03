package ar.edu.utn.frsf.dmg.teamflash.lab03.service.impl;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;
import ar.edu.utn.frsf.dmg.teamflash.lab03.service.OfertaService;

/**
 * Created by AdminUser on 09/09/2016.
 */
public class OfertaServiceImpl implements OfertaService {

    private Context context;

    public OfertaServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Trabajo> getListaOferta() {
        return new ArrayList<>(Arrays.asList(Trabajo.TRABAJOS_MOCK));
    }

    @Override
    public List<Categoria> getListaCategoria() {
       return Arrays.asList(Categoria.CATEGORIAS_MOCK);
    }

    @Override
    public void persitir(Trabajo trabajo, OfertaServiceCallBack callBack) {
        try {
            // Aquí debería persistir oferta laboral en la Capa de acceso a datos,
            // también se deben validar los datos.
            // Vamos a dar por hecho que se realizó de manera correcta
            // dicha validación y persistencia
            Log.i("persitir",trabajo.toString());

            callBack.onSuccess(trabajo);
        }
        catch (Exception e) {
            // Error al guardar una oferta laboral
            callBack.onError(e.getMessage().toString());
        }
    }
}
