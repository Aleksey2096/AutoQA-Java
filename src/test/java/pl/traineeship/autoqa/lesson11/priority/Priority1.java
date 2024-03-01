package pl.traineeship.autoqa.lesson11.priority;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/*
	Task1: Создать отдельный пакет priority. В нем класс Priority1. 
	В этом классе создать тесты a, b, c, d, e , f, g такого плана:
		@Test
		public void a(){ assertTrue(true); }
	Сделать так, чтобы при запуске данного класса эти тесты проходили в порядке 
	обратном алфавитному. Придумать по крайней мере два способа, как это можно сделать.
 */
public class Priority1 {

	@Test(priority = 7)
	public void a() {
		assertTrue(true);
	}

	@Test(priority = 6)
	public void b() {
		assertTrue(true);
	}

	@Test(priority = 5)
	public void c() {
		assertTrue(true);
	}

	@Test(priority = 4)
	public void d() {
		assertTrue(true);
	}

	@Test(priority = 3)
	public void e() {
		assertTrue(true);
	}

	@Test(priority = 2)
	public void f() {
		assertTrue(true);
	}

	@Test(priority = 1)
	public void g() {
		assertTrue(true);
	}
}
