package com.example.andresvelasquez.seccion_7_realm.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andresvelasquez.seccion_7_realm.R;
import com.example.andresvelasquez.seccion_7_realm.adapters.BoardAdapter;
import com.example.andresvelasquez.seccion_7_realm.models.Board;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
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

        boardAdapter = new BoardAdapter(this,results,R.layout.list_view_board_item);
        listView = (ListView) findViewById(R.id.listViewBoard);
        listView.setAdapter(boardAdapter);
        fab = (FloatingActionButton) findViewById(R.id.fabAddBoard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForCreatingBoard("Add new board","Add title for board");
                boardAdapter.notifyDataSetChanged();
            }
        });
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
}
