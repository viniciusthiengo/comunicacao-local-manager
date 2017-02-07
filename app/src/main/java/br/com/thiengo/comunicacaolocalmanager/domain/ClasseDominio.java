package br.com.thiengo.comunicacaolocalmanager.domain;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import br.com.thiengo.comunicacaolocalmanager.MainActivity;
import br.com.thiengo.comunicacaolocalmanager.broadcasts.LocalBrodcastClasseDominio;

/**
 * Created by viniciusthiengo on 07/02/17.
 */

public class ClasseDominio {
    public static final String FILTRO_KEY = "ClasseDominio_KEY";
    public static final String MENSAGEM_KEY = "ClasseDominio_MENSAGEM_KEY";

    private Context context;
    private LocalBrodcastClasseDominio broadcast;


    public ClasseDominio( Context context ){
        this.context = context;

        broadcast = new LocalBrodcastClasseDominio(this);
        IntentFilter intentFilter = new IntentFilter( FILTRO_KEY );
        LocalBroadcastManager.getInstance(context).registerReceiver( broadcast, intentFilter );
    }

    public void logMensagem( String mensagem ){
        mensagem += "MainActivity: mensagem ok.<br>";

        Intent intent = new Intent(MainActivity.FILTRO_KEY);
        intent.putExtra(MainActivity.MENSAGEM_KEY, mensagem);
        LocalBroadcastManager.getInstance(context).sendBroadcast( intent );
    }

    public void destroy(){
        LocalBroadcastManager.getInstance(context).unregisterReceiver( broadcast );
    }
}
