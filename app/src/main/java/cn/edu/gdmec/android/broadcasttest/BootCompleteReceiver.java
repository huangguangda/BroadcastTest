package cn.edu.gdmec.android.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Jack on 2017/12/11.
 */

public class BootCompleteReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText ( context, "Boot Complete", Toast.LENGTH_LONG ).show ();
    }
}
