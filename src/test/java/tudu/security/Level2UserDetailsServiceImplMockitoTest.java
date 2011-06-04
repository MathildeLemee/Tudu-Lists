package tudu.security;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import tudu.service.UserService;

import static org.mockito.Mockito.when;

//Niveau2
public class Level2UserDetailsServiceImplMockitoTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

    }

    @Test(expected = UsernameNotFoundException.class)
    //niveau 2 - simuler une levee d exceptions - tester que la methode leve bien une UsernameNotFoundException si la methode findBy leve une ObjectRetrievalFailureException
    public void testLoadUserByUsername() {

        //given
        when(userService.findUser("test_user")).thenThrow(new ObjectRetrievalFailureException("simulee", null));
        //when
        UserDetails springSecurityUser = userDetailsService
                .loadUserByUsername("test_user");
    }
}
