package com.mj.sso.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author piggy
 * @desciption
 * @date 2021/11/9 - 0:47
 */
public class StringUtil {

    public static String getFirstSubStr(String str,String split){
        String regex="^.*?("+split+")";
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(str);
        while(m.find()){
            return m.group();
        }
        return null;
    }
}
