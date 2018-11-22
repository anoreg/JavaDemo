package com.proxy.staticproxy;

import com.proxy.IProxy;

/**
 * 代理类是在编译时就实现好的。也就是说 Java 编译完成后代理类是一个实际的 class 文件
 */
public class StaticProxy implements IProxy{
    
    IProxy proxy;
    
    public StaticProxy(IProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void request() {
        System.out.println("PreProcess");
        proxy.request();
        System.out.println("PostProcess");
    }
    
}
