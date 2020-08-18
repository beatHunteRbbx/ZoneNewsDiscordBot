import java.lang.reflect.Array;
import java.util.*;

public class Resources {

    private static List<String> stalkerSurnamesList = Arrays.asList(
            "Лысый", "Гуцул", "Цыган", "Академик", "Арап", "Зима", "Крученый", "Курок", "Чирий", "Трэш", "Тыр",
            "Клямпер", "Мальчик", "Кривой", "Бэн", "Силя", "Пашканчик", "Терминатор", "Батон", "Кот", "Сика",
            "Шапито", "Репа", "Мозг", "Боров", "Свин", "Свинец", "Навзик", "Чиж", "Лещ", "Чиркан", "Молодой",
            "Панк", "Пышный", "Бух", "Бокс", "Губастый", "Клюй", "Крыл", "Нос", "Фрол", "Базай",
            "Воробей", "Питон", "Люссак", "Пидаль", "Блин", "Зевс", "Кабан", "Айболит", "Нафталин", "Корвалол",
            "Нашатырь", "Ствол", "Затвор", "Слесарь", "Кевлар", "Олень", "Фазер", "Пистон", "Питерский",
            "Валет","Винт","Водила","Вор","Вандал","Варвар","Вахтёр","Ведьмак","Визитёр","Викинг","Водолаз",
            "Вождь","Вояжёр","Врач","Всадник","Важный","Везунчик","Везучий","Верный","Верзила","Вепрь","Воробей",
            "Ворон","Выхухоль","Вакуум","Валун","Вентилятор","Вертолёт","Вентиль","Ворот","Воланд","Вареник","Вист",
            "Гнилой","Горбун","Гангрена","Глюк","Градус","Гангстер","Гонец","Граф","Глыба","Геолог","Гладиатор","Гость",
            "Грузчик","Глазастый","Говорун","Гордый","Глухарь","Гоблин","Грач","Горький","Гриф","Гусь","Грозный",
            "Громила","Гвоздь","Гильза","Глобус","Граната","Гребень","Гроб","Голова","Гомер","Горыныч","Гибрид",
            "Горец","Гремлин","Доходяга","Дохляк","Дубина","Девяностик","Дуб","Дед","Дезертир","Делец","Денди",
            "Депутат","Деспот","Дикарь","Дипломат","Док","Доктор","Доцент","Душитель","Дельфин","Динозавр",
            "Дрозд","Дятел","Длинный","Добряк","Дохлый","Дачник","Движок","Динамит","Дырчик","Дырявый","Джаконда",
            "Дуремар","Дух","Дядька","Египтянин","Егерь","Ёж","Жмот","Жмур","Жук","Жадина","Жалкий","Железный",
            "Железячник","Жжёный","Живчик","Жирдяй","Жлоб","Железняк","Зверь","Змей","Звиздец","Законник","Забугровый",
            "Зелёный","Зенитчик","Знахарь","Заяц","Зубр","Замок","Закон","Затвор","Задумчивый","Зануда","Зоркий",
            "Зубастый","Зябкий","Игрок","Индеец","Ирокез","Индюк","Изотоп","Икар","Изгой","Изверг","Инвалид","Капитан",
            "Кидала","Клоун","Князь","Красный","Казак","Колдун","Кок","Команч","Король","Крёстный отец","Купец",
            "Курильщик","Курьер","Кучер","Кабан","Карась","Кенарь","Клоп","Кобра","Комар","Конь","Кот","Крокодил",
            "Кузнечик","Камень","Капкан","Кардан","Карма","Кирпич","Клей","Ключ","Кнут","Козырь","Колпак","Колун",
            "Коньяк","Корень","Коса","Космос","Кастет","Косяк","Крест","Крюк","Курок","Клык","Коготь","Кость",
            "Кулак","Казанова","Кощей","Кесарь","Колобок","Кутузов","Кагор","Капуста","Карбон","Командор","Калека",
            "Колотун","Коматозник","Косой","Косорылый","Костлявый","Кривоног","Легионер","Лорд","Легавый","Лекарь",
            "Лесник","Лось","Лапоть","Лом","Лёд","Леший","Ловкач","Лохматый","Лысый","Любимчик","Лютый","Мясо",
            "Магнат","Мажор","Маньяк","Марсианин","Масон","Механик","Могильщик","Модератор","Мясник","Молочник",
            "Мамонт","Медведь","Микроб","Москит","Машина","Муравей","Мишень","Мотор","Малыш","Мавр","Мадера","Макарон",
            "Мастер","Мутант","Мертвец","Мёртвый","Местный","Мрачный","Мутный","Мятый","Наёмник","Настройщик",
            "Начальник","Ниндзя","Нарцисс","Насос","Ништяк","Нытик","Нудный","Неудачник","Неумёха","Небрежный",
            "Нельсон","Нептун","Наполеон","Нерв"
            );

