package com.dupake.common.utils;//package com.dupake.common.utils;
//
//import org.apache.commons.lang3.StringUtils;
//
//
///**
// * IP工具类
// * @author Jill
// * @date 2019-07-03
// * @date 2019-07-03 14:07
// * @version 1.0
// */
//
//public class IPUtil {
//
//	private static final String UNKNOWN = "unknown";
//
//	protected IPUtil(){
//
//	}
//
//	/**
//	 * 获取 IP地址
//	 * 使用 Nginx等反向代理软件， 则不能通过 request.getRemoteAddr()获取 IP地址
//	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，
//	 * X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
//	 */
//	public static String getIpAddr(HttpServletRequest request) {
//	    // 从请求头上
//	    String ip = request.getParameter("bossLoginIp");
//
//        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader("X-Forwarded-For");
//        }
//
//		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//			ip = request.getHeader("X-Real-IP");
//		}
//		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//		}
//		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//		}
//		System.out.println("========>" + ip);
//		if (StringUtils.isNotEmpty(ip)){
//			String[] ipary = ip.split(",");
//			ip = ipary[0];
//		}
//		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
//	}
//
//}
