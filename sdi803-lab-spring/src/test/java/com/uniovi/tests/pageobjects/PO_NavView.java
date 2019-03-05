package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.*;
import com.uniovi.tests.util.SeleniumUtils;

public class PO_NavView extends PO_View {
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
		// SeleniumUtils.esperarSegundos(driver, 2);//CLickamos la opción IngléspartiendodelaopciónEspañol
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
		elementos.get(0).click();
	}
}
