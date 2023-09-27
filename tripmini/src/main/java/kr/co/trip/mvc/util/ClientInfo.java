package kr.co.trip.mvc.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ClientInfo {
    /**
     * 클라이언트의 IP 주소를 가져옴
     * @return ip 주소
     */
    public String getIpAddr() {
        String ip_addr = null;
        
        // RequestContextHolder를 사용하여 현재 요청의 ServletRequestAttributes를 가져옴
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        
        // X-Forwarded-For 헤더를 통해 프록시 서버를 통한 클라이언트 IP를 가져옴
        ip_addr = request.getHeader("X-Forwarded-For");
        
        // 만약 X-Forwarded-For 헤더가 없다면, 다른 헤더나 요청 객체를 통해 IP 주소를 가져옴
        if (ip_addr == null) {
            ip_addr = request.getHeader("Proxy-Client-IP");
        }
        if (ip_addr == null) {
            ip_addr = request.getHeader("WL-Proxy-Client-IP"); 
        }
        if (ip_addr == null) {
            ip_addr = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip_addr == null) {
            ip_addr = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip_addr == null) {
            ip_addr = request.getRemoteAddr(); // IP 주소를 가져옴
        }
        
        return ip_addr;
    }
}
