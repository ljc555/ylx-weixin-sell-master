package com.ylx.service;

import com.ylx.dataobject.SellerInfo;

/**
 * 卖家端
 * Created by LJH
 * 2018-10-29 23:14
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
