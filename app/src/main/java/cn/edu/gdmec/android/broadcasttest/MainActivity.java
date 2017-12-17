package cn.edu.gdmec.android.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManageer;

    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        localBroadcastManageer = LocalBroadcastManager.getInstance ( this );

        Button button = ( Button ) findViewById ( R.id.button );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( "cn.edu.gdmec.android.broadcasttest.LOCAL_BROADCAST" );
                localBroadcastManageer.sendBroadcast ( intent );
                /*  Intent intent = new Intent ( "cn.edu.gdmec.android.broadcasttest.MY_BROADCAST" );
                sendOrderedBroadcast ( intent, null );
                sendBroadcast ( intent );*/
            }
        } );

        intentFilter = new IntentFilter (  );
        intentFilter.addAction ( "cn.edu.gdmec.android.broadcasttest.LOCAL_BROADCAST" );
        localReceiver = new LocalReceiver();
        localBroadcastManageer.registerReceiver ( localReceiver, intentFilter );
    /*    intentFilter.addAction ( "android.net.conn.CONNECTIVITY_CHANGE" );
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver ( networkChangeReceiver, intentFilter );*/
    }
    @Override
    protected void onDestroy(){
        super.onDestroy ();
        //unregisterReceiver ( networkChangeReceiver );
        localBroadcastManageer.unregisterReceiver ( localReceiver );
    }

    class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText ( context, "received local broadcast", Toast.LENGTH_SHORT ).show ();
        }
    }
    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService ( Context.CONNECTIVITY_SERVICE );
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo ();
            Toast.makeText ( context, "network changes", Toast.LENGTH_SHORT ).show ();

            if (networkInfo != null && networkInfo.isAvailable ()){
                Toast.makeText ( context, "network is available", Toast.LENGTH_SHORT ).show ();
            }else{
                Toast.makeText ( context, "network is unavailable", Toast.LENGTH_SHORT ).show ();
            }
        }
    }
}
