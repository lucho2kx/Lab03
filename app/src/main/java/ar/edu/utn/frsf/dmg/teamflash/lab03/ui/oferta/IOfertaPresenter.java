package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;

/**
 * Created by AdminUser on 09/09/2016.
 */
public interface IOfertaPresenter {

    void showSpinnerCategoria();

    void onClickedAgregar(String descripcion,
                          Integer horasPresupuestadas,
                          Categoria categoria,
                          String precioMaximoHora,
                          String fechaEntrega,
                          Integer monedaPago, //1 US$ 2Euro 3 AR$- 4 Libra 5 R$
                          Boolean requiereIngles);



    void upDateHsSelec(Integer hsSelec);

    void onClickRadioButtonMoneda(int moneda);
}
