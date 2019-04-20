package com.edu.sicnu.cs.zzy.cirdealtest;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CIRdeal {
    // Android 4.4之后 红外遥控ConsumerIrManager
    private static ConsumerIrManager mCIR;
    //引导码
    private static int startH = 9000;
    private static int startL = 4500;

    private static int high8 = 560;
    //0：1125
    private static int low0 = 560;
    //1：2250
    private static int low1 = 1690;

    //用户编码高八位80
    private static String userH = "00000000";
    //用户编码低八位18
    private static String userL = "11111111";

    //38kHz
    private static int carrierFrequency = 38000;
    private static int[] pattern;

    private static List<Integer> list = new ArrayList<>();

    /**
     * 正常发码：引导码（9ms+4.5ms）+用户编码（低八位）+用户编码（高八位）+键数据码+键数据反码
     */
    public static void transmitKey(Context context,String key, String key2) {
        checkCIR(context);
        if (!mCIR.hasIrEmitter()) {
            Toast.makeText(context,"未找到红外发射器",Toast.LENGTH_SHORT).show();
        } else {
            list.clear();
            //引导码
            list.add(startH);
            list.add(startL);
            //用户编码
            change(userH);
            change(userL);
            //键数据码
            change(key);
            //键数据反码
            change(key2);

            //添加连续码
            list.add(9000);
            list.add(2250);
            list.add(2250);
            list.add(94000);

            int size = list.size();
            pattern = new int[size];
            for (int i = 0; i < size; i++) {
                pattern[i] = list.get(i);
            }

            //发送红外信号
            mCIR.transmit(carrierFrequency, pattern);
        }
    }

    /**
     * 二进制转成电平
     *
     * @param code
     */
    public static void change(String code) {
        int len = code.length();
        String part;
        for (int i = 0; i < len; i++) {
            list.add(high8);
            part = code.substring(i, i + 1);
            if (part.equals("0"))
                list.add(low0);
            else
                list.add(low1);
        }
    }




    /**
     * 检测手机是否有红外功能
     */
    public static void checkCIR(Context context) {
        if (null == mCIR) {
            // 获取系统的红外遥控服务
            mCIR = (ConsumerIrManager) context.getSystemService(Context.CONSUMER_IR_SERVICE);
        }
        if (!mCIR.hasIrEmitter()) {
            Toast.makeText(context,"未找到红外发射器",Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(context,"存在红外发射器",Toast.LENGTH_SHORT).show();
        }
    }


}