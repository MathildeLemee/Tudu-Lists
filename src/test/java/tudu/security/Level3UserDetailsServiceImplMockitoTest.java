package tudu.security;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.Role;
import tudu.domain.RolesEnum;
import tudu.domain.User;
import tudu.service.UserService;
import tudu.service.impl.UserServiceImpl;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//Niveau2
public class Level3UserDetailsServiceImplMockitoTest {

    /* Le test suivant a été ecris avec EasyMock. L'écrire à nouveau en utilisant la syntaxe BDD Mockito et en utilisant les assertions de fest assert a la place des assertEquals
   * assertNotNull*/
    @Test
    public void testLoadUserByUsername() {
     }
    // Pour aller plus loin : Should you only mock types that you own : http://stackoverflow.com/questions/1906344/should-you-only-mock-types-you-own
}
