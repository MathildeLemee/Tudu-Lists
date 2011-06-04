package tudu;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tudu.security.Level1UserDetailsServiceImplMockitoTest;
import tudu.security.Level2AttentionMockitoTest;
import tudu.security.Level2UserDetailsServiceImplMockitoTest;
import tudu.service.impl.Level1UserServiceImplMockitoTest;
import tudu.service.impl.Level2UserServiceImplMockitoTest;
import tudu.service.impl.Level3UserServiceImplMockitoTest;
import tudu.web.filter.Level3MonitorFilterTest;
import tudu.web.mvc.Level1AdministrationControllerTest;
import tudu.web.mvc.Level2AdministrationControllerTest;
import tudu.web.mvc.Level3AdministrationControllerTest;

/**
 * Test Suite that run all the project tests.
 *
 * @author Julien Dubois
 */
@RunWith(Suite.class)
@SuiteClasses(value = {
        Level1UserDetailsServiceImplMockitoTest.class,
        Level1UserServiceImplMockitoTest.class,
        Level1AdministrationControllerTest.class,
        Level2UserServiceImplMockitoTest.class,
        Level2UserDetailsServiceImplMockitoTest.class,
        Level2AttentionMockitoTest.class,
        Level2AdministrationControllerTest.class,
        Level3UserServiceImplMockitoTest.class,
        Level3UserServiceImplMockitoTest.class,
        Level3AdministrationControllerTest.class,
        Level3MonitorFilterTest.class

})
public class AllTests {
}
