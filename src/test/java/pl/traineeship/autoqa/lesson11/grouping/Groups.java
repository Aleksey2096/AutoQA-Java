package pl.traineeship.autoqa.lesson11.grouping;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/*
	Task2: Создать отдельный пакет grouping. В нем класс Groups. 
	В этом классе создать тесты one, two, three, four, five, six, seven, eight такого плана:
		@Test
		public void one(){ assertTrue(true); }
	Сделать так, чтобы тесты, которые называются нечетными числами принадлежали группе first, 
	а тесты четных чисел принадлежали группе second. Создать отдельный xml файл testngGroupingHome.xml 
	в котором последовательно прогонялись вначале файлы группы first, а после файлы группы second. P.S. 
	В каждой группе тесты должны проходить в порядке возрастания.
 */
// Run this test class using 'testngGroupingHome.xml'
public class Groups {

	@Test(groups = { "first" }, priority = 1)
	public void one() {
		assertTrue(true);
	}

	@Test(groups = { "second" }, priority = 1)
	public void two() {
		assertTrue(true);
	}

	@Test(groups = { "first" }, priority = 2)
	public void three() {
		assertTrue(true);
	}

	@Test(groups = { "second" }, priority = 2)
	public void four() {
		assertTrue(true);
	}

	@Test(groups = { "first" }, priority = 3)
	public void five() {
		assertTrue(true);
	}

	@Test(groups = { "second" }, priority = 3)
	public void six() {
		assertTrue(true);
	}

	@Test(groups = { "first" }, priority = 4)
	public void seven() {
		assertTrue(true);
	}

	@Test(groups = { "second" }, priority = 4)
	public void eight() {
		assertTrue(true);
	}
}
