package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_HomeView extends PO_View {
	static public void checkWelcome(WebDriver driver, int language) {
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("welcome.message", language), getTimeout());
	}

	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_HomeView.checkWelcome(driver, locale1);
		// Cambiamosa segundoidioma
		PO_HomeView.changeIdiom(driver, textIdiom2);
		// COmprobamos queel textodebienvenidahayacambiadoa segundoidioma
		PO_HomeView.checkWelcome(driver, locale2);
		// Volvemosa Español.
		PO_HomeView.changeIdiom(driver, textIdiom1);
		// Esperamosa quesecargueel saludodebienvenidaenEspañol
		PO_HomeView.checkWelcome(driver, locale1);
	}

	/***
	 * Seleccionael enlacedeidiomacorrespondientealtextotextLanguage * @paramdriver:
	 * apuntandoalnavegadorabiertoactualmente.* @paramtextLanguage: el
	 * textoqueapareceenel enlacedeidioma("English" o "Spanish")
	 */
	public static void changeIdiom(WebDriver driver, String textLanguage) {
		// clickamoslaopciónIdioma.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());
		elementos.get(0).click();
		// Esperamosa queaparezcael menúdeopciones.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
		// SeleniumUtils.esperarSegundos(driver, 2);//CLickamos la opción
		// IngléspartiendodelaopciónEspañol
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
		elementos.get(0).click();
	}

	/***
	 * CLicka unadelasopcionesprincipales(a href) y compruebaquesevayaa lavista
	 * conel elementodetipotype conel textoDestino* @paramdriver:
	 * apuntandoalnavegadorabiertoactualmente.* @paramtextOption:
	 * Textodelaopciónprincipal.* @paramcriterio: "id" or "class" or "text" or
	 * "@attribute" or "free". Siel valor decriterioesfree
	 * esunaexpresionxpathcompleta. * @paramtextoDestino: textocorrespondientea
	 * labúsquedadelapáginadestino.
	 */
	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		// CLickamos enlaopciónderegistroy esperamosa quesecargueel enlacedeRegistro.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());// Tienequehaberunsóloelemento.
		assertTrue(elementos.size() == 1);// Ahoraloclickamos
		elementos.get(0).click();// Esperamosa quesea visible unelementoconcreto
		elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());// Tienequehaberunsóloelemento.
		assertTrue(elementos.size() == 1);
	}
}
