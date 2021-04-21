package ro.gini.iordache.security.handler;

import com.gini.iordache.cache.MiniCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CacheLogoutHandler implements LogoutHandler {

    @Autowired
    private MiniCache miniCache;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        var username = SecurityContextHolder.getContext().getAuthentication().getName();

        miniCache.getOrder().remove(username);
        miniCache.getLabors().remove(username);
        miniCache.getOrderLabors().remove(username);
        miniCache.getParts().remove(username);
        miniCache.getVehicle().remove(username);
        miniCache.getPerson().remove(username);
        miniCache.getCompany().remove(username);
    }
}
