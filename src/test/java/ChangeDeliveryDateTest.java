import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import utils.Person;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;




public class ChangeDeliveryDateTest {

    public String generateDate(int addDays) {
        LocalDate tomorrow = LocalDate.now().plusDays(addDays);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tomorrow.format(formatter);
    }

    private void bookDeliveryDate(String city, String name, String date, String phone){

        $("[placeholder='Город']").doubleClick();
        $("[placeholder='Город']").sendKeys(Keys.DELETE);
        $("[placeholder='Город']").setValue(city);

//        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(date);

        $("[name='name']").doubleClick();
        $("[name='name']").sendKeys(Keys.DELETE);
        $("[name='name']").setValue(name);

        $("[name='phone']").doubleClick();
        $("[name='phone']").sendKeys(Keys.DELETE);
        $("[name='phone']").setValue(phone);

        $("[data-test-id='agreement']").click();
        $$(".button__text").findBy(text("Запланировать")).click();
    }

    private void checkDeliveryDateReservation(String date){
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(20))
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date));
    }

    @Test
    public void shouldChangeDeliveryDate() {
        Person person = Person.generateFakePerson();
        String deliveryDate = generateDate(5);
        String newDeliveryDate = generateDate(6);

        open("http://localhost:9999");

        $("[placeholder='Город']").setValue(person.getCity());

        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(deliveryDate);

        $("[name='name']").setValue(person.getFullName());

        $("[name='phone']").setValue(person.getPhoneNumber());

        $("[data-test-id='agreement']").click();
        $$(".button__text").findBy(text("Запланировать")).click();

        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").setValue(newDeliveryDate);

        $$(".button__text").findBy(text("Запланировать")).click();
        $$(".button__text").findBy(text("Перепланировать")).click();

        checkDeliveryDateReservation(newDeliveryDate);
    }



}
