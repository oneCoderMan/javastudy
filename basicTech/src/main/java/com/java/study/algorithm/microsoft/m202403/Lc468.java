package com.java.study.algorithm.microsoft.m202403;

/**
 * @Author： yijun
 * @DATE: 2024/3/28 21:37
 * @Description
 * https://leetcode.cn/problems/validate-ip-address/
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；
 * 如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的 IP 地址，返回 "Neither" 。
 *
 * 有效的ipv4地址：x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零
 *
 * 有效的ipv6地址：x1:x2:x3:x4:x5:x6:x7:x8 的IP地址  1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )
 * 在 xi 中允许前导零。
 */
public class Lc468 {
    public static void main(String[] args) {
        Lc468 lc468 = new Lc468();
        String test = "20...";
        String re = lc468.validIPAddress(test);
        System.out.println(re);
    }
    public String validIPAddress(String queryIP) {
        if (queryIP == null || "".equals(queryIP)) {
            return "Neither";
        }
        if (queryIP.contains(".")) {
            if (isIpv4(queryIP)) {
                return "IPv4";
            }
            return "Neither";
        }
        if (queryIP.contains(":")) {
            if (isIpv6(queryIP)) {
                return "IPv6";
            }
            return "Neither";
        }
        return "Neither";
    }

    private boolean isIpv6(String queryIP) {
        // 注意需要保留空字符串
        String[] split = queryIP.split(":", -1);
        if (split.length != 8) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            if (!isIpv6Segment(split[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isIpv6Segment(String seg) {
        if (seg == null || "".equals(seg)) {
            return false;
        }
        if (seg.length() > 4) {
            return false;
        }
        for (int i = 0; i < seg.length(); i++) {
            char cur = seg.charAt(i);
            if ((cur >= '0' && cur <= '9') || (cur >= 'a' && cur <= 'f')
                    || (cur >= 'A' && cur <= 'F')) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isIpv4(String queryIP) {
        String[] split = queryIP.split("\\.", -1);
        if (split.length != 4) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (!isIpv4Segment(split[i])) {
                return false;
            }
        }
        return true;
    }
    private boolean isIpv4Segment(String seg) {
        if (seg == null || "".equals(seg)) {
            return false;
        }
        if (seg.charAt(0) == '0' && seg.length() > 1) {
            return false;
        }
        // 解析出数字
        int num = 0;
        // 判断一下不能超长
        if (seg.length() >= 4) {
            return false;
        }
        for (int i = 0; i < seg.length(); i++) {
            if (seg.charAt(i) >= '0' && seg.charAt(i) <= '9') {
                num = num * 10 + (seg.charAt(i) - '0');
            } else {
                return false;
            }
        }
        if (num > 255) {
            return false;
        }
        return true;
    }
}
