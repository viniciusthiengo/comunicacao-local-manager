package br.com.thiengo.comunicacaolocalmanager.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.thiengo.comunicacaolocalmanager.fragments.FragmentThread;


public class LocalBroadcastFragment extends BroadcastReceiver {
    private FragmentThread fragment;

    public LocalBroadcastFragment(FragmentThread fragment ){
        this.fragment = fragment;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra( FragmentThread.MENSAGEM_KEY );
        fragment.logMensagem( mensagem );
    }
}
