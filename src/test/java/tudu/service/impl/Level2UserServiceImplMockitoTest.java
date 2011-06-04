package tudu.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tudu.domain.User;
import tudu.service.UserAlreadyExistsException;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;

public class Level2UserServiceImplMockitoTest {


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
verifier que l on recupere bien RuntimeException en sortie de updateUser si la methode Merge de l entityManager leve une RuntimeException
 */
    @Test(expected = RuntimeException.class)
    public void when_an_exception_is_thrown_by_entityManager_then_rethrow() {
        //given
        doThrow(new RuntimeException()).when(entityManager).merge(Matchers.<Object>anyObject());
        //when
        userService.updateUser(new User());
    }

    /*
    LES METHODES SUIVANTES SONT UNIQUEMENT LA POUR APPRENDRE LA SYNTAXE. CES TESTS SONT EXTREMEMENTS FRAGILES, A MANIPULER AVEC PRECAUTIONS !
     */
    @Test
    /*garantir que si l user n existe pas, la methode persist de l entity manager a bien ete appele 4 fois
     Ce genre de methode de test est a manipuler avec precaution ... Eviter de reproduire ca dans votre code !
    */
    public void when_creation_of_a_new_user_then_4_calls_to_entityManager_persist() throws UserAlreadyExistsException {
        //given

        //when
        userService.createUser(user);

        //then
        verify(entityManager, times(4)).persist(Matchers.<Object>anyObject());
    }

    @Test
    /*garantir que si l user n existe pas, la methode persist de l entity manager a bien ete appele au moins 2 fois et au plus 5 fois
     Ce genre de methode de test est a manipuler avec precaution ... Eviter de reproduire ca dans votre code !
*/
    public void when_creation_of_a_new_user_then_between_2_and_5_calls_to_entityManager_persist() throws UserAlreadyExistsException {
        //given

        //when
        userService.createUser(user);

        //then
        verify(entityManager, atLeast(2)).persist(Matchers.<Object>anyObject());
        verify(entityManager, atMost(5)).persist(Matchers.<Object>anyObject());

    }

    @Test
    /*garantir que si l user existe, la methode persist de l entity manager n a jamais ete appelle
    2 moyens d y arriver
     Ce genre de methode de test est a manipuler avec precaution ... Eviter de reproduire ca dans votre code !
    */
    public void when_the_login_already_exist_then_persist_never_called_1() throws UserAlreadyExistsException {
        //given
        when(entityManager.find(User.class, user.getLogin())).thenReturn(user);

        //when
        try {
            userService.createUser(user);
        } catch (UserAlreadyExistsException e) {
        }

        //then
        verify(entityManager, never()).persist(Matchers.<Object>anyObject());

    }

    /*
    utilisation de verifyNoMoreInteractions - a utiliser avec precaution
    */
    @Test
    public void when_the_login_already_exist_then_persist_never_called_2() throws UserAlreadyExistsException {
        //given
        when(entityManager.find(User.class, user.getLogin())).thenReturn(user);

        //when
        try {
            userService.createUser(user);
        } catch (UserAlreadyExistsException e) {
        }

        //then
        verify(entityManager).find(User.class, user.getLogin());
        verifyNoMoreInteractions(entityManager);
    }

}