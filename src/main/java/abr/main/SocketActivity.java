package abr.main;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import abr.main.Client;
import abr.main.R;
import abr.main.Server;

public class SocketActivity extends Activity {

    TextView response;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear;
    Server server;
    TextView infoip, msg;
    String phoneIpAddress = "169.234." + "17.229";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);


        // ADDED FROM SERVER
        infoip = (TextView) findViewById(R.id.infoip);
        msg = (TextView) findViewById(R.id.msg);
        server = new Server(this);
        infoip.setText(server.getIpAddress()+":"+server.getPort());

        buttonConnect = (Button) findViewById(R.id.connectButton);
        response = (TextView) findViewById(R.id.responseTextView);

        buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Client myClient = new Client(phoneIpAddress,8080,response);
                myClient.execute();
            }
        });


        Button btn = (Button)findViewById(R.id.open_activity_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SocketActivity.this, Main_activity.class));
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        server.onDestroy();
    }

}
