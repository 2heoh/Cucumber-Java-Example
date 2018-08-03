#language: ru
Функционал: Поиск рейсов

  Структура сценария: пользователь ищет нужный ему рейс через сайт s7.ru
    Допустим пользователь открывет "http://s7.ru/"
    Когда он выбирает поиск "<из>" "<в>" через "<N>" дней на "<M>" дней
    Тогда ему отбражется <результат>

    Примеры:
      | из      | в           | N | M | результат         |
      | Moscow  | Novosibirsk | 7 | 2 | список рейсов     |
      | Minsk   | Moscow      | 7 | 2 | ничего не найдено |