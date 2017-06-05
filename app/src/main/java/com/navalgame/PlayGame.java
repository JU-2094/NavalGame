package com.navalgame;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PlayGame extends Activity implements View.OnClickListener{

    ImageView A1,A2,A3,A4,A5,A6,A7,A8,A9,A10;
    ImageView B1,B2,B3,B4,B5,B6,B7,B8,B9,B10;
    ImageView C1,C2,C3,C4,C5,C6,C7,C8,C9,C10;
    ImageView D1,D2,D3,D4,D5,D6,D7,D8,D9,D10;
    ImageView E1,E2,E3,E4,E5,E6,E7,E8,E9,E10;
    ImageView F1,F2,F3,F4,F5,F6,F7,F8,F9,F10;
    ImageView G1,G2,G3,G4,G5,G6,G7,G8,G9,G10;
    ImageView H1,H2,H3,H4,H5,H6,H7,H8,H9,H10;
    ImageView I1,I2,I3,I4,I5,I6,I7,I8,I9,I10;
    ImageView J1,J2,J3,J4,J5,J6,J7,J8,J9,J10;

    View gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        gridView = findViewById(R.id.grid_view);

        decclarar();

        A1.setOnClickListener(this);
        A2.setOnClickListener(this);
        A3.setOnClickListener(this);
        A4.setOnClickListener(this);
        A5.setOnClickListener(this);
        A6.setOnClickListener(this);
        A7.setOnClickListener(this);
        A8.setOnClickListener(this);
        A9.setOnClickListener(this);
        A10.setOnClickListener(this);

        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);
        B9.setOnClickListener(this);
        B10.setOnClickListener(this);

        C1.setOnClickListener(this);
        C2.setOnClickListener(this);
        C3.setOnClickListener(this);
        C4.setOnClickListener(this);
        C5.setOnClickListener(this);
        C6.setOnClickListener(this);
        C7.setOnClickListener(this);
        C8.setOnClickListener(this);
        C9.setOnClickListener(this);
        C10.setOnClickListener(this);

        D1.setOnClickListener(this);
        D2.setOnClickListener(this);
        D3.setOnClickListener(this);
        D4.setOnClickListener(this);
        D5.setOnClickListener(this);
        D6.setOnClickListener(this);
        D7.setOnClickListener(this);
        D8.setOnClickListener(this);
        D9.setOnClickListener(this);
        D10.setOnClickListener(this);

        E1.setOnClickListener(this);
        E2.setOnClickListener(this);
        E3.setOnClickListener(this);
        E4.setOnClickListener(this);
        E5.setOnClickListener(this);
        E6.setOnClickListener(this);
        E7.setOnClickListener(this);
        E8.setOnClickListener(this);
        E9.setOnClickListener(this);
        E10.setOnClickListener(this);

        F1.setOnClickListener(this);
        F2.setOnClickListener(this);
        F3.setOnClickListener(this);
        F4.setOnClickListener(this);
        F5.setOnClickListener(this);
        F6.setOnClickListener(this);
        F7.setOnClickListener(this);
        F8.setOnClickListener(this);
        F9.setOnClickListener(this);
        F10.setOnClickListener(this);

        G1.setOnClickListener(this);
        G2.setOnClickListener(this);
        G3.setOnClickListener(this);
        G4.setOnClickListener(this);
        G5.setOnClickListener(this);
        G6.setOnClickListener(this);
        G7.setOnClickListener(this);
        G8.setOnClickListener(this);
        G9.setOnClickListener(this);
        G10.setOnClickListener(this);

        H1.setOnClickListener(this);
        H2.setOnClickListener(this);
        H3.setOnClickListener(this);
        H4.setOnClickListener(this);
        H5.setOnClickListener(this);
        H6.setOnClickListener(this);
        H7.setOnClickListener(this);
        H8.setOnClickListener(this);
        H9.setOnClickListener(this);
        H10.setOnClickListener(this);

        I1.setOnClickListener(this);
        I2.setOnClickListener(this);
        I3.setOnClickListener(this);
        I4.setOnClickListener(this);
        I5.setOnClickListener(this);
        I6.setOnClickListener(this);
        I7.setOnClickListener(this);
        I8.setOnClickListener(this);
        I9.setOnClickListener(this);
        I10.setOnClickListener(this);

        J1.setOnClickListener(this);
        J2.setOnClickListener(this);
        J3.setOnClickListener(this);
        J4.setOnClickListener(this);
        J5.setOnClickListener(this);
        J6.setOnClickListener(this);
        J7.setOnClickListener(this);
        J8.setOnClickListener(this);
        J9.setOnClickListener(this);
        J10.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        float alpha = (float) 0.9;
        view.setAlpha(alpha);
    }


    public void decclarar(){

        A1 = (ImageView)findViewById(R.id.A1);
        A2 = (ImageView)findViewById(R.id.A2);
        A3 = (ImageView)findViewById(R.id.A3);
        A4 = (ImageView)findViewById(R.id.A4);
        A5 = (ImageView)findViewById(R.id.A5);
        A6 = (ImageView)findViewById(R.id.A6);
        A7 = (ImageView)findViewById(R.id.A7);
        A8 = (ImageView)findViewById(R.id.A8);
        A9 = (ImageView)findViewById(R.id.A9);
        A10 = (ImageView)findViewById(R.id.A10);

        B1 = (ImageView)findViewById(R.id.B1);
        B2 = (ImageView)findViewById(R.id.B2);
        B3 = (ImageView)findViewById(R.id.B3);
        B4 = (ImageView)findViewById(R.id.B4);
        B5 = (ImageView)findViewById(R.id.B5);
        B6 = (ImageView)findViewById(R.id.B6);
        B7 = (ImageView)findViewById(R.id.B7);
        B8 = (ImageView)findViewById(R.id.B8);
        B9 = (ImageView)findViewById(R.id.B9);
        B10 = (ImageView)findViewById(R.id.B10);

        C1 = (ImageView)findViewById(R.id.C1);
        C2 = (ImageView)findViewById(R.id.C2);
        C3 = (ImageView)findViewById(R.id.C3);
        C4 = (ImageView)findViewById(R.id.C4);
        C5 = (ImageView)findViewById(R.id.C5);
        C6 = (ImageView)findViewById(R.id.C6);
        C7 = (ImageView)findViewById(R.id.C7);
        C8 = (ImageView)findViewById(R.id.C8);
        C9 = (ImageView)findViewById(R.id.C9);
        C10 = (ImageView)findViewById(R.id.C10);

        D1 = (ImageView)findViewById(R.id.D1);
        D2 = (ImageView)findViewById(R.id.D2);
        D3 = (ImageView)findViewById(R.id.D3);
        D4 = (ImageView)findViewById(R.id.D4);
        D5 = (ImageView)findViewById(R.id.D5);
        D6 = (ImageView)findViewById(R.id.D6);
        D7 = (ImageView)findViewById(R.id.D7);
        D8 = (ImageView)findViewById(R.id.D8);
        D9 = (ImageView)findViewById(R.id.D9);
        D10 = (ImageView)findViewById(R.id.D10);

        E1 = (ImageView)findViewById(R.id.E1);
        E2 = (ImageView)findViewById(R.id.E2);
        E3 = (ImageView)findViewById(R.id.E3);
        E4 = (ImageView)findViewById(R.id.E4);
        E5 = (ImageView)findViewById(R.id.E5);
        E6 = (ImageView)findViewById(R.id.E6);
        E7 = (ImageView)findViewById(R.id.E7);
        E8 = (ImageView)findViewById(R.id.E8);
        E9 = (ImageView)findViewById(R.id.E9);
        E10 = (ImageView)findViewById(R.id.E10);

        F1 = (ImageView)findViewById(R.id.F1);
        F2 = (ImageView)findViewById(R.id.F2);
        F3 = (ImageView)findViewById(R.id.F3);
        F4 = (ImageView)findViewById(R.id.F4);
        F5 = (ImageView)findViewById(R.id.F5);
        F6 = (ImageView)findViewById(R.id.F6);
        F7 = (ImageView)findViewById(R.id.F7);
        F8 = (ImageView)findViewById(R.id.F8);
        F9 = (ImageView)findViewById(R.id.F9);
        F10 = (ImageView)findViewById(R.id.F10);

        G1 = (ImageView)findViewById(R.id.G1);
        G2 = (ImageView)findViewById(R.id.G2);
        G3 = (ImageView)findViewById(R.id.G3);
        G4 = (ImageView)findViewById(R.id.G4);
        G5 = (ImageView)findViewById(R.id.G5);
        G6 = (ImageView)findViewById(R.id.G6);
        G7 = (ImageView)findViewById(R.id.G7);
        G8 = (ImageView)findViewById(R.id.G8);
        G9 = (ImageView)findViewById(R.id.G9);
        G10 = (ImageView)findViewById(R.id.G10);

        H1 = (ImageView)findViewById(R.id.H1);
        H2 = (ImageView)findViewById(R.id.H2);
        H3 = (ImageView)findViewById(R.id.H3);
        H4 = (ImageView)findViewById(R.id.H4);
        H5 = (ImageView)findViewById(R.id.H5);
        H6 = (ImageView)findViewById(R.id.H6);
        H7 = (ImageView)findViewById(R.id.H7);
        H8 = (ImageView)findViewById(R.id.H8);
        H9 = (ImageView)findViewById(R.id.H9);
        H10 = (ImageView)findViewById(R.id.H10);

        I1 = (ImageView)findViewById(R.id.I1);
        I2 = (ImageView)findViewById(R.id.I2);
        I3 = (ImageView)findViewById(R.id.I3);
        I4 = (ImageView)findViewById(R.id.I4);
        I5 = (ImageView)findViewById(R.id.I5);
        I6 = (ImageView)findViewById(R.id.I6);
        I7 = (ImageView)findViewById(R.id.I7);
        I8 = (ImageView)findViewById(R.id.I8);
        I9 = (ImageView)findViewById(R.id.I9);
        I10 = (ImageView)findViewById(R.id.I10);

        J1 = (ImageView)findViewById(R.id.J1);
        J2 = (ImageView)findViewById(R.id.J2);
        J3 = (ImageView)findViewById(R.id.J3);
        J4 = (ImageView)findViewById(R.id.J4);
        J5 = (ImageView)findViewById(R.id.J5);
        J6 = (ImageView)findViewById(R.id.J6);
        J7 = (ImageView)findViewById(R.id.J7);
        J8 = (ImageView)findViewById(R.id.J8);
        J9 = (ImageView)findViewById(R.id.J9);
        J10 = (ImageView)findViewById(R.id.J10);
    }

}