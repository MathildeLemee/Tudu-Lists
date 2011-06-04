package tudu.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.orm.ObjectRetrievalFailureException;
import tudu.domain.User;
import tudu.service.UserAlreadyExistsException;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Level1UserServiceImplMockitoTest {


    User user = new User();
    @Mock
    EntityManager entityManager;
    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        user.setLogin("test_user");
        user.setFirstName("First name");
        user.setLastName("Last name");
    }

    /*
    test etat - utilisation when pour mocker l appel a entityManager.find et retourner le user en variable de classe
    */
    @Test
    public void find_user_should_return_the_user() {
        //given
        when(entityManager.find(User.class, "test_user")).thenReturn(user);

        //when
        User testUser = userService.findUser("test_user");

        //then
        assertEquals(testUser, user);
    }

    @Test
    /*
    test comportement - verifier que l appel a l entity manager a bien été effectué avec le user passé a update user

    */
    public void update_user_should_call_entityManager_merge() {
        //when
        userService.updateUser(user);

        //then
        verify(entityManager).merge(user);
    }

    @Test
    /*
    test etat - verifier que le login du user renvoyée par findUser est bien equivalent a celui renvoyé par le mock entityManager.find

    */
    public void user_should_be_retrieved() {
        //given
        when(entityManager.find(User.class, "toto")).thenReturn(user);

        //when
        User userLocal = userService.findUser("toto");

        //then
        assertEquals("should be equals to the user", "test_user", userLocal.getLogin());
    }


    /*
    test etat - methode findUser - controler que si l entityManager find renvoie null une exception de type
    ObjectRetrievalFailureException est bien levée
    */
    @Test(expected = ObjectRetrievalFailureException.class)
    public void error_should_be_thrown_when_a_user_is_not_found() {
        userService.findUser("toto");
        fail("should thrown a ObjectRetrievalFailureException");

    }


    @Test(expected = UserAlreadyExistsException.class)
    /*
    test etat - erreur UserAlreadyExistsException

    */
    public void exception_should_be_thrown_when_creating_an_already_existed_user() throws UserAlreadyExistsException {
        //given
        when(entityManager.find(User.class, user.getLogin())).thenReturn(user);
        //when
        userService.createUser(user);
    }

    @Test
    /*
    garantir que si l user n existe pas, la methode persist de l entity manager a bien été appelée

    */
    public void new_user_should_be_saved() throws UserAlreadyExistsException {
        //given

        //when
        userService.createUser(user);
        //then
        verify(entityManager).persist(user);
    }


}
