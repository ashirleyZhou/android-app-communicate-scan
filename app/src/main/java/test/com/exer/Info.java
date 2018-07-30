package test.com.exer;
import java.io.Serializable;

/**
 * Created by hp on 2017/6/30.
 */

public class Info implements Serializable {
    private static final long serialVersionUID = 1L;
    private String account;
    private String ip;
    private String result;
    private int visible;
    public Info(String ip,String result,int visible,String account) {
        this.ip=ip;
        this.result=result;
        this.visible=visible;
        this.account=account;
    }
    public String getIp(){
        return ip;
    }
    public String getResult(){
        return result;
    }
    public int getVisible(){
        return visible;
    }
    public String getAccount(){return account;}
}
