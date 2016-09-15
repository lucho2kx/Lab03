package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta;

import android.util.Log;

import java.util.Date;
import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;
import ar.edu.utn.frsf.dmg.teamflash.lab03.service.OfertaService;
import ar.edu.utn.frsf.dmg.teamflash.lab03.service.impl.OfertaServiceImpl;

/**
 * Created by AdminUser on 09/09/2016.
 */
public class OfertaPresenter implements IOfertaPresenter {

    private OfertaService ofertaService;
    private IOfertaView view;

    public OfertaPresenter(IOfertaView view) {
        this.view= view;
        this.ofertaService= new OfertaServiceImpl(this.view.getContext());
    }

    @Override
    public void showSpinnerCategoria() {
        List<Categoria> categorias= ofertaService.getListaCategoria();
        Log.i("showSpinnerCategoria()","categorias.size()="+categorias.size());
        view.setAdapter(categorias);
        view.showSpinner();
    }

    @Override
    public void onClickedAgregar(String descripcion, Integer horasPresupuestadas, Categoria categoria, Double precioMaximoHora, Date fechaEntrega, Integer monedaPago, Boolean requiereIngles) {
        //validar los datos
        // Si está ok finiish
    }
}
