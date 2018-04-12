package com.example.administrator.shadowapplication.design_pattern.decorator;

/**
 * Author : shadow
 * Desc :客户端
 * Date :2018/4/12/012
 */

public class Client {
    public static void main(String[] args){
        YangGou yangGou = new YangGou();
        HongQiGong hongQiGong = new HongQiGong(yangGou);
        hongQiGong.attackMagic();//杨过会全真剑法，洪七公教杨过打狗棒，杨过会打狗棒
    }
}
