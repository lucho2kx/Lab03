package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal;

import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;
import ar.edu.utn.frsf.dmg.teamflash.lab03.service.OfertaService;
import ar.edu.utn.frsf.dmg.teamflash.lab03.service.impl.OfertaServiceImpl;

/**
 * Created by AdminUser on 09/09/2016.
 */
public class MainPresenter implements IMainPresenter {

    private IMainView view;
    private OfertaService ofertaService;

    public MainPresenter(IMainView view) {
        this.view= view;
        this.ofertaService= new OfertaServiceImpl(view.getContext());
    }

    @Override
    public void showListOfertas() {
        List<Trabajo> trabajos= ofertaService.getListaOferta();
        view.setAdapter(trabajos);
        view.showListViewOfertas();
    }

    @Override
    public void showToastTrabajo(Trabajo trabajo) {
        view.showMensaje(trabajo.getDescripcion());
    }

    @Override
    public void navigateToCrearOferta() {
        view.goToCrearOfertaActivity();
    }

    @Override
    public void navigateToConfiguracion() {

    }

    @Override
    public void navigateToVerMisOfertas() {

    }

    @Override
    public void navigateToSalir() {

    }
}
