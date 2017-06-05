package com.navalgame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import static com.navalgame.BluetoothUtil.MESSAGE_READ;
import static com.navalgame.BluetoothUtil.MESSAGE_WRITE;

public class Game extends Activity {

    View gridView;
    Button btnBegin;
    ImageView boatL,boatM,boatS1,boatS2,boatU1,boatU2,boatU3;
    MediaPlayer mp;

    final int CONST_GRID = 10;

    private StringBuffer mOutStringBuffer;
    private BluetoothUtil bluetoothManager;
    private BluetoothAdapter mBluetoothAdapter = null;
    public boolean isConnected = false;

    int [] p_boatL,p_boatM,p_boatS1,p_boatS2,p_boatU1,p_boatU2,p_boatU3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);


        gridView = findViewById(R.id.grid_view);
        btnBegin = (Button) findViewById(R.id.btn_begin);
        mp = MediaPlayer.create(this,R.raw.snapper);

        boatL = (ImageView)findViewById(R.id.larger_boat);
        boatM = (ImageView)findViewById(R.id.medium_boat);
        boatS1 = (ImageView)findViewById(R.id.small_boat1);
        boatS2 = (ImageView)findViewById(R.id.small_boat2);
        boatU1 = (ImageView)findViewById(R.id.unit_boat1);
        boatU2 = (ImageView)findViewById(R.id.unit_boat2);
        boatU3 = (ImageView)findViewById(R.id.unit_boat3);

        p_boatL = new int[2];
        p_boatM = new int[2];
        p_boatS1 = new int[2];
        p_boatS2 = new int[2];
        p_boatU1 = new int[2];
        p_boatU2 = new int[2];
        p_boatU3 = new int[2];

        boatL.setTag("boatL");
        boatL.setOnTouchListener(new ListenerOnTouch());

        boatM.setTag("boatM");
        boatM.setOnTouchListener(new ListenerOnTouch());

        boatS1.setTag("boatS");
        boatS1.setOnTouchListener(new ListenerOnTouch());

        boatS2.setTag("boatS");
        boatS2.setOnTouchListener(new ListenerOnTouch());

        boatU1.setTag("boatU");
        boatU1.setOnTouchListener(new ListenerOnTouch());

        boatU2.setTag("boatU");
        boatU2.setOnTouchListener(new ListenerOnTouch());

        boatU3.setTag("boatU");
        boatU3.setOnTouchListener(new ListenerOnTouch());


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mOutStringBuffer = new StringBuffer("");
        bluetoothManager = new BluetoothUtil(mHandler);


        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                bluetoothManager.enableBluetooth();

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        Game.this,android.R.layout.select_dialog_singlechoice
                );

                for(String name : bluetoothManager.getPaired())
                    adapter.add(name);

                AlertDialog.Builder dialog = new AlertDialog.Builder(Game.this);
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

                        new Connect().execute();
                    }
                });

                dialog.show();


            }
        });


        YoYo.with(Techniques.FadeIn)
                .duration(2000)
                .playOn(gridView);
        YoYo.with(Techniques.Landing)
                .duration(2000)
                .playOn(btnBegin);

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

                    if(readMessage.equals("Game"))
                        isConnected=true;
                    break;
            }
        }
    };

    private class Connect extends AsyncTask<Void,Void,Void>{

        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
//            super.onPreExecute();
            progressDialog = new ProgressDialog(Game.this);
            progressDialog.setMessage("Connectando");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            while(!isConnected){
                sendMessage("Game");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Intent intent = new Intent(Game.this,PlayGame.class);


            intent.putExtra("boatL",p_boatL);
            intent.putExtra("boatM",p_boatM);
            intent.putExtra("boatS1",p_boatS1);
            intent.putExtra("boatS2",p_boatS2);
            intent.putExtra("boatU1",p_boatU1);
            intent.putExtra("boatU2",p_boatU2);
            intent.putExtra("boatU3",p_boatU3);

            startActivity(intent);
        }

    }

    private class ListenerOnTouch implements View.OnTouchListener{
        float dx,dy;
        boolean isclick;



        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isclick=true;
                    dx = v.getX() - event.getRawX();
                    dy = v.getY() - event.getRawY();

                    Log.e("----","--------------");
                    Log.e("POSITION X,Y, dx, dy",v.getX()+","+v.getY()+","+dx+","+dy);
                    Log.e("Rotation",v.getRotation()+"");

                    break;
                case MotionEvent.ACTION_UP:
                    if(isclick){
                        if(v.getRotation()>=360)
                            v.setRotation(90);
                        else
                            v.setRotation(v.getRotation()+90);
                    }
                    v.animate()
                            .x(v.getX())
                            .y(getposition(v.getY(),1,v.getTag().toString(),v))
                            .setDuration(0)
                            .start();
                    adjust(v,event.getRawX()+dx,event.getRawY()+dy);
                    break;

                case MotionEvent.ACTION_MOVE:
                    isclick=false;

                    v.animate()
                            .x(getposition(event.getRawX()+dx,0,v.getTag().toString(),v))
                            .y(event.getRawY()+dy)
                            .setDuration(0)
                            .start();

                    break;
                default:
                    return false;
            }
            return true;
        }

        private float getposition(float val,int type,String tag,View v){

            float res=0;

            float xUnit = gridView.getWidth()/CONST_GRID;
            float yUnit = gridView.getHeight()/CONST_GRID;

            switch (type){
                case 0:
                    int indexX = (int) ((val+xUnit*0.4)/xUnit);
                    if (indexX>9)
                        indexX=9;
                    if (indexX<0)
                        indexX=0;
                    res = indexX*xUnit;


                    if(v.equals(findViewById(R.id.small_boat1))){
                        p_boatS1[0]=indexX;
                    }else if(v.equals(findViewById(R.id.small_boat2))){
                        p_boatS2[0]=indexX;
                    }else if(v.equals(findViewById(R.id.unit_boat1))){
                        p_boatU1[0]=indexX;
                    }else if(v.equals(findViewById(R.id.unit_boat2))){
                        p_boatU2[0]=indexX;
                    }else if(v.equals(findViewById(R.id.unit_boat3))){
                        p_boatU3[0]=indexX;
                    }

                    break;

                case 1:
                    int indexY;

                    switch (tag){
                        case "boatL":

                            yUnit = -0.25f*yUnit;
                            indexY = (int) ((val + yUnit*0.4)/yUnit);

                            indexY = ((4/3)*indexY +1);

                            if(indexY>9)
                                indexY = 9;

                            if(indexY<-27)
                                indexY=-27;

                            int set,nearest=-1,setNearest=indexY,calc;
                            for(set=9;set>=-27;set-=4){
                                calc = Math.abs(set-indexY);
                                if(nearest==-1){
                                    nearest = calc;
                                    setNearest = set;
                                }else if(calc<nearest){
                                    nearest = calc;
                                    setNearest = set;
                                }
                            }
                            indexY = setNearest;

                            break;

                        case "boatM":
                            yUnit = 0.5f*yUnit;
                            indexY = (int) ((val)/(yUnit*0.3f));

                            indexY = (indexY/3);
                            if((indexY&1)==0)
                                indexY++;

                            if(indexY<-5)
                                indexY=-5;
                            if(indexY>13)
                                indexY=13;
                            break;
                        case "boatS":
                            yUnit = 0.5f*yUnit;
                            indexY = (int) ((val + yUnit*0.4)/yUnit);
                            if (indexY<=0)
                                indexY = -1;
                            else if((indexY&1)==0)
                                indexY --;

                            if(indexY>17)
                                indexY=17;

                            if(v.equals(findViewById(R.id.small_boat1))){
                                p_boatS1[1]=indexY;
                            }else if(v.equals(findViewById(R.id.small_boat2))){
                                p_boatS2[1]=indexY;
                            }


                            break;
                        default:
                            indexY = (int) ((Math.abs(val) + yUnit*0.4)/yUnit);
                            if (indexY<0)
                                indexY = 0;
                            if (indexY>9)
                                indexY = 9;
                            if(v.equals(findViewById(R.id.unit_boat1))){
                                p_boatU1[1]=indexY;
                            }else if(v.equals(findViewById(R.id.unit_boat2))){
                                p_boatU2[1]=indexY;
                            }else if(v.equals(findViewById(R.id.unit_boat3))){
                                p_boatU3[1]=indexY;
                            }


                    }

                    res = indexY*yUnit;

                    break;
            }


            return res;
        }


        private void adjust(View v,float rawX,float rawY){
            float rot = v.getRotation();
            float xUnit = gridView.getWidth()/CONST_GRID;
            float yUnit = gridView.getHeight()/CONST_GRID;
            float adjustX=v.getX(),adjustY=v.getY();
            String tag = v.getTag().toString();

            boolean isY = !((rot==90) || (rot==270));

            switch (tag){
                case "boatL":

                    int index;

                    if(isY){
                        yUnit = -0.25f*yUnit;
                        index =(int) (v.getY()/yUnit);
                        Log.e("INDEX",index+"");
                        if(index==9) {
                            adjustY -= 5*yUnit;
                        }else if(index==-27){
                            adjustY += 5*yUnit;
                        }

                        p_boatL[1] = index;
                    }else{
                        index = (int) (v.getX()/xUnit);
                        if(index==0)
                            adjustX += xUnit;
                        else if(index==9)
                            adjustX -= xUnit;

                        p_boatL[0] = index;
                    }
                    break;

                case "boatM":
                    if(isY){
                        yUnit = 0.5f*yUnit;
                        index =(int) (v.getY()/yUnit);
                        if(index==-5){
                            adjustY -= yUnit;
                        }else if(index==13)
                            adjustY += yUnit;

                        p_boatM[1] = index;
                    }else{
                        index = (int) (v.getX()/xUnit);
                        if(index==0)
                            adjustX += xUnit;
                        else if(index==9)
                            adjustX -= xUnit;

                        p_boatM[0] = index;
                    }
                    break;

                case "boatS":
                    break;
            }


            v.animate()
                    .x(adjustX)
                    .y(adjustY)
                    .setDuration(0)
                    .start();

        }

    }
}

