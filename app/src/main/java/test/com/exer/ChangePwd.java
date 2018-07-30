package test.com.exer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChangePwd extends AppCompatActivity {


    private String TAG = "===Client===";

    Handler mhandler;
    Handler mhandlerSend;
    boolean isRun = true;

    Button send;
    Button cancel;

    SharedPreferences sp;
    private Context ctx;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    SocThread socketThread;

    private String ip=null;
    private String employee=null;
    private String key=null;
    private String newkey=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork() // 这里可以替换为detectAll() 就包括了磁盘读写和网络I/O
                .penaltyLog() //打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() //探测SQLite数据库操作
                .penaltyLog() //打印logcat
                .penaltyDeath()
                .build());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepwd);


        send = (Button) findViewById(R.id.send);
        cancel=(Button) findViewById(R.id.cancel);
        ctx = ChangePwd.this;

        sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        sp.getString("ipstr", "");
        sp.getString("port", "");


        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                try {
                    //MyLog.i(TAG, "mhandler接收到msg=" + msg.what);
                    if (msg.obj != null) {
                        String s = msg.obj.toString();
                        if (s.trim().length() > 0) {
                            //MyLog.i(TAG, "mhandler接收到obj=" + s);
                            //MyLog.i(TAG, "开始更新UI");
                            if(s.indexOf("修改成功")>=0) {
                                MyLog.i(TAG, "修改密码成功");
                                Toast.makeText(ctx,"修改成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(ctx,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else
                            Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
                            MyLog.i(TAG, s);
                        } else {
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
                        MyLog.i(TAG, "发送修改密码请求成功");
                    } else {
                        MyLog.i(TAG, "与服务端连接失败");
                        Toast.makeText(ChangePwd.this,"与服务端连接失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ee) {
                    MyLog.i(TAG, "加载过程出现异常");
                    ee.printStackTrace();
                }
            }
        };
        startSocket();

            /*修改密码*/
        send.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText user = (EditText)findViewById(R.id.account);
                EditText pwd = (EditText)findViewById(R.id.pwd);
                EditText newpwd = (EditText)findViewById(R.id.newpwd);
                EditText confirm = (EditText)findViewById(R.id.confirm);
                EditText ipaddr=(EditText)findViewById(R.id.ip);
                employee=user.getText().toString();
                key=pwd.getText().toString();
                newkey=newpwd.getText().toString();
                String confirmkey=confirm.getText().toString();
                ip=ipaddr.getText().toString();
                Log.i(TAG,"开始修改");
                String port ="9000";
                Editor editor = sp.edit();
                editor.putString("ipstr", ip);
                editor.putString("port", port);
                editor.commit();
                Log.i(TAG, "保存成功"+sp.getString("ipstr", "")+";"+sp.getString("port", ""));
                /*发送内容*/
                MyLog.i(TAG, "准备发送改密信息");
                if(confirmkey.equals(newkey)) {
                    String check = "change" + employee + "old" + key + "new" + newkey;
                    socketThread.Send(check);
                }else{
                    MyLog.i(TAG, "改密时两次密码填写不一致");
                    Toast.makeText(ctx, "密码不一致", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(ctx,ChangePwd.class));
                }



            }
        });
        cancel.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               MyLog.i(TAG, "放弃修改密码");
               startActivity(new Intent(ctx,MainActivity.class));
               finish();
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


