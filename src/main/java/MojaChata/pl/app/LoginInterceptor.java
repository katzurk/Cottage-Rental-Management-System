package MojaChata.pl.app;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UrlPathHelper;

import MojaChata.pl.app.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String lookupPathForRequest = urlPathHelper.getLookupPathForRequest(request);
        // logger.info("----- login interceptor called. path: " + lookupPathForRequest);


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loggedInUser");
        if (pathRequiresUser(lookupPathForRequest) && user == null) {
                // logger.info("----- login interceptor. path requires user: " + lookupPathForRequest + " and user is missing");
                session.setAttribute("pathBeforeLogin", lookupPathForRequest);
                response.sendRedirect("/login");
                return false; // handled
        }
        if(user != null) {
            String originalPath = (String) session.getAttribute("pathBeforeLogin");
            if (originalPath != null) {
                // logger.info("----- login interceptor. user is present: " + user + ". and original path is: " + originalPath);
                session.removeAttribute("pathBeforeLogin");
                response.sendRedirect(originalPath);
                return false; // handled
            }
        }

        return true; // continue processing
    }

    private boolean pathRequiresUser(String path) {
        return "/addcottage".equals(path)
            || "/reservations".equals(path)
            || path.matches("^/edit/.*")
            || path.matches("^/request/.*")
            || path.matches(".*/delete/.*")
            || path.matches(".*/reviews/addReview$");
    }
}
