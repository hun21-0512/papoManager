package papo.hun.papomanager.Func

import android.app.Activity
import android.content.IntentSender
import android.util.Log
import android.view.*
import com.google.android.gms.common.api.ResolvableApiException

import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task

class FuncT {
    var mView:View
    var mActivity:Activity

    constructor(mActivity: Activity, mView: View) {
        this.mView = mView
        this.mActivity = mActivity
    }

    fun turnOnGPS() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(mActivity)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            // GPS가 켜져있을 경우
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(mActivity,100)
                } catch (sendEx: IntentSender.SendIntentException) {
                    Log.d("var1234", sendEx.message.toString())
                }
            }
        }
    }
}