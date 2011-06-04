package tudu.web.mvc;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import tudu.domain.Property;
import tudu.service.ConfigurationService;
import tudu.service.UserService;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Answers.RETURNS_SMART_NULLS;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Level2AdministrationControllerTest {

    @Mock(answer = RETURNS_SMART_NULLS)
    private ConfigurationService cfgService;
    @Mock
    private UserService userService;

    @InjectMocks
    private AdministrationController adminController = new AdministrationController();

    /*
     * - Vérifier qu'aucune interactions n'a lieu lorsque la page demandée n'est ni "configuration" ni "users"
     */
    @Test
    public void display_should_not_interact_when_page_different_than_configuration_or_users() throws Exception {
        assertThat(adminController.display("somepage")).isNotNull();

        verifyZeroInteractions(cfgService, userService);
    }

    /*
*
*  Vérifier dans un test que pour la page "configuration" il n'y a pas d'interaction avec userService.
    */
    @Test
    public void display_should_read_configService_properties_when_page_is_configuration() throws Exception {
        // given
        given(cfgService.getProperty(anyString())).willReturn(property("whatever"));

        // when
        ModelAndView mv = adminController.display("configuration");

        // then
        verifyZeroInteractions(userService);
        assertThat(mv.getModelMap().get("page")).isEqualTo("configuration");
    }

    /*
     * - Vérifier que le configService.updateEmailProperties est bien appelé en ne vérifiant que les valeurs user et password
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
    * - Vérifer que pour l'action "enableUser" le service afférent est appelé et que disableUser ne l'est pas
    */
    @Test
    public void update_enable_user_on_enableUser_action() throws Exception {
        AdministrationModel adminModel = new AdministrationModel();
        adminModel.setAction("enableUser");
        adminModel.setLogin("Paterne");

        adminController.update(adminModel);

        verify(userService).enableUser("Paterne");
        verify(userService, never()).disableUser("Paterne");
    }

    /*
    * - Vérifer que pour l'action "disableUser" le service afférent est appelé et que enableUser ne l'est pas (d'une manière différente)
    */
    @Test
    public void update_can_disable_user_on_disableUser_action() throws Exception {
        AdministrationModel adminModel = new AdministrationModel();
        adminModel.setAction("disableUser");
        adminModel.setLogin("Bob");

        adminController.update(adminModel);

        verify(userService).disableUser("Bob");
        verify(userService, times(0)).enableUser("Bob");
    }

    /*
   *
   * - Vérifier que pour l'action appelle findUsersByLogin après un enableUser ou un disableUser
    */
    @Test
    public void update_should_fetch_users_on_login_after_disabling_suer() throws Exception {
        AdministrationModel adminModel = new AdministrationModel();
        adminModel.setAction("disableUser");
        adminModel.setSearchLogin("bob*");

        adminController.update(adminModel);

        InOrder inOrder = inOrder(userService);
        inOrder.verify(userService).disableUser(anyString());
        inOrder.verify(userService).findUsersByLogin("bob*");
    }

    private Property property(String value) {
        Property property = new Property();
        property.setValue(value);
        return property;
    }
}
