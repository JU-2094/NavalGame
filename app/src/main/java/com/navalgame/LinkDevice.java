package com.navalgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import static com.navalgame.BluetoothUtil.MESSAGE_READ;
import static com.navalgame.BluetoothUtil.MESSAGE_WRITE;

public class LinkDevice extends Activity {

    private StringBuffer mOutStringBuffer;
    private BluetoothUtil bluetoothManager;
    private BluetoothAdapter mBluetoothAdapter = null;
    Button btnBegin, btnsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_device);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mOutStringBuffer = new StringBuffer("");
        bluetoothManager = new BluetoothUtil(mHandler);
        btnBegin= (Button)findViewById(R.id.btn_begin);
        btnsend= (Button)findViewById(R.id.btn_send);

        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bluetoothManager.enableBluetooth();

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        LinkDevice.this,android.R.layout.select_dialog_singlechoice
                );

                for(String name : bluetoothManager.getPaired())
                    adapter.add(name);

                AlertDialog.Builder dialog = new AlertDialog.Builder(LinkDevice.this);
                dialog.setTitle("Selecciona el dispositivo");


                dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String devName = adapter.getItem(which);

                        Log.e("SELECT",devName);

                        bluetoothManager.connect(bluetoothManager.getDevice(devName));


                    }
                });

                dialog.show();
            }
        });


        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("MENSAJE!");
            }
        });


    }

    private void sendMessage(String message) {
        // Check that we're actually connected before trying anything
        if (bluetoothManager.getState() != BluetoothUtil.STATE_CONNECTED) {
            Toast.makeText(this, "not connected", Toast.LENGTH_SHORT).show();
            return;
        }
        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
            bluetoothManager.write(send);
            // Reset out string buffer to zero and clear the edit text field
            mOutStringBuffer.setLength(0);

            Log.e("ENVIADO",mOutStringBuffer.toString());

        }
    }

    // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            /**
             * See the messages... not done 'cause time
             */
            switch (msg.what) {
                case MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    Log.e("Handler", "Write: " + writeMessage);
                    break;

                case MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    String readMessage = new String(readBuf, 0, msg.arg1);
                    Log.e("Handler","Read: "+readMessage);
                    break;
            }
        }
    };
}
