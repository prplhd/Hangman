# 🎮 Виселица (Hangman Game)

Это консольная игра "Виселица" на Java, в которой игроку предстоит отгадать загаданное слово, вводя по одной букве за попытку. Спасите человечка от гибели!

---

## 📂 Содержание проекта
- `HangmanGameEngine.java` — основной игровой движок, отвечает за запуск и процесс игры.
- `SecretWordManager.java` — управление загаданным словом, проверка и обработка ввода.
- `HangmanGraphics.java` — ASCII-графика для отображения прогресса игры.
- `dictionary.txt` — словарь, из которого выбираются случайные слова для угадывания.

---

## 🎯 Правила игры
- Вводите по одной букве русского алфавита за попытку.
- Если угадали — в замаскированном слове откроется угаданная буква.
- Если ошиблись — человечек окажется на один шаг ближе к виселице. У Вас есть 8 попыток!
