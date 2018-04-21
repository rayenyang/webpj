package com.rayenyang.webpj.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rayenyang on 2017/1/13.
 */

public class CommonTest implements Cloneable {
    
    public static final Log logger = LogFactory.getLog(CommonTest.class);
    
    public static void main(String[] args) throws Exception {
//        final long start = System.nanoTime();
//        System.out.println(start);
//        System.out.println(start - 30 * 60 * 1000);
//        Executors.newSingleThreadScheduledExecutor();
//        final OperatingSystemMXBean platformMXBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
//        for(;;){
//            System.out.println(platformMXBean.getProcessCpuLoad());
//            System.out.println(platformMXBean.getSystemCpuLoad());
//            Thread.sleep(1000);：
//            System.out.println("-------------------------------------------");
//        }
        System.out.println(14 ^ 3);
        System.out.println(3 ^ 14);
        System.out.println(3 ^ 3);
        System.out.println(0 ^ 3);
    }
    
    
    /**
     * 上传文件编码判断
     */
    public static String get_charset(File file) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        try {
            boolean checked = false;
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE
                    && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF
                    && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                // int len = 0;
                int loc = 0;
                
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
                            // (0x80
                            // - 0xBF),也可能在GB编码内
                            continue;
                        else
                            break;
                    } else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else
                                break;
                        } else
                            break;
                    }
                }
                
            }
            
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return charset;
    }
    
    
    static class LruCache<K, V> extends LinkedHashMap<K, V> {
        private int size;
        
        public LruCache(int size) {
            super(16, 1, true);
            this.size = size;
        }
        
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > size;
        }
    }
    
    private static <T> T[] createArray(T t) {
        T[] ts = (T[]) Array.newInstance(t.getClass(), 10);
        for (int i = 0; i < ts.length; i++) {
            ts[i] = null;
        }
        return ts;
    }
}

interface I {
    void test();
}

class Impl1 implements I {
    private String name = "Impl1";
    
    @Override
    public String toString() {
        return "Impl1{" +
                "name='" + name + '\'' +
                '}';
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void test() {
    
    }
}

class Impl2 implements I {
    private String name = "Impl2";
    private String name2;
    
    @Override
    public String toString() {
        return "Impl2{" +
                "name='" + name + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }
    
    public String getName2() {
        return name2;
    }
    
    public void setName2(String name2) {
        this.name2 = name2;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void test() {
    }
}

class T1 {
    private int i = 10;
    
    public int getI() {
        return i;
    }
    
    public void setI(int i) {
        this.i = i;
    }
    
    
}

class T2 extends T1 {
    public T1 getT2() {
        return this;
    }
}

