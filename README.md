# Задание по мобильной автоматизации:

Все тесты нужно писать к приложению ApiDemos. Которое мы рассматривали на занятие.

**1)** Открыть приложение → Views →

Написать тест, который будет проверять количество элементов на данной (и меются в виду кнопки при тапе на которые можно будет перейти на новую страницу).
( Их тут всего 42).

![1]

**2)** Открыть приложение → Views → Data Widgets → 1. Dialog →

Здесь нужно задать дату и время использую данные кнопки:

![2]

Нужно задать завтрашнее число и время 11:11 PM:

![3]

![4]

**3)** Открыть приложение → Views → TextSwitcher →

Проверить работоспособность кнопки Next. Суть проверки заключается в том, что после определенного количества нажатия на кнопку Next это количество нажатий должно отобразиться в поле по центру экрана:

![5]

**Desired Capabilities in Appium Inspector**

{

  "platformName": "Android",
  
  "appium:deviceName": "emulator-5554",
  
  "appium:appPackage": "io.appium.android.apis",
  
  "appium:appActivity": "io.appium.android.apis.ApiDemos",
  
  "appium:automationName": "UiAutomator2",
  
  "appium:adbExecTimeout": "100000"
  
}

<!-- MARKDOWN LINKS & IMAGES -->

[1]:project-info/1.png

[2]:project-info/2.png

[3]:project-info/3.png

[4]:project-info/4.png

[5]:project-info/5.png