    private static List<String> stalkerNamesList = Arrays.asList(
            "Руслан","Саша","Сергей","Слава","Толик","Юра","Богдан","Вениамин","Виталий","Вова","Дима","Иван",
            "Костя","Леша","Андрей","Санек","Шурик","Вадя","Васян","Васек","Витюха","Веталь","Вован","Жорка","Гришка",
            "Гришко","Димон","Леха","Димуха","Жека","Фимка","Илюха","Костян","Митька","Михал","Миха","Михась","Колян",
            "Толян","Павлуха","Петруха","Степка","Степуха","Тимоха","Фелька","Юрась","Яшка","Андрюха","Тоха","Аркаша",
            "Тёмка","Борян","Бодя","Андрей","Антон","Олег","Роман","Александр","Сергей","Анатолий","Юрий","Богдан",
            "Вениамин","Виталий","Владимир","Дмитрий","Иван","Константин","Алексей","Саня","Санек","Артур","Яшка",
            "Ярик","Яра","Слава","Боря","Борька","Богдан","Славик","Вадим","Вадя","Вадик","Валик","Валера","Шурик",
            "Вася","Васька","Васько","Веня","Витя","Витька","Виталик","Влад","Вова","Вовка","Леня","Сева","Севка",
            "Гена","Генка","Георг","Гоша","Жора","Герман","Глеб","Гриша","Алекс","Гришка","Гришко","Даня","Данила",
            "Данько","Денис","Дима","Димка","Дмитро","Женя","Лешка","Женька","Егор","Егорка","Фима","Ваня","Ванька",
            "Игорек","Илья","Костя","Костик","Толик","Лева","Леня","Ленька","Макс","Матвей","Митя","Миша","Мишка",
            "Никита","Ник","Антон","Коля","Олег","Паша","Пашка","Петро","Петя","Петька","Рома","Ромка","Ростик",
            "Тошка","Рус","Сава","Семен","Сема","Серега","Сеня","Степан","Степа","Тима","Тимка","Артем","Федор",
            "Федя","Федька","Филя","Филька","Эдик","Юрка","Юрик","Юрко","Яша"
    );

    private static List<String> scientistNamesList = Arrays.asList(
            "Иван", "Кирилл", "Никита", "Михаил", "Егор", "Матвей", "Андрей", "Илья", "Алексей", "Роман", "Сергей",
            "Владислав", "Ярослав", "Тимофей", "Арсений", "Денис", "Владимир", "Павел", "Глеб", "Константин", "Богдан",
            "Евгений", "Николай", "Степан", "Захар"
    );

