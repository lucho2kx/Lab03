package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta;

import java.util.Date;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;

/**
 * Created by AdminUser on 09/09/2016.
 */
public interface IOfertaPresenter {

    void showSpinnerCategoria();

    void onClickedAgregar(String descripcion,
                          Integer horasPresupuestadas,
                          Categoria categoria,
                          Double precioMaximoHora,
                          Date fechaEntrega,
                          Integer monedaPago, //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
                          Boolean requiereIngles);


}
