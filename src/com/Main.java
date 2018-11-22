package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.proxy.Action;
import com.proxy.IProxy;
import com.proxy.dynamicproxy.ProxyHandler;
import com.proxy.staticproxy.StaticProxy;

public class Main {

    @Test
    public void testInheritance() {
        ActionImpl actionImpl = new ActionImpl();
    }
    
    public boolean isPattern(CharSequence str, String pattern) {
        Pattern pt = Pattern.compile(pattern);
        Matcher mt = pt.matcher(str);
        return mt.matches();
    }
    
    @Test
    public void testRegEx() {
       boolean result = isPattern("18668194450", "^1(3[0-9]|4[579]|5[^4]|6[6]|7[0135678]|8[0-9]|9[89])\\d{8}$");
       System.out.println(result);
       
       result = isPattern("11a", "^[0-9]*$");
       System.out.println(result);
    }
    
    @Test
    public void staticProxy() {
        IProxy staticProxy = new StaticProxy(new Action());
        staticProxy.request();
    }
    
    @Test
    public void dynamicProxy() {
        IProxy dynamicProxy = ProxyHandler.newDynamicProxy(new Action());
        dynamicProxy.request();
    }

}
