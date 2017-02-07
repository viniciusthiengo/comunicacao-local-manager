package br.com.thiengo.comunicacaolocalmanager.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import br.com.thiengo.comunicacaolocalmanager.broadcasts.LocalBroadcastServiceTest;
import br.com.thiengo.comunicacaolocalmanager.fragments.FragmentThread;


public class ServiceTest extends Service {
    public static final String FILTRO_KEY = "ServiceTest_KEY";
    public static final String MENSAGEM_KEY = "ServiceTest_MENSAGEM_KEY";

    private LocalBroadcastServiceTest broadcast;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        broadcast = new LocalBroadcastServiceTest(this);
        IntentFilter intentFilter = new IntentFilter( FILTRO_KEY );
        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver( broadcast, intentFilter );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver( broadcast );
    }

    public void logMensagem(String mensagem ){
        mensagem += "FragmentThread: mensagem ok.<br>";

        Intent intent = new Intent(FragmentThread.FILTRO_KEY);
        intent.putExtra(FragmentThread.MENSAGEM_KEY, mensagem);
        LocalBroadcastManager
                .getInstance(this)
                .sendBroadcast( intent );
    }
}
