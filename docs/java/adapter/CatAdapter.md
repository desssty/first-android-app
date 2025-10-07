## Что такое CatAdapter в Android и зачем он нужен

**CatAdapter** — это класс-наследник `RecyclerView.Adapter`, который связывает **список котов** (`List<Cat>`) с **элементами интерфейса** в `RecyclerView`.

Adapter отвечает за отображение каждого кота в списке, обработку кликов на элемент и передачу выбранного кота в `CatDetailActivity` через `Intent`.

---

### Основные задачи CatAdapter

1. Создаёт виджеты для элементов списка (`ViewHolder`).
2. Заполняет эти виджеты данными котов (`onBindViewHolder`).
3. Обрабатывает клики на элементы списка.
4. Позволяет RecyclerView узнавать, сколько элементов отображать (`getItemCount()`).

---

### Конструктор

```java
public CatAdapter(Context context, List<Cat> catList) {
    this.context = context;
    this.catList = catList;
}
```

- `context` нужен для запуска новых `Activity` (через `Intent`).
- `catList` — это список объектов `Cat`, которые будут отображены в RecyclerView.

### Метод onCreateViewHolder

```java
@NonNull
@Override
public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recyclerview_item, parent, false);
    return new CatViewHolder(view);
}

```

- Создаёт новый элемент списка из XML-разметки `recyclerview_item`.
- Возвращает объект `CatViewHolder`, который хранит ссылки на виджеты (`TextView` для имени и породы).

💡 RecyclerView вызывает этот метод только когда нужно создать новый виджет, который ещё не был создан.

### Метод `onBindViewHolder`

```java
@Override
public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
    Cat cat = catList.get(position);
    holder.nameTextView.setText(cat.getName());
    holder.breedTextView.setText(cat.getBreed().getBreedName());

    holder.itemView.setOnClickListener(v -> {
        Intent intent = new Intent(context, CatDetailActivity.class);
        intent.putExtra("catObject", catList.get(position));
        context.startActivity(intent);
    });
}

```

1. Получаем объект кота для текущей позиции в списке.
2. Заполняем TextView именем кота и его породой.
3. Обрабатываем клик на элемент:
   - Создаём `Intent` для перехода в `CatDetailActivity`.
   - Передаём объект кота через `putExtra("catObject", cat)`.
   - Запускаем новую активность с помощью `context.startActivity(intent)`.

### Метод `getItemCount`

```java
@Override
public int getItemCount() {
    return catList.size();
}
```

- Возвращает количество элементов в списке.
- RecyclerView использует это значение, чтобы понять, сколько элементов нужно отобразить.

### Вложенный класс `CatViewHolder`

```java
static class CatViewHolder extends RecyclerView.ViewHolder {
    TextView nameTextView;
    TextView breedTextView;

    public CatViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.productNameTextView);
        breedTextView = itemView.findViewById(R.id.productBreedTextView);
    }
}

```

- **ViewHolder** хранит ссылки на виджеты одного элемента списка.
- Это повышает производительность: RecyclerView **не ищет виджеты через findViewById** каждый раз при прокрутке, а использует уже сохранённые ссылки.

### Пример использования CatAdapter в Activity

```java
RecyclerView recyclerView = findViewById(R.id.catalogRecyclerView);
List<Cat> catList = new ArrayList<>();
catList.add(new Cat("Барсик", new Breed("Сиамская")));
catList.add(new Cat("Мурзик", new Breed("Персидская")));

CatAdapter adapter = new CatAdapter(this, catList);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(adapter);
```

- Создаём `RecyclerView`.
- Передаём список котов в `CatAdapter`.
- Связываем `Adapter` с `RecyclerView`.
- Используем `LinearLayoutManager` для вертикального списка.

Теперь каждый элемент списка отображает имя и породу кота, а при клике открывает `CatDetailActivity` с данными выбранного кота.

## Итог

- `CatAdapter` связывает данные котов с элементами интерфейса.
- **ViewHolder** хранит ссылки на виджеты, что ускоряет работу RecyclerView.
- Через `onBindViewHolder` и `Intent` можно обрабатывать клики и переходить на новый экран с деталями кота.

---
