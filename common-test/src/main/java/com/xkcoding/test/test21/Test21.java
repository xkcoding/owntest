package com.xkcoding.test.test21;

import cn.hutool.http.webservice.SoapClient;
import com.google.common.base.Charsets;

import javax.xml.namespace.QName;

/**
 * <p>
 * 测试 hutool 的 SoapClient
 * </p>
 *
 * @package: com.xkcoding.test.test21
 * @description: 测试 hutool 的 SoapClient
 * @author: yangkai.shen
 * @date: Created in 2019-04-09 13:54
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test21 {
    public static void main(String[] args) {
        SoapClient soapClient = SoapClient.create("http://192.168.40.32:8083/ivrzzyy/webservice/soap");
        soapClient.setMethod(new QName("http://webservice.IVRZzyy.mchz.com/","queryData","web"));
        soapClient.setParam("arg0", "12#6123904#",false).setCharset(Charsets.UTF_8);
        String msgStr = soapClient.getMsgStr(true);
        System.out.println(msgStr);
        String send = soapClient.send(true);
        System.out.println(send);
    }
}
