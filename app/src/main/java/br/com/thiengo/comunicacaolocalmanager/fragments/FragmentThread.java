package br.com.thiengo.comunicacaolocalmanager.fragments;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import br.com.thiengo.comunicacaolocalmanager.broadcasts.LocalBrodcastFragment;
import br.com.thiengo.comunicacaolocalmanager.domain.ClasseDominio;


public class FragmentThread extends Fragment {
    public static final String KEY = "FragmentThread";
    public static final String FILTRO_KEY = "FragmentThread_KEY";
    public static final String MENSAGEM_KEY = "FragmentThread_MENSAGEM_KEY";

    private LocalBrodcastFragment broadcast;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        broadcast = new LocalBrodcastFragment(this);
        IntentFilter intentFilter = new IntentFilter( FILTRO_KEY );
        LocalBroadcastManager.getInstance( getActivity() ).registerReceiver( broadcast, intentFilter );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance( getActivity() ).unregisterReceiver( broadcast );
    }

    public void logMensagem(String mensagem ){
        final String m = mensagem + "ClasseDominio: mensagem ok.<br>";

        new Thread(){
            @Override
            public void run() {
                super.run();
                Intent intent = new Intent(ClasseDominio.FILTRO_KEY);
                intent.putExtra( ClasseDominio.MENSAGEM_KEY, m );
                LocalBroadcastManager.getInstance( getActivity() ).sendBroadcast( intent );
            }
        }.start();
    }
}
