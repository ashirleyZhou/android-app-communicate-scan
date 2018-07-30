package test.com.exer;
import java.io.Serializable;

/**
 * Created by hp on 2017/6/30.
 */

public class IpResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String result;
    private String ip;

    public IpResult(String ip, String result) {
        this.ip=ip;
        this.result=result;
    }
    public String getIp(){
        return ip;
    }
    public String getResult(){return result;}
}
