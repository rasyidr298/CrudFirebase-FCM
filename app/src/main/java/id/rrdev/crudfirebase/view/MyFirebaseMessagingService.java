package id.rrdev.crudfirebase.view;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import id.rrdev.crudfirebase.util.NotificationUtil;


public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.d(TAG," RefreshToken "+token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, " From "+remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0){
            Log.d(TAG," Data Payload "+remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null){
            String body = remoteMessage.getNotification().getBody();
            String title = remoteMessage.getNotification().getTitle();
            Log.d(TAG, "Message Body "+body);
            Log.d(TAG, "Message Title "+title);

            NotificationUtil.createNotification(title, body, this);
        }
    }
}
