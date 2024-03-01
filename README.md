# Задание 11

**Task1:**
     Создать отдельный пакет priority. В нем класс Priority1. В этом классе создать тесты a, b, c, d, e , f, g такого плана:
     
     @Test
     public void a(){ assertTrue(true); }

Сделать так, чтобы при запуске данного класса эти тесты проходили в порядке обратном алфавитному. Придумать по крайней мере два способа, как это можно сделать.

**Task2:**
     Создать отдельный пакет grouping. В нем класс Groups. В этом классе создать тесты one, two, three, four, five, six, seven, eight такого плана:
     
     @Test
     public void one(){ assertTrue(true); }
     
Сделать так, чтобы тесты, которые называются нечетными числами принадлежали группе first, а тесты четных чисел принадлежали группе second.
Создать отдельный xml файл testngGroupingHome.xml в котором последовательно прогонялись вначале файлы группы first, а после файлы группы second. 
P.S. В каждой группе тесты должны проходить в порядке возрастания.

**Task3:**
     Создать отдельный пакет parallelismus. В нем класс ParallelClass1. В котором  создать тесты parallel1, parallel2, parallel3, parallel4, parallel5. Создать класс ParallelClass2. В котором создать тесты parallel6, parallel7, parallel8, parallel9, parallel10. Тесты в классах должны быть следующего плана:
     
     @Test
     public void parallel1() throws InterruptedException { 
     	Thread.sleep(2000);
     	assertTrue(true); 
     }     

Создать отдельный xml файл testngParallelHome.xml в котором параллельно будут прогоняться выше созданные два класса ParallelClass1 и ParallelClass2.

**Task4:**
     Заранее создать трех пользователей для нашего сайта. Написать тест используя @DataProvider который будет проверять логин этих трех пользователей.

**Task5:**
     Решить предыдущую задачу используя аннотацию @Parameters. А также создать для работы с данным тестом дополнительный xml файл testngParametersHome.xml. 
