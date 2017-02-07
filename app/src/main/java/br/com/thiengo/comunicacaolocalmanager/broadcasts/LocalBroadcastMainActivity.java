package br.com.thiengo.comunicacaolocalmanager.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import br.com.thiengo.comunicacaolocalmanager.MainActivity;


public class LocalBroadcastMainActivity extends BroadcastReceiver {
    private MainActivity activity;

    public LocalBroadcastMainActivity(MainActivity activity ){
        this.activity = activity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra( MainActivity.MENSAGEM_KEY );
        activity.logMensagem( mensagem );
    }
}
