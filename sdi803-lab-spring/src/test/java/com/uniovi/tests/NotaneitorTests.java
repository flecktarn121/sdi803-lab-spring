package com.uniovi.tests;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorTests {
	// En Windows (Debeserlaversión65.0.1y desactivar las actualizacioens
	// automáticas)):
//	static String PathFirefox64 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
//	static String Geckdriver022 = "C:\\Users\\UO258654\\Downloads\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
//	En GNU/Linux
	private static String PathFirefox65 = "/usr/bin/firefox";
	private static String Geckdriver024 = "/usr/bin/geckodriver";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		driver.navigate().to(URL);
	}

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

	// PR01. Accedera lapáginaprincipal /
	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());// SeleniumUtils.esperarSegundos(driver, 2);}
	}

	@Test
	public void PR05() {// Vamosalformularioderegistro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	@Test
	public void PR06() {
		// Vamosalformularioderegistro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		// COmprobamos el error deDNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777");
		// COmprobamos el error deNombrecorto.
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
		// Rellenamosel formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Per", "77777", "77777");
		// Comprobamos el apellido corto
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
		// Comprobamos la contraseña corta
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Perez", "7777", "7777");
	}

	// PRN. Loguearseconexitodesdeel ROl deUsuario, 99999990D, 123456
	@Test
	public void PR07() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");// COmprobamos queentramosenlapaginaprivadadeAlumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	@Test
	public void PR08() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		// Comprobamos que está visible la administración de
		// notas
		PO_View.checkElement(driver, "text", " Gestión de notas ");
	}

	@Test
	public void PR09() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999988F", "123456");
		// Comprobamos que está visible la administración de
		// usuarios
		PO_View.checkElement(driver, "text", "Gestión de Usuarios");
	}

	@Test
	public void PR10() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999990A", "123457");
		// Comprobamos que está visible la administración de
		// usuarios
		try {
			PO_View.checkElement(driver, "text", "Notas del usuario");
			fail();
		} catch (TimeoutException e) {

		}
	}

	@Test
	public void PR11() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");// COmprobamos queentramosenlapaginaprivadadeAlumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkKey(driver, "login.message", PO_Properties.getSPANISH());
	}

	@Test
	public void PR12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		PO_View.checkElement(driver, "text", "Nota A1");
		PO_View.checkElement(driver, "text", "Nota A2");
		PO_View.checkElement(driver, "text", "Nota A3");
		PO_View.checkElement(driver, "text", "Nota A4");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR13. Loguearsecomoestudiantey verlosdetallesdelanotaconDescripcion=
	// NotaA2.//P13. VerlalistadeNotas.
	@Test
	public void PR13() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// COmprobamos queentramosenlapaginaprivadadeAlumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
		SeleniumUtils.esperarSegundos(driver, 1);
		// Contamoslasnotas
		By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");
		driver.findElement(enlace).click();
		SeleniumUtils.esperarSegundos(driver, 1);
		// Esperamosporlaventanadedetalle
		PO_View.checkElement(driver, "text", "Detalles de la nota");
		SeleniumUtils.esperarSegundos(driver, 1);// Ahoranosdesconectamos
		PO_HomeView.clickOption(driver, "logout", "text", "Identifícate");
	}

	// P14. Loguearsecomoprofesory AgregarNotaA2.//P14.
	// Estapruebapodríaencapsularsemejor...
	@Test
	public void PR14() {
		// Vamosalformulariodelogueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamosel formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		// COmprobamos queentramosenlapaginaprivadadelProfesor
		PO_View.checkElement(driver, "text", "99999993D");
		// Pinchamosenlaopcióndemenu deNotas: //li[contains(@id, 'marks-­‐menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
		elementos.get(0).click();
		// Esperamosa aparezcalaopcióndeañadirnota: //a[contains(@href, 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");
		// PinchamosenagregarNota.
		elementos.get(0).click();
		// Ahoravamosa rellenarlanota. //option[contains(@value, '4')]
		PO_PrivateView.fillFormAddMark(driver, 3, "Nota  Nueva  1", "8");
		// Esperamosa quesemuestrenlosenlacesdepaginaciónlalistadenotas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nosvamosa laúltimapágina
		elementos.get(3).click();
		// Comprobamosqueaparecelanotaenlapagina
		elementos = PO_View.checkElement(driver, "text", "Nota  Nueva  1");
		// Ahoranosdesconectamos
		PO_HomeView.clickOption(driver, "logout", "text", "Identifícate");
	}

}
