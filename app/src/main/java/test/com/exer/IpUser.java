package test.com.exer;
import java.io.Serializable;

/**
 * Created by hp on 2017/6/30.
 */

public class IpUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String account;
    private String ip;

    public IpUser(String ip,String account) {
        this.ip=ip;
        this.account=account;
    }
    public String getIp(){
        return ip;
    }
    public String getAccount(){return account;}
}
