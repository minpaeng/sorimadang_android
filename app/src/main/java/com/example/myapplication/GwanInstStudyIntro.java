package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication.ui.Instrument.InstrumentFragment;

public class GwanInstStudyIntro extends AppCompatActivity {

    Button g1, g2, g3, g4, g5, g6, g7, g8, g9, g10,g11, g12, g13, g14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwan_inst_study_intro);

        g1=findViewById(R.id.G_bt);
        g2=findViewById(R.id.G_bt2);
        g3=findViewById(R.id.G_bt3);
        g4=findViewById(R.id.G_bt4);
        g5=findViewById(R.id.G_bt5);
        g6=findViewById(R.id.G_bt6);
        g7=findViewById(R.id.G_bt7);
        g8=findViewById(R.id.G_bt8);
        g9=findViewById(R.id.G_bt9);
        g10=findViewById(R.id.G_bt10);
        g11=findViewById(R.id.G_bt11);
        g12=findViewById(R.id.G_bt12);
        g13=findViewById(R.id.G_bt13);
        g14=findViewById(R.id.G_bt14);





    }

    public void btnClickGwan(View view){
        if(view.getId()==R.id.G_bt){
            Intent intent1 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent1.putExtra("whatAkgi",1);
            intent1.putExtra("akgi",g1.getText());
            startActivity(intent1);
        }else if(view.getId()==R.id.G_bt2){
            Intent intent2 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent2.putExtra("whatAkgi",1);
            intent2.putExtra("akgi",g2.getText());
            startActivity(intent2);
        }else if(view.getId()==R.id.G_bt3){
            Intent intent3 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent3.putExtra("whatAkgi",1);
            intent3.putExtra("akgi",g3.getText());
            startActivity(intent3);
        }else if(view.getId()==R.id.G_bt4){
            Intent intent4 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent4.putExtra("whatAkgi",1);
            intent4.putExtra("akgi",g4.getText());
            startActivity(intent4);
        }else if(view.getId()==R.id.G_bt5){
            Intent intent5 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent5.putExtra("whatAkgi",1);
            intent5.putExtra("akgi",g5.getText());
            startActivity(intent5);
        }else if(view.getId()==R.id.G_bt6){
            Intent intent6 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent6.putExtra("whatAkgi",1);
            intent6.putExtra("akgi",g6.getText());
            startActivity(intent6);
        }else if(view.getId()==R.id.G_bt7){
            Intent intent7 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent7.putExtra("whatAkgi",1);
            intent7.putExtra("akgi",g7.getText());
            startActivity(intent7);
        }else if(view.getId()==R.id.G_bt8){
            Intent intent8 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent8.putExtra("whatAkgi",1);
            intent8.putExtra("akgi",g8.getText());
            startActivity(intent8);
        }else if(view.getId()==R.id.G_bt9){
            Intent intent9 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent9.putExtra("whatAkgi",1);
            intent9.putExtra("akgi",g9.getText());
            startActivity(intent9);
        }else if(view.getId()==R.id.G_bt10){
            Intent intent10 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent10.putExtra("whatAkgi",1);
            intent10.putExtra("akgi",g10.getText());
            startActivity(intent10);
        }else if(view.getId()==R.id.G_bt11){
            Intent intent11 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent11.putExtra("whatAkgi",1);
            intent11.putExtra("akgi",g11.getText());
            startActivity(intent11);
        }else if(view.getId()==R.id.G_bt12){
            Intent intent12 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent12.putExtra("whatAkgi",1);
            intent12.putExtra("akgi",g12.getText());
            startActivity(intent12);
        }else if(view.getId()==R.id.G_bt13){
            Intent intent13 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent13.putExtra("whatAkgi",1);
            intent13.putExtra("akgi",g13.getText());
            startActivity(intent13);
        }else{
            Intent intent14 = new Intent(GwanInstStudyIntro.this, InstStudyActivity.class);
            intent14.putExtra("whatAkgi",1);
            intent14.putExtra("akgi",g14.getText());
            startActivity(intent14);
        }
    }

    public void G_instbackActivity(View view) {
        startActivity(new Intent(GwanInstStudyIntro.this, InstrumentFragment.class));
    }
}