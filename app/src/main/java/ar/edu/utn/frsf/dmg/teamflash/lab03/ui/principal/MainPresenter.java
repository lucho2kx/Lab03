package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal;

import java.util.ArrayList;
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
        ArrayList<Trabajo> trabajos= ofertaService.getListaOferta();
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

    @Override
    public void addOfertaLaboral(Trabajo trabajo) {
        ArrayList<Trabajo> l= view.getLista();
        l.add(trabajo);
        view.setAdapter(l);
    }

    @Override
    public void postularseOferta(Trabajo trabajo) {
        // Aquí debería guardar la postulación
        // utilizando alguna clase del paquete de servicios
        // que tenga implementado el método de registrar la postulación,
        // vamos a ser de cuenta, qué se guardó tal acción
        // y se mostrará el msg de retorno.
        view.showMensaje("Se ha registrado la postulación.");
    }

    @Override
    public void compartirOferta(Trabajo trabajo) {

    }
}
