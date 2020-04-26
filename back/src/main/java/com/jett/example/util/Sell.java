package com.jett.example.util;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;


/**
 *功能描述: 动态代理测试
 * @author zhaoJianTao
 * @date 2020/4/16
 * @param
 * @return
 */
public interface Sell {//接口
    void sale();
}
class SellWine implements Sell {//实现接口，要做的工作
    @Override
    public void sale() {
        System.out.println("出售葡萄酒");
    }
}

class SellProxy implements InvocationHandler {//jdk动态代理类，在工作开始和结束要执行的任务
    private Object object;
    public SellProxy(Object object) {
        this.object = object;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("销售开始，商店是：  "+this.getClass().getSimpleName()+"");
        method.invoke(object,objects);
        System.out.println("销售结束！");
        return null;
    }
}

class SellCglib implements MethodInterceptor{
    private Object target;
    /**
     * 创建代理实例
     * @param target 这里的目标类型为Object，则可以接受任意一种参数作为被代理类，实现了动态代理
     * @return
     */
    public Object getInstance(Object target){
        // 设置需要创建子类的类
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("销售开始。。。");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("销售结束。。。");
        return result;
    }
}

class Demo{
    public static void main(String[] args) {
        //jdk动态代理
//        SellWine sellWine=new SellWine();
//        InvocationHandler invocationHandler = new SellProxy(sellWine);
//        Sell sell  = (Sell) Proxy.newProxyInstance(SellWine.class.getClassLoader(), SellWine.class.getInterfaces(), invocationHandler);
//        sell.sale();

        //cglib动态代理
        SellCglib sellCglib=new SellCglib();
        SellWine instance = (SellWine) sellCglib.getInstance(new SellWine());
        instance.sale();

    }
}
