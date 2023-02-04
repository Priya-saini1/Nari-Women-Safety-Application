package com.example.nari_app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.nari_app.Contact.ContactModel;
import com.example.nari_app.Contact.DbHelper;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;
public class GeofenceTransitionService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Menu destination=new Menu();
        Toast.makeText(context, "Geofence Trigger", Toast.LENGTH_SHORT).show();
        GeofencingEvent geofencingEvent= GeofencingEvent.fromIntent((intent));
        if(geofencingEvent.hasError())
        {
            String error=String.valueOf(geofencingEvent.getErrorCode());
            Log.w("TAG",error);
            Toast.makeText(context, "error code="+error, Toast.LENGTH_SHORT).show();
            return ;
        }
        List<Geofence> geofenceList= geofencingEvent.getTriggeringGeofences();
        for(Geofence geofence:geofenceList)
        {
            Log.d("tag","onRecevice"+geofence.getRequestId());
        }
        //Location location=geofencingEvent.getTriggeringLocation();
        int transitiontype = geofencingEvent.getGeofenceTransition();
        switch (transitiontype)
        {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "GEOFENCE_TRANSITION_ENTER", Toast.LENGTH_SHORT).show();
                SmsManager smsManager = SmsManager.getDefault();
                DbHelper db = new DbHelper(context);
                List<ContactModel> list = db.getAllContacts();
                for (ContactModel c : list) {
                    String message = "Hey, " + c.getName() + "I Reached My Destination Safely...." ;
                    smsManager.sendTextMessage(c.getPhoneNo(), null, message, null, null);
                }
                break;
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "GEOFENCE_TRANSITION_DWELL", Toast.LENGTH_SHORT).show();
                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "GEOFENCE_TRANSITION_EXIT", Toast.LENGTH_SHORT).show();
                break;

        }

     }


    }


