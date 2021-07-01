package id.rrdev.crudfirebase.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import id.rrdev.crudfirebase.data.model.Note;

public class NoteRespository {
    private static final String TAG = NoteRespository.class.getSimpleName();
    private FirebaseFirestore firebaseFirestore;

    public NoteRespository(){
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public LiveData<List<Note>> getAllNote(){
        final MutableLiveData<List<Note>> data = new MutableLiveData<>();
        firebaseFirestore.collection("notes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List notesList = new ArrayList<>();
                            for (DocumentSnapshot doc : task.getResult()) {
                                Note note = doc.toObject(Note.class);
                                note.setId(doc.getId());
                                notesList.add(note);
                            }
                            data.setValue(notesList);

                            Log.d(TAG, "onSuccess: " + data.toString());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure get data: " + e.getMessage());
                    }
                });

        return data;
    }

    public void addNote(Note note) {

        firebaseFirestore.collection("notes")
                .add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG,"onSuccess add note");
                        firebaseFirestore.collection("notes")
                                .document(documentReference.getId())
                                .update("id", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure add note: " + e.getMessage());
                    }
                });
    }

    public void deleteNote(String id){
        firebaseFirestore.collection("notes")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG,"onSuccess delete");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"onFailure delete");
                    }
                });
    }

    public void updateNote(Note note){

        firebaseFirestore.collection("notes")
                .document(note.getId())
                .set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"onSuccess update");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG,"onFailure update");
                    }
                });
    }

}
