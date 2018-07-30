package test.com.exer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ResultDisplay extends Activity {
    private String order=null;
    private String ip;

    private TextView order1;
    private TextView order2;
    private TextView order3;
    private TextView order4;
    private TextView order5;
    private TextView order6;
    private TextView order7;
    private TextView order8;
    private TextView order9;
    private TextView order10;

    private TextView kind1=null;
    private TextView kind2=null;
    private TextView kind3=null;
    private TextView kind4=null;
    private TextView kind5=null;
    private TextView kind6=null;
    private TextView kind7=null;
    private TextView kind8=null;
    private TextView kind9=null;
    private TextView kind10=null;

    private TextView num1;
    private TextView num2;
    private TextView num3;
    private TextView num4;
    private TextView num5;
    private TextView num6;
    private TextView num7;
    private TextView num8;
    private TextView num9;
    private TextView num10;
    private String result;



    private String TAG = "===Client===";
    Handler mhandler;
    Handler mhandlerSend;
    SharedPreferences sp;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    SocThread socketThread;

    Button btnsend;
    private Context ctx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultdisplay);

        btnsend = (Button) findViewById(R.id.yes);
        ctx=ResultDisplay.this;

        sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        sp.getString("ipstr", "");
        sp.getString("port", "");

        //服务端返回具体信息
        Intent i=getIntent();
        Bundle b=new Bundle();
        b=i.getExtras();
        IpResult info=(IpResult) b.getSerializable("ipresult");
        ip=info.getIp();
        result=info.getResult();

        //定义

        kind1 = (TextView) findViewById(R.id.kind1);
        kind1.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind1.setBackgroundColor(Color.BLUE);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order1.getText().toString();
        }});
        order1 = (TextView) findViewById(R.id.order1);
        order1.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order1.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order1.getText().toString();
        }});
        num1 = (TextView) findViewById(R.id.n1);
        num1.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num1.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order1.getText().toString();
        }});

        kind2 = (TextView) findViewById(R.id.kind2);
        kind2.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind2.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order2.getText().toString();
            if(order.equals("")){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order2 = (TextView) findViewById(R.id.order2);
        order2.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order2.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order2.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num2 = (TextView) findViewById(R.id.n2);
        num2.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num2.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order2.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind3 = (TextView) findViewById(R.id.kind3);
        kind3.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind3.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order3.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order3 = (TextView) findViewById(R.id.order3);
        order3.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order3.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order3.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num3 = (TextView) findViewById(R.id.n3);
        num3.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num3.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order3.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind4 = (TextView) findViewById(R.id.kind4);
        kind4.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind4.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order4.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order4 = (TextView) findViewById(R.id.order4);
        order4.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order4.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order4.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num4 = (TextView) findViewById(R.id.n4);
        num4.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num4.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order4.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind5 = (TextView) findViewById(R.id.kind5);
        kind5.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind5.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order5.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order5 = (TextView) findViewById(R.id.order5);
        order5.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order5.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order5.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num5 = (TextView) findViewById(R.id.n5);
        num5.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num5.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order5.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind6 = (TextView) findViewById(R.id.kind6);
        kind6.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind6.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order6.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order6 = (TextView) findViewById(R.id.order6);
        order6.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order6.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order6.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num6 = (TextView) findViewById(R.id.n6);
        num6.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num6.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order6.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind7 = (TextView) findViewById(R.id.kind7);
        kind7.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind7.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order7.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order7 = (TextView) findViewById(R.id.order7);
        order7.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order7.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order7.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num7 = (TextView) findViewById(R.id.n7);
        num7.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num7.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order7.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind8 = (TextView) findViewById(R.id.kind8);
        kind8.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind8.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order8.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order8 = (TextView) findViewById(R.id.order8);
        order8.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order8.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order8.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num8 = (TextView) findViewById(R.id.n8);
        num8.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num8.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order8.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind9 = (TextView) findViewById(R.id.kind9);
        kind9.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind9.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order9.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order9 = (TextView) findViewById(R.id.order9);
        order9.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order9.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order9.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num9 = (TextView) findViewById(R.id.n9);
        num9.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num9.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order9.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});

        kind10 = (TextView) findViewById(R.id.kind10);
        kind10.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            kind10.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order10.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        order10 = (TextView) findViewById(R.id.order10);
        order10.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            order10.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            num10.setBackgroundColor(Color.TRANSPARENT);
            order=order10.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});
        num10 = (TextView) findViewById(R.id.n10);
        num10.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v) {
            num10.setBackgroundColor(Color.BLUE);
            kind1.setBackgroundColor(Color.TRANSPARENT);
            order1.setBackgroundColor(Color.TRANSPARENT);
            num1.setBackgroundColor(Color.TRANSPARENT);
            kind2.setBackgroundColor(Color.TRANSPARENT);
            order2.setBackgroundColor(Color.TRANSPARENT);
            num2.setBackgroundColor(Color.TRANSPARENT);
            kind3.setBackgroundColor(Color.TRANSPARENT);
            order3.setBackgroundColor(Color.TRANSPARENT);
            num3.setBackgroundColor(Color.TRANSPARENT);
            kind4.setBackgroundColor(Color.TRANSPARENT);
            order4.setBackgroundColor(Color.TRANSPARENT);
            num4.setBackgroundColor(Color.TRANSPARENT);
            kind5.setBackgroundColor(Color.TRANSPARENT);
            order5.setBackgroundColor(Color.TRANSPARENT);
            num5.setBackgroundColor(Color.TRANSPARENT);
            kind6.setBackgroundColor(Color.TRANSPARENT);
            order6.setBackgroundColor(Color.TRANSPARENT);
            num6.setBackgroundColor(Color.TRANSPARENT);
            kind7.setBackgroundColor(Color.TRANSPARENT);
            order7.setBackgroundColor(Color.TRANSPARENT);
            num7.setBackgroundColor(Color.TRANSPARENT);
            kind8.setBackgroundColor(Color.TRANSPARENT);
            order8.setBackgroundColor(Color.TRANSPARENT);
            num8.setBackgroundColor(Color.TRANSPARENT);
            kind9.setBackgroundColor(Color.TRANSPARENT);
            order9.setBackgroundColor(Color.TRANSPARENT);
            num9.setBackgroundColor(Color.TRANSPARENT);
            kind10.setBackgroundColor(Color.TRANSPARENT);
            order10.setBackgroundColor(Color.TRANSPARENT);
            order=order10.getText().toString();
            if(order==null){
                Toast.makeText(ctx,"请选择订单！",Toast.LENGTH_SHORT).show();
            }
        }});


        //截取信息中具体的品种，单号和数量
        int k1start=result.indexOf("kind1");
        int k1end=result.indexOf("order1");
        String k1=result.substring(k1start+5,k1end);
        kind1.setText(k1);
        int o1end=result.indexOf("num1");
        String o1=result.substring(k1end+6,o1end);
        order1.setText(o1);
        int n1end=result.indexOf("kind2");
        if(n1end==-1){
            num1.setText(result.substring(o1end+4));}
        else{
            String n1=result.substring(o1end+4,n1end);
            num1.setText(n1);
            int k2start=result.indexOf("kind2");
            int k2end=result.indexOf("order2");
            String k2=result.substring(k2start+5,k2end);
            kind2.setText(k2);
            int o2end=result.indexOf("num2");
            String o2=result.substring(k2end+6,o2end);
            order2.setText(o2);
            int n2end=result.indexOf("kind3");
            if(n2end==-1){
                String n2=result.substring(o2end+4);
                num2.setText(n2);}
            else{
                num2.setText(result.substring(o2end+4,n2end));
                int k3start=result.indexOf("kind3");
                int k3end=result.indexOf("order3");
                kind3.setText(result.substring(k3start+5,k3end));
                int o3end=result.indexOf("num3");
                order3.setText(result.substring(k3end+6,o3end));
                int n3end=result.indexOf("kind4");
                if(n3end==-1){
                    num3.setText(result.substring(o3end+4));}
                else{
                    num3.setText(result.substring(o3end+4,n3end));
                    int k4start=result.indexOf("kind4");
                    int k4end=result.indexOf("order4");
                    kind4.setText(result.substring(k4start+5,k4end));
                    int o4end=result.indexOf("num4");
                    order4.setText(result.substring(k4end+6,o4end));
                    int n4end=result.indexOf("kind5");
                    if(n4end==-1){
                        num4.setText(result.substring(o4end+4));}
                    else{
                        num4.setText(result.substring(o4end+4,n4end));
                        int k5start=result.indexOf("kind5");
                        int k5end=result.indexOf("order5");
                        kind5.setText(result.substring(k5start+5,k5end));
                        int o5end=result.indexOf("num5");
                        order5.setText(result.substring(k5end+6,o5end));
                        int n5end=result.indexOf("kind6");
                        if(n5end==-1){
                            num5.setText(result.substring(o5end+4));}
                        else{
                            num5.setText(result.substring(o5end+4,n5end));
                            int k6start=result.indexOf("kind6");
                            int k6end=result.indexOf("order6");
                            kind6.setText(result.substring(k6start+5,k6end));
                            int o6end=result.indexOf("num6");
                            order6.setText(result.substring(k6end+6,o6end));
                            int n6end=result.indexOf("kind7");
                            if(n6end==-1){
                                num6.setText(result.substring(o6end+4));}
                            else{
                                num6.setText(result.substring(o6end+4,n6end));
                                int k7start=result.indexOf("kind7");
                                int k7end=result.indexOf("order7");
                                kind7.setText(result.substring(k7start+5,k7end));
                                int o7end=result.indexOf("num7");
                                order7.setText(result.substring(k7end+6,o7end));
                                int n7end=result.indexOf("kind8");
                                if(n7end==-1){
                                    num7.setText(result.substring(o7end+4));}
                                else{
                                    num7.setText(result.substring(o7end+4,n7end));
                                    int k8start=result.indexOf("kind8");
                                    int k8end=result.indexOf("order8");
                                    kind8.setText(result.substring(k8start+5,k8end));
                                    int o8end=result.indexOf("num8");
                                    order8.setText(result.substring(k8end+6,o8end));
                                    int n8end=result.indexOf("kind9");
                                    if(n8end==-1){
                                        num8.setText(result.substring(o8end+4));}
                                    else{
                                        num8.setText(result.substring(o8end+4,n8end));
                                        int k9start=result.indexOf("kind9");
                                        int k9end=result.indexOf("order9");
                                        kind9.setText(result.substring(k9start+5,k9end));
                                        int o9end=result.indexOf("num9");
                                        order9.setText(result.substring(k9end+6,o9end));
                                        int n9end=result.indexOf("kind10");
                                        if(n9end==-1){
                                            num9.setText(result.substring(o9end+4));}
                                        else{
                                            num9.setText(result.substring(o9end+4,n9end));
                                            int k10start=result.indexOf("kind10");
                                            int k10end=result.indexOf("order10");
                                            kind10.setText(result.substring(k10start+6,k10end));
                                            int o10end=result.indexOf("num10");
                                            order10.setText(result.substring(k10end+7,o10end));
                                            num10.setText(result.substring(o10end+5));}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                try {
                    //MyLog.i(TAG, "mhandler接收到msg=" + msg.what);
                    if (msg.obj != null) {
						/*s为服务端发回的提示信息*/
                        String s = msg.obj.toString();
                        if (s.trim().length() > 0) {
                            //MyLog.i(TAG, "mhandler接收到obj=" + s);
                            //MyLog.i(TAG, "开始更新UI");
                            if(s.indexOf("未选择订单")>=0) {
                                MyLog.i(TAG,"未选择订单");
                                Toast.makeText(ctx, "重新选择订单", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent=new Intent(ctx,ResultDisplay.class);
                                IpResult ir=new IpResult(ip,result);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("ipresult",ir);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                            else{
                                MyLog.i(TAG, "接收具体单号信息成功");
                                Intent intent=new Intent(ctx,Result.class);
                                intent.putExtra("data",s);
                                startActivity(intent);}

                        } else {
                            Log.i(TAG, "没有数据返回不更新");
                            MyLog.i(TAG, "没有数据返回");
                        }
                    }
                } catch (Exception ee) {
                    MyLog.i(TAG, "加载过程出现异常");
                    ee.printStackTrace();
                }
            }
        };
        mhandlerSend = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                try {
                    //MyLog.i(TAG, "mhandlerSend接收到msg.what=" + msg.what);
                    String s = msg.obj.toString();
                    if (msg.what == 1) {
                        MyLog.i(TAG,"发送具体单号成功");
                    } else {
                        MyLog.i(TAG,"与服务端连接失败");
                    }
                } catch (Exception ee) {
                    MyLog.i(TAG, "加载过程出现异常");
                    ee.printStackTrace();
                }
            }
        };
        startSocket();

        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送数据
                Log.i(TAG,"开始修改");
                String port = "9000";
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("ipstr", ip);
                editor.putString("port", port);
                editor.commit();//保存新数据
                Log.i(TAG, "保存成功"+sp.getString("ipstr", "")+";"+sp.getString("port", ""));
                MyLog.i(TAG, "准备发送具体订单号");
                if(order==null){
                    order="";
                }
                socketThread.Send("tank"+order);

            }
        });

    }
    public void startSocket() {
        socketThread = new SocThread(mhandler, mhandlerSend, ctx);
        socketThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.client, menu);
        return true;
    }

    private void stopSocket() {
        if(socketThread!=null) {
            socketThread.isRun = false;
            socketThread.close();
            socketThread = null;
            MyLog.i(TAG, "Socket已终止");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "start onStart~~~");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "start onRestart~~~");
        startSocket();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "start onResume~~~");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "start onPause~~~");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "start onStop~~~");
        stopSocket();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "start onDestroy~~~");

    }

}


