package tudu.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import tudu.domain.TodoList;
import tudu.domain.User;
import tudu.service.UserAlreadyExistsException;

import java.util.Set;
import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.verify;

public class Level3UserServiceImplMockitoTest {


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
	  //MODIFICATION DU SUJET / il est maintenant impossible de faire un simple user.getTodoLists

    /*
    Vérifier que la liste todo a bien pour name Welcome!
    Méthode :  createNewTodoList
    */
    @Test
    public void new_todolist_should_be_named_welcome_1() throws UserAlreadyExistsException {
        //given
        ArgumentCaptor<TodoList> userCaptured = ArgumentCaptor.forClass(TodoList.class);

        //when
        userService.createNewTodoList(user);
		Set<TodoList> todos = user.getTodoLists();
        //then
        verify(entityManager).persist(userCaptured.capture());
        TodoList todo = userCaptured.getValue();
        assertEquals("todo list should be named welcome", "Welcome!", todo.getName());
    }

    /*
    Vérifier que la liste todo a bien pour name Welcome! - autre méthode
    Méthode :  createNewTodoList
    */
    class IsTodoListEquals extends ArgumentMatcher<TodoList> {
        TodoList todo;

        IsTodoListEquals(TodoList todo) {
            this.todo = todo;
        }

        @Override
        public boolean matches(Object o) {
            if (this == o) return true;
            TodoList todoList = (TodoList) o;
            if (!todoList.getName().equals(todo.getName())) return false;
            //ici mettre tous les controles pour verifier que les 2 todolists sont identiques
            return true;
        }
    }

    @Test
    public void new_todolist_should_be_named_welcome_2() throws UserAlreadyExistsException {
        //given
        TodoList todo = new TodoList();
        todo.setName("Welcome!");

        //when
        userService.createNewTodoList(user);

        //then
        verify(entityManager).persist(argThat(new IsTodoListEquals(todo)));
    }


}
