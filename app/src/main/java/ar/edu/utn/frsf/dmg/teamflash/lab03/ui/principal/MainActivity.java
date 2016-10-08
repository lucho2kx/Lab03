package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.R;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;
import ar.edu.utn.frsf.dmg.teamflash.lab03.ui.oferta.OfertaActivity;
import ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal.adapter.OfertaLaboralBaseAdapter;

public class MainActivity extends AppCompatActivity implements IMainView {


    static final String STATE_LISTA = "lista";
    private int request_code = 1;
    private Toolbar toolbar;
    private IMainPresenter presenter;
    private OfertaLaboralBaseAdapter adapter;
    private ArrayList<Trabajo> lista= null;
    private ListView listView_ofertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView_ofertas= (ListView) findViewById(R.id.listView_ofertas);

        if (savedInstanceState != null) {
            lista= savedInstanceState.getParcelableArrayList(STATE_LISTA);
        }


        presenter= new MainPresenter(this);

        presenter.showListOfertas();

        registerForContextMenu(listView_ofertas);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (lista != null) {
            outState.putParcelableArrayList(STATE_LISTA, lista);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle estadoGuardado){
        super.onRestoreInstanceState(estadoGuardado);
        if (estadoGuardado != null && lista != null) {
            lista= estadoGuardado.getParcelableArrayList(STATE_LISTA);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle(this.getResources().getString(R.string.menu_titulo));
        inflater.inflate(R.menu.menu_acciones, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_postularse_oferta:
               presenter.postularseOferta((Trabajo)adapter.getItem(info.position));
                return true;
            case R.id.menu_compartir_oferta:
                presenter.compartirOferta((Trabajo)adapter.getItem(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_crear_oferta:
                presenter.navigateToCrearOferta();
                break;
            case R.id.action_configuracion:
                presenter.navigateToConfiguracion();
                break;
            case R.id.action_ver_mis_ofertas:
                presenter.navigateToVerMisOfertas();
                break;
            case R.id.action_salir:
                presenter.navigateToSalir();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapter(ArrayList<Trabajo> listaOferta) {

        if (lista == null) {
            lista= listaOferta;
            adapter= new OfertaLaboralBaseAdapter(this, listaOferta);
        }
        else {
            adapter= new OfertaLaboralBaseAdapter(this, lista);
        }


        /*adapter.setOnItemLongClickListener(new OfertaLaboralBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Trabajo trabajo) {
                presenter.showToastTrabajo(trabajo);
            }
        });*/

        listView_ofertas.setAdapter(adapter);
    }

    @Override
    public void showListViewOfertas() {
        listView_ofertas.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMensaje(String msj) {
        Toast.makeText(this, msj,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToCrearOfertaActivity() {
        Intent intent = new Intent(this, OfertaActivity.class);
        startActivityForResult(intent, request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == request_code) && (resultCode == RESULT_OK)) {
            Trabajo trabajo= data.getExtras().getParcelable(OfertaActivity.TRABAJO_RETURN);

            presenter.addOfertaLaboral(trabajo);
            // Se obtiene el mensaje de retorno de la actividad
            // MensajeRetorno msj= data.getExtras().getParcelable("mensaje");
            // mainPresenter.showMessage(msj);
        }
    }

    @Override
    public ArrayList<Trabajo> getLista() {
        return lista;
    }
}
