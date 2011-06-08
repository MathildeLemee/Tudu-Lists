package tudu.security;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import tudu.domain.Role;
import tudu.domain.RolesEnum;
import tudu.domain.User;
import tudu.service.impl.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

//Niveau2
//Enlever le @Ignore et trouver pourquoi la deuxieme methode ne fonctionne pas comme prevue comparee a la premiere
@Ignore
public class Level2AttentionMockitoTest {
	@Mock
	UserServiceImpl userService;
	@InjectMocks
	UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

	@Before
	public void before() {
		MockitoAnnotations.initMocks( this );

	}
	//  testLoadUserByUsername_danger renomme en  testLoadUserByUsername_erreur
	@Test
	public void testLoadUserByUsername_erreur() {
		User user = new User();
		user.setLogin( "test_user" );
		user.setPassword( "password" );
		user.setEnabled( true );
		Role userRole = new Role();
		userRole.setRole( RolesEnum.ROLE_USER.toString() );
		user.getRoles().add( userRole );
		//Ici ete mocke un appel a findUserJoke alors que la nmethode loadByUsername appelait findUser
		when( userService.findUser( "test_user" ) ).thenReturn( user );

		UserDetails springSecurityUser = userDetailsService.loadUserByUsername( "test_user" );

		assertEquals( user.getLogin(), springSecurityUser.getUsername() );
		assertEquals( user.getPassword(), springSecurityUser.getPassword() );
		assertNotNull( user.getLastAccessDate() );
		assertEquals( 1, springSecurityUser.getAuthorities().size() );
		assertEquals( RolesEnum.ROLE_USER.toString(), springSecurityUser.getAuthorities().iterator().next().getAuthority() );

	}

	@Test
	public void testLoadUserByUsername_danger() {
		User user = new User();
		user.setLogin( "test_user" );
		user.setPassword( "password" );
		user.setEnabled( true );
		Role userRole = new Role();
		userRole.setRole( RolesEnum.ROLE_USER.toString() );
		user.getRoles().add( userRole );
		//ICI c est le bon mock qui est cree mais la methode mockee est finale : mockito ne sait pas mocker une
		// methode finale, on peut par exemple enlever le final si inutile, passer par une interface ou par powermock
		// pour
		// passer
		// outre

	/*	when( userService.findUserJoke( "test_user" ) ).thenReturn( user );

		UserDetails springSecurityUser = userDetailsService.loadUserByUsernameJoke( "test_user" );

		assertEquals( user.getLogin(), springSecurityUser.getUsername() );
		assertEquals( user.getPassword(), springSecurityUser.getPassword() );
		assertNotNull( user.getLastAccessDate() );
		assertEquals( 1, springSecurityUser.getAuthorities().size() );
		assertEquals( RolesEnum.ROLE_USER.toString(), springSecurityUser.getAuthorities().iterator().next().getAuthority() );
*/
	}


}
