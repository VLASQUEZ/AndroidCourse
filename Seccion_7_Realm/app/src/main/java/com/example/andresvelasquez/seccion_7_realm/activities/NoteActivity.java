package com.example.andresvelasquez.seccion_7_realm.activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andresvelasquez.seccion_7_realm.R;
import com.example.andresvelasquez.seccion_7_realm.models.Board;
import com.example.andresvelasquez.seccion_7_realm.models.Note;
import com.example.andresvelasquez.seccion_7_realm.adapters.NoteAdapter;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class NoteActivity extends AppCompatActivity implements RealmChangeListener<Board> {
    private ListView listView;
    private FloatingActionButton fab;

    private NoteAdapter adapter;
    private RealmList<Note> notes;
    private Realm realm;
    private int boardId;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        realm = Realm.getDefaultInstance();

        if(getIntent().getExtras() != null){
            boardId = getIntent().getExtras().getInt("id");
        }

        board = realm.where(Board.class).equalTo("id",boardId).findFirst();

        notes = board.getNotes();

        this.setTitle(board.getTitle());

        fab = (FloatingActionButton) findViewById(R.id.fabAddNote);
        listView = (ListView) findViewById(R.id.listViewNote);

        adapter = new NoteAdapter(this,notes,R.layout.list_view_note_item);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForCreatingNote("Add new note","Type a note Name for "+board.getTitle());
            }
        });
    }
    /**
     * CRUD Actions
     * */
    private void createNewNote(final String noteName){
        realm.beginTransaction();
        Note note = new Note(noteName);
        realm.copyToRealm(note);
        board.getNotes().add(note);
        realm.commitTransaction();
    }
    private void deleteNote(final Note note){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                note.deleteFromRealm();
            }
        });
    }
    private void editNote(final String noteName, final Note note){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                note.setDescription(noteName);
                realm.copyToRealmOrUpdate(board);
            }
        });
    }
    private void deleteAllNotes(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                //Elimina todas las notas pertenecientes a esa board;
                board.getNotes().deleteAllFromRealm();
            }
        });

    }

    private void showAlertForCreatingNote(String title, String message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(title != null) builder.setTitle(title);
        if(message != null) builder.setMessage(message);
        //Se infla el layout dentro de un objeto view
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_board,null);
        //se agrega el objeto view en el alertBuilder
        builder.setView(viewInflated);

        final EditText input = (EditText)viewInflated.findViewById(R.id.addBoard);
        input.setHint("Note title");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String noteName = input.getText().toString().trim();
                if (noteName.length() > 0)
                {
                    createNewNote(noteName);
                }
                else
                {
                    Toast.makeText(NoteActivity.this,"The note name is required",Toast.LENGTH_LONG).show();
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

    @Override
    public void onChange(Board board) {
        adapter.notifyDataSetChanged();
    }
}
