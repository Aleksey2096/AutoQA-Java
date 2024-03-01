package pl.traineeship.autoqa.lesson11.parallelismus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/*
Task3: Создать отдельный пакет parallelismus. В нем класс ParallelClass1. 
В котором создать тесты parallel1, parallel2, parallel3, parallel4, parallel5. 
Создать класс ParallelClass2. В котором создать тесты parallel6, parallel7, 
parallel8, parallel9, parallel10. Тесты в классах должны быть следующего плана:
	@Test
	public void parallel1() throws InterruptedException { 
		Thread.sleep(2000);
		assertTrue(true); 
	}     
Создать отдельный xml файл testngParallelHome.xml в котором параллельно 
будут прогоняться выше созданные два класса ParallelClass1 и ParallelClass2.
 */
// Run this test class using 'testngParallelHome.xml'
public class ParallelClass1 {

	@Test
	public void parallel1() throws InterruptedException {
		Thread.sleep(2000);
		assertTrue(true);
	}

	@Test
	public void parallel2() throws InterruptedException {
		Thread.sleep(2000);
		assertTrue(true);
	}

	@Test
	public void parallel3() throws InterruptedException {
		Thread.sleep(2000);
		assertTrue(true);
	}

	@Test
	public void parallel4() throws InterruptedException {
		Thread.sleep(2000);
		assertTrue(true);
	}

	@Test
	public void parallel5() throws InterruptedException {
		Thread.sleep(2000);
		assertTrue(true);
	}
}
