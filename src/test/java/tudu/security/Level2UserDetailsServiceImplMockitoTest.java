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
import tudu.service.impl.UserServiceImpl;

import static org.mockito.Mockito.when;

//Niveau2
public class Level2UserDetailsServiceImplMockitoTest {
    @Mock
	UserServiceImpl userService;
    @InjectMocks
    UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

    }

    @Test(expected = UsernameNotFoundException.class)
    /*
    * Simuler une levée d'exceptions - tester que la methode lève bien une UsernameNotFoundException si la méthode findBy lève une ObjectRetrievalFailureException
    * Méhode : loadUserByUsername

    */public void testLoadUserByUsername() {

        //given
        when(userService.findUser("test_user")).thenThrow(new ObjectRetrievalFailureException("simulee", null));
        //when
        UserDetails springSecurityUser = userDetailsService
                .loadUserByUsername("test_user");
    }
}
