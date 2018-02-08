package com.olimpiait.seccion5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    List<String> names = new ArrayList<String>();
    GridView gridView;
    MyAdapter myadapter;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = (GridView) findViewById(R.id.gridView);
        names.add("Andres");
        names.add("Carlos");
        names.add("Juan");
        names.add("Pedro");

        //listView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GridViewActivity .this,"se ha presionado el item "+parent.getAdapter().getItem(position).toString(),Toast.LENGTH_SHORT).show();
            }
        });


        // Enlace con el adaptador personalizado
        myadapter = new MyAdapter(this,R.layout.list_item,names);
        gridView.setAdapter(myadapter);

        registerForContextMenu(gridView);
    }

    /**
     * Metodos para el menu del navigation bar
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                this.names.add("Added nº"+(++counter));
                //Notifica al adaptador que se actualizaron los datos
                this.myadapter.notifyDataSetChanged();
                return true;
            case R.id.remove_item:
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    /**
     * Metodos para el menu contextual
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.names.get(info.position));
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_item:
                this.names.remove(info.position);
                myadapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
    }
}
