package id.rrdev.crudfirebase.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.rrdev.crudfirebase.data.NoteRespository;
import id.rrdev.crudfirebase.data.model.Note;

public class MainViewModel extends AndroidViewModel {

    private NoteRespository noteRespository;
    private LiveData<List<Note>> getNoteLiveData;


    public MainViewModel(@NonNull Application application) {
        super(application);

        noteRespository = new NoteRespository();
        this.getNoteLiveData = noteRespository.getAllNote();
    }

    public LiveData<List<Note>> getNoteLiveData() {
        this.getNoteLiveData = noteRespository.getAllNote();
        return getNoteLiveData;
    }

    public void addNote(Note note){
        noteRespository.addNote(note);
    }

    public void deleteNote(String id){
        noteRespository.deleteNote(id);
    }

    public void update(Note note){
        noteRespository.updateNote(note);
    }
}
