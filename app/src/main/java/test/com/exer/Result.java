package test.com.exer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Result extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);


        //服务端返回具体信息
        Intent i = getIntent();
        String result = i.getStringExtra("data");

        //定义
        TextView time = (TextView) findViewById(R.id.time);
        TextView fengang = (TextView) findViewById(R.id.fengang);
        TextView tiaose = (TextView) findViewById(R.id.tiaose);
        TextView zaolixiaoshi = (TextView) findViewById(R.id.zaolixiaoshi);
        TextView zaoli = (TextView) findViewById(R.id.zaoli);
        TextView zonghunxiaoshi = (TextView) findViewById(R.id.zonghunxiaoshi);
        TextView zonghun = (TextView) findViewById(R.id.zonghun);
        TextView tiaozheng = (TextView) findViewById(R.id.tiaozheng);
        TextView banmian = (TextView) findViewById(R.id.banmian);
        TextView dengniandu = (TextView) findViewById(R.id.dengniandu);
        TextView date = (TextView) findViewById(R.id.date);
        TextView operator = (TextView) findViewById(R.id.operator);

        //截取信息中具体属性
        int tstart = result.indexOf("总时间");
        int tend = result.indexOf("分缸");
        String t=result.substring(tstart + 3, tend);
        time.setText(result.substring(tstart + 3, tend));
        int fgend = result.indexOf("调色");
        String fg=result.substring(tend + 2, fgend);
        fengang.setText(result.substring(tend + 2, fgend));
        int tsend = result.indexOf("造粒小试");
        String ts=result.substring(fgend + 2, tsend);
        tiaose.setText(result.substring(fgend + 2, tsend));
        int zlxsend = result.indexOf("造粒 ");
        String zlxs=result.substring(tsend+4,zlxsend);
        zaolixiaoshi.setText(result.substring(tsend+4,zlxsend));
        int zlend = result.indexOf("总混小试");
        zaoli.setText(result.substring(zlxsend + 3, zlend));
        int zhxsend = result.indexOf("总混 ");
        zonghunxiaoshi.setText(result.substring(zlend + 4, zhxsend));
        int zhend = result.indexOf("测试调整");
        String zh=result.substring(zhxsend + 3, zhend);
        zonghun.setText(result.substring(zhxsend + 3, zhend));
        int tzend = result.indexOf("板面PASS");
        String tz=result.substring(zhend+4,tzend);
        tiaozheng.setText(result.substring(zhend+4,tzend));
        int bmend = result.indexOf("等粘度");
        banmian.setText(result.substring(tzend + 6, bmend));
        int dndend = result.indexOf("下单日期");
        dengniandu.setText(result.substring(bmend + 3, dndend));
        int dend = result.indexOf("操作员");
        date.setText(result.substring(dndend + 4, dend));
        operator.setText(result.substring(dend+3));
    }
}


