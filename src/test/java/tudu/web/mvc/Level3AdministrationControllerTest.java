package tudu.web.mvc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.runners.MockitoJUnitRunner;
import tudu.service.ConfigurationService;
import tudu.service.UserService;

import static org.mockito.Answers.RETURNS_SMART_NULLS;

@RunWith(MockitoJUnitRunner.class)
public class Level3AdministrationControllerTest {

    @Mock(answer = RETURNS_SMART_NULLS)
    private ConfigurationService cfgService;
    @Mock
    private UserService userService;

    @InjectMocks
    private AdministrationController adminController = new AdministrationController();

    /*
    *  - La réponse par défaut du mock configService et faire un test avec la page configuration  - configuaration du mock a l'aide de RETURNS_SMART_NULLS
    */
    @Test(expected = SmartNullPointerException.class)
    public void display_should_throw_mockito_ex_when_configService_is_accessed_properties_when_page_is_configuration() throws Exception {
        // given config service not stubbed

        // when
        adminController.display("configuration");
    }

/*
* Reprendre sur quelques tests ayant des assertEquals, assertNull, assertNotNull avec le framefork fest assert
*/


/*
* Reprendre sur quelques tests ayant des when, verify, doThrow en utilisant la syntaxe bdd mockito
*/

}
