## Что такое Parcel в Java (Android) и зачем он нужен

**Parcel** — это специальный контейнер в Android, который используется для упаковки и распаковки данных
для их передачи между компонентами приложения (Activity, Service, Fragment и т.д.).

Parcel — это более быстрый и лёгкий аналог сериализации (`Serializable`),
оптимизированный именно под Android. Он работает напрямую с памятью, поэтому экономичнее,
но требует ручного описания того, как объект будет записан и восстановлен.

### Зачем нужен Parcel

- Для передачи сложных объектов через **Intent** (например, при запуске другой Activity).
- Для обмена данными между **процессами** (через Binder).
- Для более быстрой работы по сравнению с `Serializable`.

### Особенности

- Не является универсальным форматом (нельзя сохранять в файл или отправлять по сети).
- Используется только внутри Android.
- Чтобы объект можно было "упаковать" в Parcel, класс должен реализовать интерфейс **Parcelable**.
- Если бы мы использовали **Serializable$$**, всё работало бы, но медленнее, потому что сериализация не оптимизирована под Android.

---

### Методы интерфейса Parcelable

Чтобы класс можно было передавать через `Parcel`, он должен реализовать интерфейс `Parcelable`.  
Этот интерфейс определяет всего два метода:

1. **`int describeContents()`**

   - Обычно возвращает `0`.
   - Используется для описания содержимого (например, если объект содержит специальные объекты типа `FileDescriptor`).
   - В большинстве случаев не требуется менять поведение.

2. **`void writeToParcel(Parcel dest, int flags)`**
   - Упаковывает объект в `Parcel`.
   - Здесь нужно вручную записать все поля объекта (строки, числа, вложенные объекты и т.д.) с помощью методов `Parcel`.
   - Параметр `flags` редко используется (например, для передачи вложенных объектов).

Кроме методов интерфейса, **обязательно** нужно добавить статический объект:

3. **`Parcelable.Creator<T> CREATOR`**
   - Отвечает за распаковку объекта из `Parcel`.
   - Должен реализовать два метода:
     - `T createFromParcel(Parcel in)` — создаёт объект, читая данные из `Parcel`.
     - `T[] newArray(int size)` — создаёт массив объектов.

#### Итог

Таким образом, любой `Parcelable`-класс в Android содержит:

- Конструктор, принимающий `Parcel` (для чтения данных).
- `writeToParcel()` — для записи.
- `describeContents()` — почти всегда возвращает `0`.
- `CREATOR` — для восстановления объектов из `Parcel`.

---

## Пример использования Parcelable

Представим, что у нас есть простой класс `Person`, который хранит имя и возраст.  
Мы хотим передавать объект `Person` между `Activity` через `Intent`.

### Класс Person

```java
import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String name;
    private int age;

    // Конструктор
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Конструктор для чтения из Parcel
    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in); // вызывает конструктор, который считывает поля из Parcel
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size]; // создаёт массив объектов для Parcel
        }
    };

    // Упаковка в Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    // Обычно возвращает 0
    @Override
    public int describeContents() {
        return 0;
    }

    // CREATOR для распаковки объекта
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
```

### Передача объекта

В одной Activity можно передать объект так:

```java
Intent intent = new Intent(this, SecondActivity.class);
Person person = new Person("Иван", 25);
intent.putExtra("person_key", person);
startActivity(intent);
```

### Получение объекта

В другой Activity мы его получаем:

```java
Person person = getIntent().getParcelableExtra("person_key");
```

---
