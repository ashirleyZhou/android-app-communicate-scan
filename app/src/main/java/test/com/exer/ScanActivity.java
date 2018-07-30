package test.com.exer;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import android.view.View.OnClickListener;
import android.widget.Toast;





import test.com.exer.zxing.android.CaptureActivity;


public class ScanActivity extends AppCompatActivity implements OnClickListener {


    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private int visible;
    private String ip;
    private String account;

    private long exitTime = 0;
    private String TAG = "===scan_or_manual===";

    private static final int REQUEST_CODE_SCAN = 0x0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);
        initView();
        Intent i=getIntent();
        Bundle b=new Bundle();
        b=i.getExtras();
        IpUser info=(IpUser)b.getSerializable("ipuser");
        ip=info.getIp();
        account=info.getAccount();

    }

    private void initView() {
        Button scanBtn;
        Button input;

        scanBtn = (Button) findViewById(R.id.scanBtn);
        input=(Button) findViewById(R.id.inputorder);

        scanBtn.setOnClickListener(ScanActivity.this);
        input.setOnClickListener(ScanActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanBtn:

                visible=0;
                MyLog.i(TAG, "扫描");
                Intent intent = new Intent(ScanActivity.this,CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
                break;

            case R.id.inputorder:

                visible=1;
                MyLog.i(TAG, "手动输入");
                Intent intentinput = new Intent(ScanActivity.this,Client.class);
                Info info=new Info(ip,"",visible,account);
                Bundle bundle=new Bundle();
                bundle.putSerializable("info",info);
                intentinput.putExtras(bundle);
                startActivity(intentinput);
                finish();
                break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            //扫描成功后的操作
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                MyLog.i(TAG,"扫描成功，得到订单号");
                String content = data.getStringExtra(DECODED_CONTENT_KEY); //扫描得到的结果
                Info info=new Info(ip,content,visible,account);
                Bundle bundle=new Bundle();
                bundle.putSerializable("info",info);

                Intent intent = new Intent(this,Client.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        }


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





