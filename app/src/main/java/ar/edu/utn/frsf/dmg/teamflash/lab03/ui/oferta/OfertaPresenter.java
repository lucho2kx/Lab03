package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta;

import android.util.Log;
import android.util.SparseBooleanArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;
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
    public void onClickedAgregar(String descripcion, Integer horasPresupuestadas, Categoria categoria, String precioMaximoHora, String fechaEntrega, Integer monedaPago, Boolean requiereIngles) {
        //validar los datos
        // Si está ok finiish
        SparseBooleanArray errores= new SparseBooleanArray();
        boolean b= false;

        if (!validarDescrpicion(descripcion)) {
            errores.put(0,true);
            b= true;
        }

        if (!validarHorasPresupuestadas(horasPresupuestadas)) {
            errores.put(1,true);
            b= true;
        }

        if (!validarCategoria(categoria)) {
            errores.put(2,true);
            b= true;
        }

        if (!validarPrecioMaximo(precioMaximoHora)) {
            errores.put(3,true);
            b= true;
        }

        Date fecha= validarFecha(fechaEntrega);
        if (fecha == null) {
            errores.put(4,true);
            b= true;
        }

        if (!validarMonedaPago(monedaPago)) {
            errores.put(5,true);
            b= true;
        }

        if (b) {
            // Hay error

            view.setErrores(errores);
        }
        else {
            // Agregar oferta laboral
            Trabajo t= new Trabajo();
            t.setCategoria(categoria);
            t.setDescripcion(descripcion);
            t.setHorasPresupuestadas(horasPresupuestadas);
            t.setPrecioMaximoHora(new Double(precioMaximoHora));
            t.setMonedaPago(monedaPago);
            t.setRequiereIngles(requiereIngles);
            t.setFechaEntrega(fecha);
            ofertaService.persitir(t, new OfertaService.OfertaServiceCallBack() {
                @Override
                public void onError(String msg) {
                    view.showMensaje(msg);
                }

                @Override
                public void onSuccess(Trabajo trabajo) {
                    view.finish(trabajo);
                }
            });

        }

    }

    @Override
    public void upDateHsSelec(Integer hsSelec) {
        view.upDateHsSelecionada(hsSelec);
    }

    @Override
    public void onClickRadioButtonMoneda(int moneda) {
        view.upDateMonedaSelec(moneda);
    }


    private boolean validarDescrpicion(String descrip) {
        if ((descrip != null) && (descrip.length() > 0))
            return true;
        else
            return false;
    }

    private boolean validarHorasPresupuestadas(Integer horasPresupuestadas) {
        if (horasPresupuestadas > 0)
            return true;
        else
            return false;
    }

    private boolean validarCategoria(Categoria categoria) {
        if (categoria != null)
            return true;
        else
            return false;
    }

    private boolean validarPrecioMaximo(String precioMaximo) {
        try {
            if (Double.parseDouble(precioMaximo) > 0)
                return true;
            else
                return false;
        }
        catch (NumberFormatException nfe) {
            return false;
        }

    }

    private Date validarFecha(String fechaEntrega) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        formatoDelTexto.setLenient(false);

        Date fecha = null;
        try {
            fecha = formatoDelTexto.parse(fechaEntrega);
        }
        catch (ParseException ex) {
            return fecha;
        }

        return fecha;
    }

    private boolean validarMonedaPago(Integer monedaPago) {
        if ((monedaPago >= 0) && (monedaPago < 5))
            return true;
        else
            return false;
    }

}
