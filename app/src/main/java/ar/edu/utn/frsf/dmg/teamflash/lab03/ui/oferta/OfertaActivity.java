package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.R;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;
import ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta.adapter.CategoriaSpinnerAdapter;

public class OfertaActivity extends AppCompatActivity implements IOfertaView {

    private Trabajo trabajo;
    private Categoria categ= null;
    private IOfertaPresenter presenter;
    private CategoriaSpinnerAdapter adapter;
    private static final String TRABAJO_RETURN= "trabajo";

    private Spinner categoriaSpinner;
    private EditText descripcion;
    private SeekBar horasEstimadas;
    private EditText horasPesos;
    private RadioGroup moneda;
    private CheckBox checkBox_ingles;
    private EditText fechaFin;
    private Button agregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoriaSpinner= (Spinner) findViewById(R.id.spinner_categoria);
        descripcion= (EditText) findViewById(R.id.editText_descripcion);
        horasEstimadas= (SeekBar) findViewById(R.id.seekBar_hs_est);
        horasPesos= (EditText) findViewById(R.id.editText_pesos_x_hora);
        moneda= (RadioGroup) findViewById(R.id.radioButton);
        checkBox_ingles= (CheckBox) findViewById(R.id.checkBox_requiere_ingles);
        fechaFin= (EditText) findViewById(R.id.editText_fecha_fin);
        agregar= (Button) findViewById(R.id.button_agregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hacer algo
                //presenter.
            }
        });

        presenter= new OfertaPresenter(this);

        presenter.showSpinnerCategoria();

    }


    /*******   IOfertaView   *******/

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void setAdapter(List<Categoria> categorias) {

        adapter= new CategoriaSpinnerAdapter(this, categorias);
        categoriaSpinner.setAdapter(adapter);
        categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                /*if (position != 0)
                    ligaSup= (Liga)adapterView.getItemAtPosition(position);*/

                categ= adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nothing
            }

        });
        //categoriaSpinner.
    }

    @Override
    public void showSpinner() {
        categoriaSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void finish(Trabajo trabajo) {
            //Log.i("NuevoEquipo.finish",mensajeRetorno.toString());
            Intent data = new Intent();
            data.putExtra(TRABAJO_RETURN,trabajo);
            setResult(RESULT_OK, data);
            finish();
    }
}
