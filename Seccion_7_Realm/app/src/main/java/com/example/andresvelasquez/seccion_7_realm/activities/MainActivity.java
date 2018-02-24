package com.example.andresvelasquez.seccion_7_realm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andresvelasquez.seccion_7_realm.R;
import com.example.andresvelasquez.seccion_7_realm.adapters.BoardAdapter;
import com.example.andresvelasquez.seccion_7_realm.models.Board;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private FloatingActionButton fab;
    private Realm realm;
    private ListView listView;
    private BoardAdapter boardAdapter;
    private RealmResults<Board> results;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        results = realm.where(Board.class).findAll();
        results.addChangeListener(new RealmChangeListener<RealmResults<Board>>() {
            @Override
            public void onChange(RealmResults<Board> boards) {
                boardAdapter.notifyDataSetChanged();
            }
        });
        boardAdapter = new BoardAdapter(this,results,R.layout.list_view_board_item);
        listView = (ListView) findViewById(R.id.listViewBoard);
        listView.setAdapter(boardAdapter);
        listView.setOnItemClickListener(this);
        fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForCreatingBoard("Add new board","Add title for board");
                //boardAdapter.notifyDataSetChanged();
            }
        });
        registerForContextMenu(listView);
    }
    /**
     * CRUD Actions
     * */
    private void createNewBoard(final String boardName){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Board board = new Board(boardName);
                realm.copyToRealm(board);
            }
        });
    }
    private void deleteBoard(final Board board){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                board.deleteFromRealm();
            }
        });
    }
    private void editBoard(final String boardName, final Board board){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                board.setTitle(boardName);
                realm.copyToRealmOrUpdate(board);
            }
        });
    }
    /**
     * Dialogs
     * */
    private void showAlertForCreatingBoard(String title,String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);
        //Se infla el layout dentro de un objeto view
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);
        //se agrega el objeto view en el alertBuilder
        builder.setView(viewInflated);

        final EditText input = (EditText)viewInflated.findViewById(R.id.addBoard);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String boardName = input.getText().toString().trim();
                if (boardName.length() > 0)
                {
                    createNewBoard(boardName);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"The board name is required",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }
    private void showAlertForEditingBoard(String title,String message,final Board board){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);
        //Se infla el layout dentro de un objeto view
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);
        //se agrega el objeto view en el alertBuilder
        builder.setView(viewInflated);

        final EditText input = (EditText)viewInflated.findViewById(R.id.addBoard);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String boardName = input.getText().toString().trim();
                if(boardName.length() == 0){
                    Toast.makeText(MainActivity.this,"The board name is required",Toast.LENGTH_LONG).show();
                }
                else if(boardName.equals(board.getTitle())){
                    Toast.makeText(MainActivity.this,"The board name is the same as the old board",Toast.LENGTH_LONG).show();
                }
                else{
                    editBoard(boardName,board);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }
    /**
     * Event item click del ListView
     * */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(MainActivity.this, NoteActivity.class);
        i.putExtra("id",results.get(position).getId());
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_board:
                deleteBoard(results.get(info.position));
                return true;
            case R.id.edit_board:
                showAlertForEditingBoard("Edit board","Set a new name for the board",results.get(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(results.get(info.position).getTitle());
        getMenuInflater().inflate(R.menu.context_menu_board,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.deleteAll();
                    }
                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
