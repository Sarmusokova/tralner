package ru.suman.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenQuestionCardTest {

    private OpenQuestionCard card;

    @BeforeEach
    void setUp() {
        card = new OpenQuestionCard(1L,"Сколько планет в Солнечной системе?", "Восемь");
    }

    @Test
    @DisplayName("Проверка метода getQuestion")
    void testGetQuestion() {
        assertEquals("Сколько планет в Солнечной системе?", card.getQuestion(), "Вопрос должен совпадать с заданным");
    }


    @Test
    @DisplayName("Проверка ответа с ведущими и конечными символами")
    void testCheckAnswerWithLeadingAndTrailingCharacters() {
        assertFalse(card.checkAnswer("!Восемь?"), "Лишние знаки должны делать ответ неправильным");
    }

    @Test
    @DisplayName("Проверка с пустым ответом пользователя")
    void testCheckEmptyUserAnswer() {
        assertFalse(card.checkAnswer(""), "Пустая строка не может быть правильным ответом");
    }

    @Test
    @DisplayName("Проверка с null-ответом пользователя")
    void testCheckNullUserAnswer() {
        assertThrows(NullPointerException.class, () -> card.checkAnswer(null), "Передача null-значения должна вызывать исключение");
    }

    @Test
    @DisplayName("Проверка создания объекта с максимально длинным вопросом")
    void testCreateCardWithLongQuestion() {
        String longQuestion = "а".repeat(1000);
        String longAnswer = "б".repeat(1000);
        OpenQuestionCard longCard = new OpenQuestionCard(card.getId(), longQuestion, longAnswer);
        assertEquals(longQuestion, longCard.getQuestion(), "Должен поддерживаться длинный текст вопроса");
    }

    @Test
    @DisplayName("Проверка чувствительности к регистру при создании объекта")
    void testCaseSensitivityInAnswer() {
        OpenQuestionCard mixedCaseCard = new OpenQuestionCard(1L,"Как зовут кота?", "Барсик");
        assertTrue(mixedCaseCard.checkAnswer("барсик"), "Ответ должен проверяться без учета регистра");
    }
}
