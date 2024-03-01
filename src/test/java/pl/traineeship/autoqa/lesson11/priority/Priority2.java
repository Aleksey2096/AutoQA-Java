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
public class Priority2 {

	@Test(dependsOnMethods = { "b" }, alwaysRun = true)
	public void a() {
		assertTrue(true);
	}

	@Test(dependsOnMethods = { "c" }, alwaysRun = true)
	public void b() {
		assertTrue(true);
	}

	@Test(dependsOnMethods = { "d" }, alwaysRun = true)
	public void c() {
		assertTrue(true);
	}

	@Test(dependsOnMethods = { "e" }, alwaysRun = true)
	public void d() {
		assertTrue(true);
	}

	@Test(dependsOnMethods = { "f" }, alwaysRun = true)
	public void e() {
		assertTrue(true);
	}

	@Test(dependsOnMethods = { "g" }, alwaysRun = true)
	public void f() {
		assertTrue(true);
	}

	@Test
	public void g() {
		assertTrue(true);
	}
}