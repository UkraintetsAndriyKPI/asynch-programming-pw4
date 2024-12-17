## Пояснення коду: Асинхронний калькулятор з використанням `CompletableFuture`

Код демонструє, як можна використовувати асинхронні операції в Java для паралельного виконання задач. Тут створюється асинхронний обчислювальний процес, що виконує кілька послідовних операцій, включаючи генерацію чисел, обчислення результату, обробку результату та виведення.

---

### Основні етапи роботи коду
1. **Запуск асинхронного процесу**  
   Код використовує `CompletableFuture`, щоб виконати асинхронні задачі без блокування головного потоку програми.

2. **Генерація чисел**  
   Генеруються випадкові числа, які будуть використані для обчислень.

3. **Обчислення суми**  
   Виконується обчислення, що складається з множення пар послідовних чисел у масиві та обчислення їхньої суми.

4. **Обробка та виведення результату**  
   Після обчислення результату асинхронно обробляється та виводиться на екран.

5. **Вимкнення `ExecutorService`**  
   Після завершення всіх операцій асинхронний пул потоків (`executor`) вимикається.

---

### Ключові концепції
- **`CompletableFuture`**  
  Допомагає працювати з асинхронністю, дозволяючи виконувати задачі послідовно або паралельно.

- **ExecutorService**  
  Керує пулом потоків, що використовуються для асинхронних обчислень.

- **Асиметричне виконання**  
  Завдання виконуються асинхронно, що дозволяє виконувати інші операції, не блокуючи головний потік програми.

---

### Переваги підходу
1. **Краща продуктивність**  
   Використання декількох потоків дозволяє виконувати обчислення паралельно.

2. **Нешкідливість головного потоку**  
   Всі довгі обчислювальні операції не блокують головний потік.

3. **Гнучкість**  
   Простий механізм для ланцюжка асинхронних обчислень.