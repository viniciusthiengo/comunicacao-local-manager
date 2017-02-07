package br.com.thiengo.comunicacaolocalmanager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import br.com.thiengo.comunicacaolocalmanager.broadcasts.LocalBroadcastMainActivity;
import br.com.thiengo.comunicacaolocalmanager.domain.ClasseDominio;
import br.com.thiengo.comunicacaolocalmanager.fragments.FragmentThread;
import br.com.thiengo.comunicacaolocalmanager.services.ServiceTest;


public class MainActivity extends AppCompatActivity {
    public static final String FILTRO_KEY = "MainActivity_KEY";
    public static final String MENSAGEM_KEY = "MainActivity_MENSAGEM_KEY";

    private LocalBroadcastMainActivity broadcast;
    private ClasseDominio dominio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* SERVICE */
        Intent intent = new Intent(this, ServiceTest.class);
        startService( intent );

        /* FRAGMENT */
        if( savedInstanceState == null ){
            FragmentThread fragment = new FragmentThread();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add( fragment, FragmentThread.KEY );
            ft.commit();
        }

        /* DOMAIN */
        dominio = new ClasseDominio(this);

        /* INTENT FILTER */
        broadcast = new LocalBroadcastMainActivity(this);
        IntentFilter intentFilter = new IntentFilter( FILTRO_KEY );
        LocalBroadcastManager
                .getInstance(this)
                .registerReceiver( broadcast, intentFilter );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dominio.destroy();

        LocalBroadcastManager
                .getInstance(this)
                .unregisterReceiver( broadcast );

        Intent intent = new Intent(this, ServiceTest.class);
        stopService( intent );
    }

    public void cicloMensagem(View view){
        Intent intent = new Intent(ServiceTest.FILTRO_KEY);
        intent.putExtra( ServiceTest.MENSAGEM_KEY, "ServiceTest: mensagem ok.<br>" );
        LocalBroadcastManager
                .getInstance(this)
                .sendBroadcast(intent);
    }

    public void logMensagem(String mensagem ){
        TextView tvConteudo = (TextView) findViewById(R.id.tv_conteudo);
        tvConteudo.setText( Html.fromHtml(mensagem) );
    }
}
