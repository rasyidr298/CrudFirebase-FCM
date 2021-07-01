package id.rrdev.crudfirebase.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import id.rrdev.crudfirebase.R;
import id.rrdev.crudfirebase.data.model.Note;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupView();
    }

    private void init(){
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void setupView(){

        //get note
        mainViewModel.getNoteLiveData().observe(this, note -> {
            if (note != null){
                Log.d(TAG, "size: "+note.size());
                Log.d(TAG, note.toString());
            }
        });

        //add note
        Note note = new Note(null,"title","deskripsi");
        mainViewModel.addNote(note);

        //delete note
//        mainViewModel.deleteNote("7nzfI5PatG4rwGMWEDXA");

        //update note
//        Note note = new Note("Dkf7e9s2EVTHHGqaeD2u","firestore","ini firestore");
//        mainViewModel.update(note);


    }
}