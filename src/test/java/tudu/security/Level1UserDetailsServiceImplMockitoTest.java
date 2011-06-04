package tudu.security;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import tudu.domain.Role;
import tudu.domain.RolesEnum;
import tudu.domain.User;
import tudu.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

//Niveau1
public class Level1UserDetailsServiceImplMockitoTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

    }

    /*
    niveau 1
    verifier que la methode loadByUsername renvoie le bon login/password/les bonnes autoritees correspondant a l User renvoye par le mock de userService.findUser
   */
    @Test
    public void userDetails_should_correspond_to_the_user_found() {
        //given
        User user = new User();
        user.setLogin("test_user");
        user.setPassword("password");
        user.setEnabled(true);
        Role userRole = new Role();
        userRole.setRole(RolesEnum.ROLE_USER.toString());
        user.getRoles().add(userRole);
        when(userService.findUser("test_user")).thenReturn(user);
        //when
        UserDetails springSecurityUser = userDetailsService
                .loadUserByUsername("test_user");
        //then
        assertEquals(user.getLogin(), springSecurityUser.getUsername());
        assertEquals(user.getPassword(), springSecurityUser.getPassword());
        assertNotNull(user.getLastAccessDate());
        assertEquals(1, springSecurityUser.getAuthorities().size());
        assertEquals(RolesEnum.ROLE_USER.toString(),
                springSecurityUser.getAuthorities().iterator().next().getAuthority());
    }
}