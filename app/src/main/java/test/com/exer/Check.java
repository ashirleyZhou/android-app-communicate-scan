package test.com.exer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Check extends Activity {
    private String TAG = "===Client===";

    private String ip;

    Handler mhandler;
    Handler mhandlerSend;
    boolean isRun = true;

    private EditText orderedit=null;
    Button btnsend;

    SharedPreferences sp;
    private Context ctx;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    SocThread socketThread;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);

        orderedit =(EditText) findViewById(R.id.order);

        Intent i=getIntent();
        Bundle b=new Bundle();
        b=i.getBundleExtra("data");
        ip=b.getString("ipaddress");

        btnsend = (Button) findViewById(R.id.button1);

        ctx = Check.this;

        sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        sp.getString("ipstr", "");
        sp.getString("port", "");

        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                try {
                    MyLog.i(TAG, "mhandler接收到msg=" + msg.what);
                    if (msg.obj != null) {
						/*s为服务端发回的提示信息*/
                        String s = msg.obj.toString();
                        if (s.trim().length() > 0 ) {
                            //MyLog.i(TAG, "mhandler接收到obj=" + s);
                            //MyLog.i(TAG, "开始更新UI");
                            MyLog.i(TAG,"查询成功");
                            if (s.indexOf("订单号不存在")==-1){
                            Intent intent=new Intent(ctx,ResultDisplay.class);
                            IpResult ir=new IpResult(ip,s);
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("ipresult",ir);
                            intent.putExtras(bundle);
                            startActivity(intent);}
                          else{
                                MyLog.i(TAG,"订单号不存在");
                                Toast.makeText(ctx,"订单号不存在",Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent=new Intent(ctx,Check.class);
                                Bundle B = new Bundle();
                                B.putString("ipaddress", ip);
                                intent.putExtra("data", B);
                                startActivity(intent);
                          }
                            MyLog.i(TAG, "更新UI完毕");
                        }else{
                            Log.i(TAG, "没有数据返回不更新");
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
                        MyLog.i(TAG,"发送查询要求成功");
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

        btnsend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 发送数据
                Log.i(TAG,"开始修改");
                String order = orderedit.getText().toString();//订单号
                String port = "9000";
                Editor editor = sp.edit();
                editor.putString("ipstr", ip);
                editor.putString("port", port);
                editor.commit();//保存新数据
                Log.i(TAG, "保存成功"+sp.getString("ipstr", "")+";"+sp.getString("port", ""));
                MyLog.i(TAG, "准备发送查询单号");
                socketThread.Send("check"+order);

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

    public boolean onKeyDown(int keyCode,KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            //这里重写返回键
            // finish();

            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);

    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();

        }
    }

}
