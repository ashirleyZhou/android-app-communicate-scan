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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;



import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;




public class Client extends Activity {
	private String TAG = "===Client===";


	private TextView scanresult=null;
	private TextView scanview=null;
	private TextView orderview=null;
	private String ip;
	private int visible;
	private String account;

	/*工位选择初始化*/
	private static final String[] m={"分缸","调色","造粒","总混","板面PASS","等粘度","结束"};
	private TextView choice ;
	private Spinner spinner;
	private ArrayAdapter<String> adapter;
	private String process;

	Handler mhandler;
	Handler mhandlerSend;
	boolean isRun = true;

    private EditText orderedit=null;
	Button btnsend;
	Button cancel;
	Button delete;
	private String result; //发送内容



	SharedPreferences sp;
	private Context ctx;
	Socket socket;
	PrintWriter out;
	BufferedReader in;
	SocThread socketThread;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.client);

		scanview=(TextView) findViewById(R.id.scanview);
		orderview=(TextView) findViewById(R.id.orderview);
		orderedit =(EditText) findViewById(R.id.order);


		choice = (TextView) findViewById(R.id.spinnerText);
		choice.setText("工位：");
		spinner = (Spinner) findViewById(R.id.Spinner01);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				process=m[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		spinner.setVisibility(View.VISIBLE);


		/*接收ip和扫描结果scanresult*/
		Intent i=getIntent();
		Bundle b=new Bundle();
		b=i.getExtras();
		Info info=(Info)b.getSerializable("info");
		scanresult = (TextView) findViewById(R.id.scanresult);
		scanresult.setText(info.getResult());
		ip=info.getIp();
		visible=info.getVisible();
		account=info.getAccount();

        /*每次只发送一个数据，手动输入的订单号或扫描结果*/
		if(visible==0){
			orderview.setVisibility(View.INVISIBLE);
			orderedit.setVisibility(View.INVISIBLE);
		}else if(visible==1){
			scanview.setVisibility(View.INVISIBLE);
		}


		btnsend = (Button) findViewById(R.id.button1);
		cancel=(Button) findViewById(R.id.quit);
		delete=(Button) findViewById(R.id.delete);

		ctx = Client.this;

		sp = this.getSharedPreferences("SP", MODE_PRIVATE);
		sp.getString("ipstr", "");
		sp.getString("port", "");


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
							/*确定服务端回复的内容*/
							AlertDialog.Builder confirm=new AlertDialog.Builder(ctx);
							confirm.setTitle("服务端回复：");
							confirm.setMessage(s);
							confirm.setPositiveButton("确定",null);
							confirm.show();
							MyLog.i(TAG, "服务端回复："+s);
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
						MyLog.i(TAG, "发送工位信息成功" );
					} else {
						MyLog.i(TAG, "与服务端连接失败" );
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
				String port = "9000";
				Editor editor = sp.edit();
				editor.putString("ipstr", ip);
				editor.putString("port", port);
				editor.commit();//保存新数据
				Log.i(TAG, "保存成功"+sp.getString("ipstr", "")+";"+sp.getString("port", ""));
				MyLog.i(TAG, "准备发送工位信息");
				String order = orderedit.getText().toString();//订单号
				if(visible==0){
					result="order"+scanresult.getText().toString()+"工位"+process+"account"+account;
				}else if(visible==1){
					result="order"+order+"工位"+process+"account"+account;
				}
				socketThread.Send(result);

			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyLog.i(TAG, "取消按钮按下，返回扫描页");
                Intent intent=new Intent(ctx,ScanActivity.class);
				IpUser trans=new IpUser(ip,account);
				Bundle bundle=new Bundle();
				bundle.putSerializable("ipuser",trans);
				intent.putExtras(bundle);
                startActivity(intent);
				finish();
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//发送删除指令
				Log.i(TAG,"开始修改");
				String port = "9000";
				Editor editor = sp.edit();
				editor.putString("ipstr", ip);
				editor.putString("port", port);
				editor.commit();//保存新数据
				Log.i(TAG, "保存成功"+sp.getString("ipstr", "")+";"+sp.getString("port", ""));
				MyLog.i(TAG, "准备删除数据");
				String order = orderedit.getText().toString();//订单号
				if(visible==0){
					result=scanresult.getText().toString()+"工位"+process;
				}else if(visible==1){
					result=order+"工位"+process;
				}
				socketThread.Send("delete:"+result+"account"+account);

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

			MyLog.i(TAG, "返回键按下，返回扫描页");
			Intent intent=new Intent(ctx,ScanActivity.class);
			IpUser trans=new IpUser(ip,account);
			Bundle bundle=new Bundle();
			bundle.putSerializable("ipuser",trans);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();


			return false;
		}
		return super.onKeyDown(keyCode, event);

	}
}