    private static List<String> militarySurnames = Arrays.asList(
            "Абдурхманов","Алтынник","Андрейчук","Андрущенко","Анисьев","Ардашев","Башаков","Биноев","Бабич","Бойко",
            "Бондарёв","Блинов","Буряк","Болдескул","Борисов","Бойченко","Башмаков","Брич","Бутурин","Бяло","Баранов",
            "Баул","Балан","Барановский","Барсак","Бешевлий","Бронштейн","Богданов","Ворохаев","Воронцов","Васильев",
            "Виноградов","Вьюшинский","Володарь","Вовненко","Воланчук","Волошин","Веселов","Грицаенко","Горюнов",
            "Гончаренко","Гарковенко","Гришачёв","Гребенюк","Губар","Гордиенко","Георгов","Ганзюк","Гунько",
            "Глебовский","Гриценков","Гнатюк","Галкин","Горлевой","Грищук","Горохин","Гацула","Гуревич","Гаркуша",
            "Грицай","Дудоров","Домбрик","Дроздуш","Дука","Дубенчук","Довгалюк","Думко","Доценко","Дыклов","Дунаев",
            "Денисенко","Ерофеев","Ефремов","Жабенко","Жмыёв","Жураковский","Звонницкий","Золотарь","Зубков",
            "Зиновьев","Зозо","Забыйягода","Зарубин","Загороднюк","Замосенчук","Захаров","Захарин","Зубарев","Иженко",
            "Иванов","Ищук","Игнатенко","Коркин","Курвиц","Кучерук","Кондратенко","Кикабидзе","Казаков","Казанцев",
            "Капука","Кузьмич","Кандыбабко","Козлов","Кунцев","Кхаладзе","Кравченко","Клименко","Колонтарь","Кушнарёв",
            "Кривохатский","Киреев","Капустянко","Кузнецов","Кульчитский","Кривда","Коев","Колонтарёв","Корнеев",
            "Кузякин","Крыга","Костылёв","Лаврик","Лымаренко","Лисичанский","Лавров","Литвин","Лившиц","Мамаев",
            "Мухамедов","Морер","Мунтян","Мостовой","Макаренко","Мальцев","Мерзляев","Мыськов","Мильченко","Миронюк",
            "Мазепа","Никаноров","Никулин","Никешин","Неприцкий","Навроцкий","Носань","Нордиев","Новиков","Орлевич",
            "Орлов","Ополев","Прасолов","Прудов","Петренко","Петкевич","Петров","Подьячев","Поплавский","Пучёк",
            "Проценко","Плохенко","Пустовар","Портных","Прокопенко","Попов","Полянин","Проскурин","Паков","Резун",
            "Русанов","Роднянский, Рублёв","Рогоза","Рабинович","Рудик","Ротань","Рычагирский, Радкевич","Синицын",
            "Сыкула","Сагитов","Стацюра","Свиридов","Смолий","Сидоров","Сорокин","Савчук","Скамейка","Слуцкий",
            "Сидоренко","Седых","Синий","Савченко","Середа","Степчин","Стычинский","Смелов","Тарнавский","Татарчук",
            "Тимчук"
            );

    private static List<String> militaryRanks = Arrays.asList(
            "Генерал","Полковник","Подполковник","Майор","Капитан","Лейтенант","Прапорщик","Сержант","Рядовой"
    );

    private static List<String> groupsList = Arrays.asList(
            "Свобода", "Монолит", "Ренегаты", "Чистое Небо", "Наёмники", "Долг", "Учёные", "Бандиты", "Военные",
            "Одиночки", "Зомбированные"
    );

    private static List<String> artefactsList = Arrays.asList(
            "Медуза", "Каменный цветок", "Ночная звезда", "Кровь камня", "Ломоть мяса", "Душа", "Выверт", "Грави",
            "Золотая рыбка", "Огненный шар", "Кристалл", "Мамины бусы", "Бенгальский огонь", "Вспышка", "Лунный свет",
            "Колобок", "Батарейка", "Пустышка", "Глаз", "Пламя", "Снежинка", "Светляк", "Пузырь", "Компас", "Пленка",
            "Капли", "Слизь", "Слизняк", "Слюда", "Колючка", "Кристальная колючка", "Морской еж", "Пружина",
            "Сердце Оазиса", "Измененный штурвал", "Измененный изолятор"
    );

    public static List<String> getScientistNamesList() { return scientistNamesList; }

    public static List<String> getStalkerSurnamesList() { return stalkerSurnamesList; }

    public static List<String> getStalkerNamesList() { return stalkerNamesList; }

    public static List<String> getGroupsList() { return groupsList; }

    public static List<String> getArtefactsList() { return artefactsList; }

    public static List<String> getMilitarySurnames() {
        return militarySurnames;
    }

    public static List<String> getMilitaryRanks() {
        return militaryRanks;
    }
}
