package utils;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Locale;
import java.util.Random;

@Data
@AllArgsConstructor
public class Person {
    private String fullName;
    private String phoneNumber;
    private String city;

    private static String getRandomCity(){
        String[] cityList = {
                "Москва", "Санкт-Петербург", "Севастополь", // города федерального значения
                "Абакан", "Анадырь", "Архангельск", "Астрахань", "Барнаул", "Белгород",
                "Биробиджан", "Благовещенск", "Брянск", "Великий Новгород", "Владивосток",
                "Владикавказ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Горно-Алтайск",
                "Грозный", "Екатеринбург", "Иваново", "Ижевск", "Йошкар-Ола", "Иркутск",
                "Казань", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома",
                "Красногорск", "Краснодар", "Красноярск", "Курган", "Курск", "Кызыл",
                "Липецк", "Магадан", "Магас", "Майкоп", "Махачкала", "Нальчик",
                "Нарьян-Мар", "Нижний Новгород", "Новосибирск", "Омск", "Оренбург",
                "Орёл", "Пенза", "Пермь", "Петрозаводск", "Петропавловск-Камчатский",
                "Псков", "Ростов-на-Дону", "Рязань", "Салехард", "Самара", "Саранск",
                "Саратов", "Смоленск", "Ставрополь", "Сыктывкар", "Тамбов", "Тверь",
                "Томск", "Тула", "Тюмень", "Улан-Удэ", "Ульяновск", "Уфа", "Хабаровск",
                "Ханты-Мансийск", "Чебоксары", "Челябинск", "Черкесск", "Чита", "Элиста",
                "Южно-Сахалинск", "Якутск", "Ярославль"
        };
        return cityList[new Random().nextInt(cityList.length)];
    }
    public static Person generateFakePerson() {
        Faker faker = new Faker(new Locale("ru"));
        return new Person(
                faker.name().firstName() + " " + faker.name().lastName(),
                faker.phoneNumber().phoneNumber().replaceAll("[()\\-]", ""),
                getRandomCity()
        );
    }

}
