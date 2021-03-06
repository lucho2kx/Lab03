package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;

/**
 * Created by AdminUser on 09/09/2016.
 */
public interface IMainPresenter {

    void showListOfertas();

    void showToastTrabajo(Trabajo trabajo);

    void navigateToCrearOferta();

    void navigateToConfiguracion();

    void navigateToVerMisOfertas();

    void navigateToSalir();

    void addOfertaLaboral(Trabajo trabajo);

    void postularseOferta(Trabajo trabajo);

    void compartirOferta(Trabajo trabajo);
}
