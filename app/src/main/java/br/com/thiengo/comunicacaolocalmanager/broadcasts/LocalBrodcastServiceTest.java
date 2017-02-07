package br.com.thiengo.comunicacaolocalmanager.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import br.com.thiengo.comunicacaolocalmanager.services.ServiceTest;


public class LocalBrodcastServiceTest extends BroadcastReceiver {
    private ServiceTest service;

    public LocalBrodcastServiceTest( ServiceTest service ){
        this.service = service;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra( ServiceTest.MENSAGEM_KEY );
        service.logMensagem( mensagem );
    }
}
