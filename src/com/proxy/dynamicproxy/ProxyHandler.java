package com.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.proxy.IProxy;

/**
 * 指定运行时将生成的代理类需要完成的具体任务（包括Preprocess和Postprocess),
 * 即代理类调用任何方法都会经过这个调用处理器类
 * 
 * 优点: 代理类的实现是有很多共性的（重复代码），动态代理的好处在于避免了这些重复代码，只需要关注操作。
 * 缺点: 动态生成的代理类继承java.lang.reflect.Proxy, 因Java单继承特性，只能针对接口创建代理类，不能针对类创建代理类。
 */
public class ProxyHandler implements InvocationHandler{
    
    private IProxy proxyInstance;
    
    private ProxyHandler(IProxy proxy) {
        this.proxyInstance = proxy;
    }

    public static IProxy newDynamicProxy(IProxy proxy) {
        //代理类是在运行时生成的。也就是说 Java 编译完之后并没有实际的 class 文件，而是在运行时动态生成的类字节码，并加载到JVM中
        return (IProxy) Proxy.newProxyInstance(
                proxy.getClass().getClassLoader(), //指定哪个类加载器来加载这个代理类到 JVM 的方法区
                proxy.getClass().getInterfaces(), //代理类需要实现哪些接口
                new ProxyHandler(proxy)); //调用处理器类实例（指定代理类中具体要干什么）
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        try {
            //定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
            System.out.println("before method " + method.getName());
            result = method.invoke(proxyInstance, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                                       e.getMessage());
        } finally {
            System.out.println("after method " + method.getName());
        }
        return result;
    }

}
