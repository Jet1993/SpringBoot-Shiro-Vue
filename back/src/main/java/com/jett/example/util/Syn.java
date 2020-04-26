package com.jett.example.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ZhaoJianTao
 * @Description
 * @CreationDate 2020/4/6
 */
public class Syn {
    public void syn(){
        ReentrantLock reentrantLock=new ReentrantLock();

        synchronized (this){
            System.out.println("ahsf");
        }
    }

    public static void main(String[] args) {
        String a="aaaa";
        String b="aaaa";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
}
