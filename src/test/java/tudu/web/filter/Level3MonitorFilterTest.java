package tudu.web.filter;

import org.junit.Test;

import javax.servlet.ServletException;
import java.io.IOException;

public class Level3MonitorFilterTest {

    /*
  Verifier que l on passe bien dans les branches
   *               if (principal instanceof String) {
                       userName = "anonymous";
                   } else {
                       User springSecurityUser = (User) principal;
                       userName = springSecurityUser.getUsername();
                   }

    *
    * Duree max : 10 minutes
    */
    @Test
    public void test_authentication_1() throws IOException, ServletException {
    }
/*
il suffit de refactorer le code parent, l appel a la librairie Spring Security (SecuritContext.xxxx doit etre
effectuer dans un service a part bien distinct.

Une idee tres rapide de ce que l on peut avoir envie de faire et que l on ne doit pas faire ...

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
