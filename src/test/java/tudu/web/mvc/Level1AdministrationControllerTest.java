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
public class Level1AdministrationControllerTest {

    @Mock(answer = RETURNS_SMART_NULLS)
    private ConfigurationService cfgService;
    @Mock
    private UserService userService;

    @InjectMocks
    private AdministrationController adminController = new AdministrationController();


    /*
   *  - Vérifier dans un test que pour la page "configuration" les propriétés smtp (et uniquement celles là) soit donnée au model
    **/
    @Test
    public void display_should_put_smtp_config_properties_in_admin_model_when_page_is_configuration() throws Exception {
        // given
        given(cfgService.getProperty(anyString())).willReturn(property("whatever"));

        given(cfgService.getProperty("smtp.host")).willReturn(property("the host"));
        given(cfgService.getProperty("smtp.port")).willReturn(property("the port"));
        given(cfgService.getProperty("smtp.user")).willReturn(property("the user"));
        given(cfgService.getProperty("smtp.password")).willReturn(property("the pass"));
        given(cfgService.getProperty("smtp.from")).willReturn(property("from"));


        // when
        ModelAndView mv = adminController.display("configuration");


        // then
        AdministrationModel adminModel = (AdministrationModel) mv.getModelMap().get("administrationModel");
        assertThat(adminModel.getSmtpHost()).isEqualTo("the host");
        assertThat(adminModel.getSmtpPort()).isEqualTo("the port");
        assertThat(adminModel.getSmtpUser()).isEqualTo("the user");
        assertThat(adminModel.getSmtpPassword()).isEqualTo("the pass");
        assertThat(adminModel.getSmtpFrom()).isEqualTo("from");
    }

    /*
   * Vérifier que l update ne retourne pas un modele null
   * */
    @Test
    public void update_shouldnt_return_a_null_model() throws Exception {
        AdministrationModel adminModel = new AdministrationModel();
        adminModel.setAction("whatever");
        assertThat(adminController.update(adminModel));
    }


    private Property property(String value) {
        Property property = new Property();
        property.setValue(value);
        return property;
    }
}
