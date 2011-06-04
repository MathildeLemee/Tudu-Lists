package tudu.web.mvc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import tudu.service.ConfigurationService;
import tudu.service.UserService;

import static org.mockito.Answers.RETURNS_SMART_NULLS;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Level3AdministrationControllerTest {

    @Mock(answer = RETURNS_SMART_NULLS)
    private ConfigurationService cfgService;
    @Mock
    private UserService userService;

    @InjectMocks
    private AdministrationController adminController = new AdministrationController();

    /*
    *  Vérifier La réponse par défaut du mock configService et faire un test avec la page configuration
    *  Méthode :  display
    *  Aide : configuaration du mock a l'aide de RETURNS_SMART_NULLS
    */
    @Test(expected = SmartNullPointerException.class)
    public void display_should_throw_mockito_ex_when_configService_is_accessed_properties_when_page_is_configuration() throws Exception {
        // given config service not stubbed

        // when
        adminController.display("configuration");
    }

    /*
     * Vérifier que le configService.updateEmailProperties est bien appelé en ne vérifiant que les valeurs user et password .
     * Aide : Spy
    * Méthode :  update
     */
    @Test
    public void update_should_update_smtp_config_and_nothing_else() throws Exception {
        // given
        AdministrationController spiedAdmnController = spy(adminController);
        willReturn(new ModelAndView()).given(spiedAdmnController).display(anyString());

        AdministrationModel adminModel = new AdministrationModel();
        adminModel.setAction("configuration");
        adminModel.setSmtpUser("the user");
        adminModel.setSmtpPassword("password");

        // when
        spiedAdmnController.update(adminModel);

        // then
        verify(cfgService).updateEmailProperties(
                anyString(),
                anyString(),
                eq("the user"),
                eq("password"),
                anyString()
        );

        verifyZeroInteractions(userService);
    }
/*
* Reprendre sur quelques tests ayant des assertEquals, assertNull, assertNotNull avec le framefork fest assert
*/


/*
* Reprendre sur quelques tests ayant des when, verify, doThrow en utilisant la syntaxe bdd mockito
*/

}
