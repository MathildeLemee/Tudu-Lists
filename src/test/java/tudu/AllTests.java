package tudu;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tudu.domain.comparator.*;
import tudu.domain.model.TodoTest;
import tudu.integration.IntegrationTest;
import tudu.security.Level1UserDetailsServiceImplMockitoTest;
import tudu.security.Level2AttentionMockitoTest;
import tudu.security.Level2UserDetailsServiceImplMockitoTest;
import tudu.security.UserDetailsServiceImplTest;
import tudu.service.impl.*;
import tudu.web.dwr.impl.TodoListsDwrImplTest;
import tudu.web.dwr.impl.TodosDwrImplTest;
import tudu.web.servlet.BackupServletTest;
import tudu.web.servlet.RssFeedServletTest;

/**
 * Test Suite that run all the project tests.
 *
 * @author Julien Dubois
 */
@RunWith(Suite.class)
@SuiteClasses(value={
        TodoTest.class,
        TodoByAssignedUserAscComparatorTest.class,
        TodoByAssignedUserComparatorTest.class,
        TodoByDescriptionAscComparatorTest.class,
        TodoByDescriptionComparatorTest.class,
        TodoByDueDateAscComparatorTest.class,
        TodoByDueDateComparatorTest.class,
        TodoByPriorityAscComparatorTest.class,
        IntegrationTest.class,
        UserDetailsServiceImplTest.class,
        ConfigurationServiceImplTest.class,
        TodoListsServiceImplTest.class,
        TodosServiceImplTest.class,
        Level1UserDetailsServiceImplMockitoTest.class,
        Level1UserServiceImplMockitoTest.class,
        Level1UserDetailsServiceImplMockitoTest.class,
        Level2UserServiceImplMockitoTest.class,
        Level2UserDetailsServiceImplMockitoTest.class,
        Level3UserServiceImplMockitoTest.class,
        Level3UserServiceImplMockitoTest.class,
        Level2AttentionMockitoTest.class,
        TodoListsDwrImplTest.class,
        TodosDwrImplTest.class,
        BackupServletTest.class,
        RssFeedServletTest.class
})
public class AllTests {
}
