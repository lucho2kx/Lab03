package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.R;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Categoria;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;
import ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta.adapter.CategoriaSpinnerAdapter;

public class OfertaActivity extends AppCompatActivity implements IOfertaView {
    public static final String STATE_TRABAJO = "trabajo";
    public static final String PRECIO_MAXIMO = "precioMax";
    public static final String FECHA_FIN = "fechaFin";
    public static final String TRABAJO_RETURN= "trabajo";

    private IOfertaPresenter presenter;
    private Trabajo trabajo= null;
    private Categoria categ= null;
    private CategoriaSpinnerAdapter adapter;
    private Integer hsPresupuestadas= 0;
    private Integer monedaPago= -1;
    private String msg="";

    private Spinner categoriaSpinner;
    private EditText descripcion;
    private TextInputLayout tillDescripcion;
    private TextView horasNecesarias;
    private SeekBar horasEstimadas;
    private EditText precioMaximo;
    private TextInputLayout tillPrecioMaximo;
    private RadioGroup moneda;
    private CheckBox checkBox_ingles;
    private EditText fechaFin;
    private TextView monPago;
    private TextInputLayout tillFechaFin;
    private TextView hsTrabajo;
    private ImageButton calendario;
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
        horasNecesarias= (TextView) findViewById(R.id.textView_horas_trab);
        tillDescripcion= (TextInputLayout) findViewById(R.id.til_descripcion);
        horasEstimadas= (SeekBar) findViewById(R.id.seekBar_hs_est);
        precioMaximo = (EditText) findViewById(R.id.editText_pesos_x_hora);
        tillPrecioMaximo= (TextInputLayout) findViewById(R.id.til_precio_max);
        moneda= (RadioGroup) findViewById(R.id.radioButton);
        monPago= (TextView) findViewById(R.id.textView_moneda_pago);
        checkBox_ingles= (CheckBox) findViewById(R.id.checkBox_ingles_agregar);
        fechaFin= (EditText) findViewById(R.id.editText_fecha_fin);
        tillFechaFin= (TextInputLayout) findViewById(R.id.til_fecha_fin);
        hsTrabajo= (TextView) findViewById(R.id.textView_horas_trabajo);
        calendario= (ImageButton) findViewById(R.id.imageButtonCalendario);
        agregar= (Button) findViewById(R.id.button_agregar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("descripción= ",descripcion.getText().toString());
                Log.i("hsPresupuestadas= ",""+hsPresupuestadas);
                Log.i("categ= ",categ.toString());
                Log.i("precioMaximo= ",precioMaximo.getText().toString());
                Log.i("fechaFin= ",fechaFin.getText().toString());
                Log.i("monedaPago= ",monedaPago.toString());
                Log.i("checkBox_ingles= ",""+checkBox_ingles.isChecked());

                presenter.onClickedAgregar( descripcion.getText().toString(),
                                            hsPresupuestadas,
                                            categ,
                                            precioMaximo.getText().toString(),
                                            fechaFin.getText().toString(),
                                            monedaPago,
                                            checkBox_ingles.isChecked() );

            }
        });

        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                fechaFin.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        horasEstimadas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                hsPresupuestadas= new Integer(i);
                presenter.upDateHsSelec(hsPresupuestadas);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                presenter.upDateHsSelec(hsPresupuestadas);
            }
        });

        moneda.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int res=-1;
                switch (checkedId) {
                    case R.id.radioButton_ar:
                        res=0;
                        break;
                    case R.id.radioButton_br:
                        res=1;
                        break;
                    case R.id.radioButton_eu:
                        res=2;
                        break;
                    case R.id.radioButton_uk:
                        res=3;
                        break;
                    case R.id.radioButton_us:
                        res=4;
                        break;
                }
                presenter.onClickRadioButtonMoneda(res);
            }

        });

        categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                categ= adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Hacer algo
            }

        });

        presenter= new OfertaPresenter(this);

        presenter.showSpinnerCategoria();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && trabajo != null) {
            trabajo= savedInstanceState.getParcelable(STATE_TRABAJO);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        trabajo= new Trabajo();
        trabajo.setDescripcion(descripcion.getText().toString());
        trabajo.setHorasPresupuestadas(hsPresupuestadas);
        trabajo.setCategoria(categ);
        trabajo.setMonedaPago(monedaPago);
        trabajo.setRequiereIngles(checkBox_ingles.isChecked());

        outState.putString(PRECIO_MAXIMO,precioMaximo.getText().toString());
        outState.putString(FECHA_FIN,fechaFin.getText().toString());
        outState.putParcelable(STATE_TRABAJO, trabajo);

        super.onSaveInstanceState(outState);
    }

    /*******   IOfertaView   *******/

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void setAdapter(List<Categoria> categorias) {

        adapter= new CategoriaSpinnerAdapter(this, categorias);

        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        categoriaSpinner.setAdapter(adapter);
        categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                categ= adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //nothing
            }

        });

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

    @Override
    public void upDateHsSelecionada(Integer hsSel) {
        hsPresupuestadas= hsSel;
        hsTrabajo.setText(hsSel+" "+this.getResources().getString(R.string.horas_trabajo_agregar));

    }

    @Override
    public void upDateMonedaSelec(int moneda) {
        monedaPago= moneda;
    }

    @Override
    public void setErrores(SparseBooleanArray errores) {
        //Máxima cant. de errores
        int tam= 6;
        msg="";

        for (int i=0; i < tam; i++) {
            if (errores.get(i)) {
                switch (i) {
                    case 0:
                        this.agregarMsg(this.getResources().getString(R.string.error_descripcion));
                        tillDescripcion.setError(this.getResources().getString(R.string.error_descripcion));
                        break;
                    case 1:
                        this.agregarMsg(this.getResources().getString(R.string.error_hs_presupuestadas));
                        horasNecesarias.setError(this.getResources().getString(R.string.error_hs_presupuestadas));
                        break;
                    case 2:
                        this.agregarMsg(this.getResources().getString(R.string.error_categoria));
                        break;
                    case 3:
                        this.agregarMsg(this.getResources().getString(R.string.error_precio_maximo));
                        tillPrecioMaximo.setError(this.getResources().getString(R.string.error_precio_maximo));
                        break;
                    case 4:
                        this.agregarMsg(this.getResources().getString(R.string.error_fecha));
                        tillFechaFin.setError(this.getResources().getString(R.string.error_fecha));
                        break;
                    case 5:
                        this.agregarMsg(this.getResources().getString(R.string.error_moneda_pago));
                        monPago.setError(this.getResources().getString(R.string.error_hs_presupuestadas));
                        break;
                }
            }
            else {
                switch (i) {
                    case 0:
                        tillDescripcion.setError(null);
                        break;
                    case 1:
                        horasNecesarias.setError(null);
                        break;
                    case 2:
                        break;
                    case 3:
                        tillPrecioMaximo.setError(null);
                        break;
                    case 4:
                        tillFechaFin.setError(null);
                        break;
                    case 5:
                        monPago.setError(null);
                        break;
                }
            }
        }

        showMensaje(msg);
    }

    private void agregarMsg(String cadena) {
        if (msg.length() > 0) {
            msg= msg + "\n- " +cadena;
        }
        else {
            msg= "- " +cadena;
        }
    }

    @Override
    public void showMensaje(String msj) {
        Toast.makeText(this, msj,Toast.LENGTH_LONG).show();
    }

}
