package cl.ejercicios.listadecomprasversion2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cl.ejercicios.listadecomprasversion2.modelo.ComprasDatabaseHelper;
import cl.ejercicios.listadecomprasversion2.modelo.ListDeCompras;
import cl.ejercicios.listadecomprasversion2.modelo.Producto;


public class ListaProductosActivity extends ListActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        cargarLista();
    }

    public void cargarLista()
    {
        lista=getListView();

        //List<Producto> productoList= ListDeCompras.getInstancia().getListaDeCompras();
        ComprasDatabaseHelper helper=new ComprasDatabaseHelper(this);
        List<Producto> productoList=helper.listaProductos();


        ArrayAdapter<Producto> listaAdapter= new ArrayAdapter<Producto>(this,
                android.R.layout.simple_expandable_list_item_1,productoList);

        lista.setAdapter(listaAdapter);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int posicion, long id)
    {
        //obener el nombre del producto
        Object o=lista.getItemAtPosition(posicion);
        String linea=o.toString();
        String[] separar=linea.split(":");

        Intent intent=new Intent(ListaProductosActivity.this,DetallesActivity.class);
        intent.putExtra("nombreProducto", separar[0]);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                cargarLista();
            }
        }
    }
}
