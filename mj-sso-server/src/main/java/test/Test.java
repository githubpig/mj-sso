package test;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author piggy
 * @desciption
 * @date 2021/11/8 - 9:51
 */
public class Test {

    public static void main(String[] args) {
        //2a13c9c8dac79245bc19b2bcf65e0695
        String md5Pwd = new Md5Hash("zyw" + "admin123" + "b43b0a").toHex();
        System.out.println(md5Pwd);
    }
}
