import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class TesteConversionCamelToArray {

	@Test
	public void comecaComNumero() {
		Converter c = new Converter();
		Boolean result = c.startWithNumber("10Primeiros");
		assertTrue(result);
	}
	
	@Test
	public void hasSpecialCharacters() {
		Converter c = new Converter();
		Boolean result = c.hasSpecialCharacters("10!Primeiros");
		assertTrue(result);
	}
	
	@Test
	public void convertSimpleWord() {
		Converter c = new Converter();
		List<String> l = c.converterCamelCase("nome");
		assertEquals(1, l.size());
		assertEquals("nome", l.get(0));
	}
	
	@Test
	public void convertSimpleWordWithOneCapitalLetter() {
		Converter c = new Converter();
		List<String> l = c.converterCamelCase("Nome");
		assertEquals(1, l.size());
		assertEquals("nome", l.get(0));
	}
	
	@Test
	public void convertDoubleWord() {
		Converter c = new Converter();
		List<String> l = c.converterCamelCase("nomeCompleto");
		assertEquals(2, l.size());
		assertEquals("nome", l.get(0));
		assertEquals("completo", l.get(1));
		
		c = new Converter();
		List<String> l2 = c.converterCamelCase("NomeCompleto");
		assertEquals(2, l.size());
		assertEquals("nome", l2.get(0));
		assertEquals("completo", l2.get(1));
	}
	
	@Test
	public void convertAcronym() {
		Converter c = new Converter();
		List<String> l = c.converterCamelCase("CPF");
		assertEquals(1, l.size());
		assertEquals("CPF", l.get(0));
	}
	
	@Test
	public void convertWordWithAcronym() {
		Converter c = new Converter();
		List<String> l = c.converterCamelCase("numeroCPF");
		assertEquals(2, l.size());
		assertEquals("numero", l.get(0));
		assertEquals("CPF", l.get(1));
	}
	

}
