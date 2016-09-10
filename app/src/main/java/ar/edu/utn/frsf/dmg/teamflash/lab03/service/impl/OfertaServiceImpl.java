package ar.edu.utn.frsf.dmg.teamflash.lab03.service.impl;

import android.content.Context;

import java.lang.reflect.Array;
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
    public List<Trabajo> getListaOferta() {
        return  Arrays.asList(Trabajo.TRABAJOS_MOCK);
    }

    @Override
    public List<Categoria> getListaCategoria() {
       return Arrays.asList(Categoria.CATEGORIAS_MOCK);
    }

    @Override
    public void Persitir(Trabajo trabajo) {

    }
}
