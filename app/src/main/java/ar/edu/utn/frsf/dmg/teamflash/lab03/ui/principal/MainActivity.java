package ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ar.edu.utn.frsf.dmg.teamflash.lab03.R;
import ar.edu.utn.frsf.dmg.teamflash.lab03.model.Trabajo;
import ar.edu.utn.frsf.dmg.teamflash.lab03.ui.principal.adapter.OfertaLaboralBaseAdapter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private Toolbar toolbar;
    private IMainPresenter presenter;
    private OfertaLaboralBaseAdapter adapter;
    private ListView listView_ofertas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView_ofertas= (ListView) findViewById(R.id.listView_ofertas);


        presenter= new MainPresenter(this);

        presenter.showListOfertas();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapter(List<Trabajo> listaOferta) {
        adapter= new OfertaLaboralBaseAdapter(this, listaOferta);

        adapter.setOnItemLongClickListener(new OfertaLaboralBaseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(Trabajo trabajo) {
                presenter.showToastTrabajo(trabajo);
            }
        });

        listView_ofertas.setAdapter(adapter);


        /*listView_ofertas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {

                presenter.showToastTrabajo((Trabajo)adapter.getItem(pos));

                return true;
            }
        });*/

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
}
