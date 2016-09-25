package com.example.rameez.quizapp1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increament(View view){
        TextView tv_counter = (TextView) findViewById(R.id.counter);
        tv_counter.setText("" + (++this.counter));
    }
    public void decreament(View view){
        TextView tv_counter = (TextView) findViewById(R.id.counter);
        if(this.counter > 0) {
            tv_counter.setText("" + (--this.counter));
        }
    }
    public void place_order(View vew){
        String order_type = "";
        String total_price = "";
        TextView tv1 = (TextView) findViewById(R.id.order_details1);
        CheckBox cb1 = (CheckBox) findViewById(R.id.iceCream);
        CheckBox cb2 = (CheckBox) findViewById(R.id.chocolate);
        if(cb1.isChecked() && cb2.isChecked()){
            order_type = "BOTH";
        }else if(cb1.isChecked()){
            order_type = "IC";
        }else if (cb2.isChecked()){
            order_type = "CH";
        }else{
            order_type = "";
        }
        if(this.counter !=0){
            if(order_type == "BOTH"){
                total_price = ""+(this.counter*100);
            }
            if(order_type == "IC"){
                total_price = ""+(this.counter*50);
            }
            if (order_type == "CH"){
                total_price = ""+(this.counter*75);
            }
            if(order_type == ""){
                total_price = ""+(this.counter*25);
            }
            String order_summary  = "Name: Rameez Masood";
            order_summary += "\nQuantity: " +counter ;
            order_summary = order_summary + "\nAdd Ice Cream ?: " +((cb1.isChecked())?"True":"false") ;
            order_summary = order_summary + "\nAdd the Chocolate: " +((cb2.isChecked())?"True":"false") ;
            order_summary = order_summary + "\nTotal: $" +total_price ;
            order_summary = order_summary + "\nThank You! " ;
            //Toast.makeText(this,order_summary,Toast.LENGTH_SHORT).show();
            tv1.setText(order_summary);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, "cristalperson@live.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Place Order");
            intent.putExtra(Intent.EXTRA_TEXT, order_summary);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }else {
            tv1.setText("");
            Toast.makeText(this,"Please place atleast one order.",Toast.LENGTH_SHORT).show();
        }

    }
}
