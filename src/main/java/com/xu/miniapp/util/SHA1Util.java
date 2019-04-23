package com.xu.miniapp.util;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author CharleyXu Created on 2019/4/23.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SHA1Util {

    public static String encrypt(String... arr) {
        if (StringUtils.isAnyEmpty(arr)) {
            throw new IllegalArgumentException("非法请求参数，有部分参数为空 : " + Arrays.toString(arr));
        } else {
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            String[] var2 = arr;
            int var3 = arr.length;
            for (int var4 = 0; var4 < var3; ++var4) {
                String a = var2[var4];
                sb.append(a);
            }
            return DigestUtils.sha1Hex(sb.toString());
        }
    }

}
