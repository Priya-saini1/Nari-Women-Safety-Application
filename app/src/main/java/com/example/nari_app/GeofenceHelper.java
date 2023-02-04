package com.example.nari_app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.LatLng;

public class GeofenceHelper extends ContextWrapper {
    PendingIntent pendingIntent;
    public GeofenceHelper(Context base) {
        super(base);
    }
    public GeofencingRequest getGeofencingRequest(Geofence geofence)
    {
        return new GeofencingRequest.Builder()
          .addGeofence(geofence)
          .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
          .build();
    }
    public Geofence getGeofence(String ID, LatLng latng,float radius,int transitionTypes)
    {
        return new Geofence.Builder()
                .setRequestId("My Geofence ")
                .setCircularRegion(latng.latitude,latng.longitude,radius)
                .setRequestId(ID)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(transitionTypes )
                .setLoiteringDelay(5000)
                .build();
    }
    public PendingIntent getPendingIntent()
    {
        if(pendingIntent!=null)
        {
            return pendingIntent;
        }
        Intent intent=new Intent(this ,GeofenceTransitionService.class);
        pendingIntent = PendingIntent.getBroadcast(this,2607,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        return pendingIntent;
    }
    public String getErrorString(Exception e)
    {
        if (e instanceof ApiException)
        {
            ApiException apiException=(ApiException) e;
            switch (apiException.getStatusCode())
            {
                case GeofenceStatusCodes
                        .GEOFENCE_NOT_AVAILABLE:
                    return "Geofence Not Availabe";
                case GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_GEOFENCES:
                    return "Too many Geofence";
                case GeofenceStatusCodes
                        .GEOFENCE_TOO_MANY_PENDING_INTENTS:
                    return " Geofence Too many pending intent";
            }
        }
        return e.getLocalizedMessage();
    }


}
