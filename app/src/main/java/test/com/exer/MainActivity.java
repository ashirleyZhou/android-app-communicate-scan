package test.com.exer;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.StrictMode;

import android.app.Activity;
import android.content.Context;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;

import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {


    private String TAG = "===Client===";
    private EditText edtip;
    Handler mhandler;
    Handler mhandlerSend;
    boolean isRun = true;

    Button login;
    Button change;
    Button quit;

    SharedPreferences sp;
    private Context ctx;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    SocThread socketThread;

    private EditText accountEdit = null;
    private EditText pwdEdit=null;

    public String checked=null;

    private String ip=null;
    private String account;

    private CheckBox savepwd = null;


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
        setContentView(R.layout.activity_main);


        login = (Button) findViewById(R.id.login);
        change=(Button) findViewById(R.id.changepwd);
        quit=(Button) findViewById(R.id.quit);
        ctx = MainActivity.this;

        sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        sp.getString("ipstr", "");
        sp.getString("port", "");

        accountEdit = (EditText) findViewById(R.id.account);
        pwdEdit = (EditText) findViewById(R.id.pwd);
        edtip = (EditText) findViewById(R.id.editText1);

        savepwd=(CheckBox)findViewById(R.id.savepwd);

        //如果记住密码没有勾选，账户和密码清空
        if (sp.getBoolean("checkboxBoolean", false))
        {
            accountEdit.setText(sp.getString("user", null));
            pwdEdit.setText(sp.getString("pwd", null));
            edtip.setText(sp.getString("ip", null));
            savepwd.setChecked(true);
        }



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
                            if(s.indexOf("员工")>=0) {
                                MyLog.i(TAG,"员工成功登录");
                                Intent intent=new Intent(ctx,ScanActivity.class);
                                IpUser trans=new IpUser(ip,account);
                                Bundle bundle=new Bundle();
                                bundle.putSerializable("ipuser",trans);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }else if(s.indexOf("管理")>=0){
                                MyLog.i(TAG,"管理员成功登录");
                                Intent intent=new Intent(ctx,Check.class);
                                Bundle B = new Bundle();
                                B.putString("ipaddress", ip);
                                intent.putExtra("data", B);
                                startActivity(intent);
                                finish();
                            }else
                                Toast.makeText(ctx,s,Toast.LENGTH_SHORT).show();
                                MyLog.i(TAG, "更新UI完毕");
                        } else {
                            Log.i(TAG, "没有数据返回不更新");
                            MyLog.i(TAG, "没有数据返回不更新");
                            Toast.makeText(ctx,"没有数据返回",Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ee) {
                    MyLog.i(TAG, "加载过程出现异常");
                    ee.printStackTrace();
                    Toast.makeText(ctx,"加载过程出现异常",Toast.LENGTH_SHORT).show();
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
                        MyLog.i(TAG,"发送登录者信息成功");
                    } else {
                        MyLog.i(TAG,"与服务器连接失败");
                        Toast.makeText(MainActivity.this,"与服务器连接失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ee) {
                    MyLog.i(TAG, "加载过程出现异常");
                    ee.printStackTrace();
                }
            }
        };

        startSocket();

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                /*登录者身份对话框*/

                /*发送登录信息*/

                /*根据ip，port进行发送*/
                Log.i(TAG,"开始修改");
                ip = edtip.getText().toString();
                String port ="9000";
                Editor editor = sp.edit();
                editor.putString("ipstr", ip);
                editor.putString("port", port);
                editor.commit();
                Log.i(TAG, "保存成功"+sp.getString("ipstr", "")+";"+sp.getString("port", ""));
                /*发送内容*/
                MyLog.i(TAG, "准备发送登录者信息");
                account = accountEdit.getText().toString();
                String pwd = pwdEdit.getText().toString();
                boolean CheckBoxLogin = savepwd.isChecked();
                //保存信息
                if (CheckBoxLogin)
                {
                    MyLog.i(TAG,"保存登录者信息");
                    editor.putString("user", account);
                    editor.putString("pwd", pwd);
                    editor.putString("ip", ip);
                    editor.putBoolean("checkboxBoolean", true);
                    editor.commit();
                }
                else
                {
                    editor.putString("user", null);
                    editor.putString("pwd", null);
                    editor.putString("ip", null);
                    editor.putBoolean("checkboxBoolean", false);
                    editor.commit();
                }

                String check="user"+account+"pwd"+pwd;
                socketThread.Send(check);


            }
        });


          /*修改密码*/
        change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLog.i(TAG,"修改密码");
                startActivity(new Intent(ctx,ChangePwd.class));
                finish();

            }
        });
        quit.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {

               MyLog.i(TAG,"退出");
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
        MyLog.i(TAG, "start onStart");
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
        MyLog.i(TAG, "start onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "start onPause~~~");
        MyLog.i(TAG, "start onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "start onStop~~~");
        MyLog.i(TAG, "start onStop");
        stopSocket();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "start onDestroy~~~");
        MyLog.i(TAG, "start onDestroy");

    }









    }


