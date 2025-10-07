## Что такое RecyclerView.Adapter в Android и зачем он нужен

**RecyclerView.Adapter** — это абстрактный класс, который связывает **данные** с **элементами интерфейса** в RecyclerView.  
Он отвечает за создание и заполнение виджетов для каждого элемента списка.

### Зачем нужен Adapter

- Для отображения коллекций данных (списков, массивов, ArrayList) в RecyclerView.
- Позволяет **разделять логику данных и логику отображения**.
- Повышает производительность: RecyclerView переиспользует уже созданные View через механизм ViewHolder.

### Основные методы Adapter

1. **`onCreateViewHolder(ViewGroup parent, int viewType)`**

   - Создаёт новый объект ViewHolder и соответствующую разметку для элемента списка.
   - Вызывается, когда RecyclerView нужен новый элемент, которого ещё нет на экране.

2. **`onBindViewHolder(VH holder, int position)`**

   - Заполняет данные для элемента списка (например, имя кота и его породу).
   - Вызывается каждый раз, когда элемент виден пользователю.

3. **`getItemCount()`**
   - Возвращает количество элементов в списке.
   - RecyclerView использует это значение для построения списка.

### Важный класс — ViewHolder

**ViewHolder** — это вспомогательный объект, который хранит ссылки на виджеты разметки элемента списка.  
Он повышает производительность, чтобы RecyclerView не вызывал `findViewById()` каждый раз при прокрутке.

### Пример абстрактного Adapter

```java
// Adapter для RecyclerView на абстрактном примере
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    // Список данных любого типа (например, строки)
    private List<String> dataList;

    // Конструктор принимает данные
    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    // Создаём ViewHolder для нового элемента списка
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // "Раздуваем" XML-разметку элемента списка
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    // Заполняем ViewHolder данными для текущей позиции
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String data = dataList.get(position); // получаем данные для элемента
        holder.textView.setText(data);        // связываем данные с виджетом

        // Пример обработки клика по элементу
        holder.itemView.setOnClickListener(v -> {
            // Здесь можно запускать Activity, показывать Toast и т.д.
            Toast.makeText(v.getContext(), "Clicked: " + data, Toast.LENGTH_SHORT).show();
        });
    }

    // Количество элементов в списке
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // ViewHolder хранит ссылки на виджеты разметки элемента списка
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            // Находим виджеты один раз, чтобы не делать findViewById при прокрутке
            textView = itemView.findViewById(R.id.textView);
        }
    }
}

// Использование Adapter в Activity
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        // Абстрактные данные для примера
        data = new ArrayList<>();
        data.add("Элемент 1");
        data.add("Элемент 2");
        data.add("Элемент 3");

        // RecyclerView использует LayoutManager для отображения элементов
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Создаём Adapter и связываем его с RecyclerView
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}


```

### Пояснение

- Adapter получает список данных (`dataList`) и связывает его с элементами RecyclerView.

- ViewHolder хранит ссылки на виджеты для каждого элемента, чтобы не создавать их заново при прокрутке.

- При клике на элемент Adapter может запускать новую Activity, передавая данные через Intent (например, объекты `Parcelable`).

---
