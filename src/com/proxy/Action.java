package com.proxy;

import com.proxy.IProxy;

public class Action implements IProxy {
 
    @Override
    public void request() {
        System.out.println("request network");
    }

}
