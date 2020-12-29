package com.wangyz.httpdns;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dns;

public class AliDns implements Dns {

    private HttpDnsService httpDns;

    public AliDns(Context context) {
        httpDns = HttpDns.getService(context, "account_id");
    }

    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        //通过异步解析接口获取ip
        String ip = httpDns.getIpByHostAsync(hostname);
        //Android9.0系统及以后版本，https请求无法直接访问，方便起见，直接在AndroidManifest.xml中配置android:usesCleartextTraffic="true"
        if (ip != null) {
            //如果ip不为null，直接使用该ip进行网络请求
            Log.e("AliDns", "ip:" + ip);
            List<InetAddress> inetAddresses = Arrays.asList(InetAddress.getAllByName(ip));
            return inetAddresses;
        }
        //如果返回null，走系统DNS服务解析域名
        return Dns.SYSTEM.lookup(hostname);
    }
}
