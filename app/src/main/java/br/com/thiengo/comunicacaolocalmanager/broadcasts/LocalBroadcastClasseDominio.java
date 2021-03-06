package br.com.thiengo.comunicacaolocalmanager.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.thiengo.comunicacaolocalmanager.domain.ClasseDominio;


public class LocalBroadcastClasseDominio extends BroadcastReceiver {
    private ClasseDominio dominio;

    public LocalBroadcastClasseDominio(ClasseDominio dominio ){
        this.dominio = dominio;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra( ClasseDominio.MENSAGEM_KEY );
        dominio.logMensagem( mensagem );
    }
}
