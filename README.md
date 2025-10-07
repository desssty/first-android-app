# First Android App

Приложение на Android, реализующее лабораторные работы 2, 3, 4 и 5.

## Установка и запуск

1. Клонировать репозиторий:

```bash
git clone https://github.com/desssty/first-android-app.git
```

2. Открыть проект в Android Studio.
3. Подождать, пока Gradle соберет проект и скачает зависимости.
4. Запустить на эмуляторе или реальном устройстве через кнопку Run.

## Структура

```bash
app/src/main
|-- java/com/example/laba2           # исходный Java-код приложения
|  |-- model                         # модели данных
|  |  |-- Breed.java                 # класс модели породы кошки
|  |  |-- Cat.java                   # класс модели кота
|
|  |-- adapter                       # адаптеры для списков
|  |  |-- CatAdapter.java            # адаптер для списка котов (RecyclerView)
|
|  |-- ui                            # пользовательский интерфейс
|  |  |-- activity                   # экраны-приложения
|  |  |  |-- MainActivity.java       # главная активность (точка входа)
|  |  |  |-- SecondActivity.java     # вторая активность (таймер)
|  |  |  |-- CatalogActivity.java    # экран каталога котов
|  |  |  |-- CatDetailActivity.java  # экран деталей кота
|  |
|  |  |-- fragments                  # фрагменты интерфейса
|  |  |  |-- FirstFragment.java      # первый фрагмент
|  |  |  |-- SecondFragment.java     # второй фрагмент
|
|-- res                              # ресурсы приложения (UI)
|  |-- drawable                      # изображения и графика
|  |  |-- cat_image.jpg              # изображение кота
|
|  |-- layout                        # xml-разметки экранов и элементов UI
|  |  |-- activity_catalog.xml       # разметка экрана каталога
|  |  |-- activity_cat_detail.xml    # разметка экрана деталей кота
|  |  |-- activity_main.xml          # разметка главной активности
|  |  |-- activity_second.xml        # разметка второй активности
|  |  |-- fragment_first.xml         # разметка первого фрагмента
|  |  |-- fragment_second.xml        # разметка второго фрагмента
|  |  |-- recyclerview_item.xml      # разметка элемента списка (RecyclerView)
|
|  |-- values                        # общие значения (цвета, строки, темы)
|  |  |-- colors.xml                 # цвета приложения
|  |  |-- strings.xml                # строковые ресурсы (тексты)
|  |  |-- themes.xml                 # тема (светлая)
|
|  |-- values-night                  # значения для тёмной темы
|  |  |-- themes.xml                 # тема (тёмная, ночной режим)
```

Подробно о файлах в папке [docs](https://github.com/desssty/first-android-app/tree/master/docs/)
