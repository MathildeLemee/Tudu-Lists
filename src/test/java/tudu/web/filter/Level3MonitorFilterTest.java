package tudu.web.filter;

import org.junit.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class Level3MonitorFilterTest {


 //   MonitorFilter monitor = new MonitorFilter();

    /*
 Faire 2 test pour verifier ces deux conditions
  *               if (principal instanceof String) {
                      userName = "anonymous";
                  } else {
                      User springSecurityUser = (User) principal;
                      userName = springSecurityUser.getUsername();
                  }

   *
   *refactoring
   *

    @Test
    public void test_authentication_1() throws IOException, ServletException {


        Authentication auth = new TestingAuthenticationToken("foo", "bar") {
            @Override
            public Object getPrincipal() {
                org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User("foo", "toto", false, false, false, false, new ArrayList<GrantedAuthority>());

                return user;    //To change body of overridden methods use File | Settings | File Templates.
            }
        };
        auth.setAuthenticated(true);
        SecurityContext context = new SecurityContextImpl();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);


        SecurityContextHolder.setContext(context);

        monitor.doFilter(mock(ServletRequest.class), mock(ServletResponse.class), mock(FilterChain.class));
    }*/
}
