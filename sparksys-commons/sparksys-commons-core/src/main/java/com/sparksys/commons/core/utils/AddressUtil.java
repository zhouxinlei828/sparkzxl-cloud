package com.sparksys.commons.core.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sparksys.commons.core.api.result.ApiResult;
import com.sparksys.commons.core.entity.AddressInfo;

import java.util.HashMap;

/*
 * description：
 *
 * @author zhouxinlei
 * @date  2020/6/17 0017
 */
public class AddressUtil {

    private final static String IP_API_URL = "http://ip.taobao.com/outGetIpInfo";

    public static AddressInfo getAddress(String ip) {
        HashMap<String, Object> paramMap = new HashMap<>(2);
        paramMap.put("ip", ip);
        paramMap.put("accessKey", "alibaba-inc");
        String result = HttpUtil.get(IP_API_URL, paramMap);
        ApiResult apiResult = JSONUtil.toBean(result, ApiResult.class);
        if (apiResult.getCode() == 0) {
            JSONObject json = JSONUtil.parseObj(apiResult.getData(), false, true);
            return json.toBean(AddressInfo.class);
        }
        return null;
    }


    public static void main(String[] args) {
        String ip = "125.118.83.91";
        AddressInfo addressInfo = getAddress(ip);
        System.out.println(addressInfo.getRegion() + addressInfo.getCity());
    }
}
