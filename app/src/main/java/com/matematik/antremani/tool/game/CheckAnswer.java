package com.matematik.antremani.tool.game;

import androidx.annotation.NonNull;
import com.matematik.antremani.Constant;

import javax.inject.Inject;

public class CheckAnswer {

    @Inject
    public CheckAnswer() {}

    private Boolean checkResult(Integer firstNumber, @NonNull String operator, Integer secondNumber, Integer result) {

        return switch (operator) {

            case Constant.OPERATOR_ADDITION -> (firstNumber + secondNumber == result);

            case Constant.OPERATOR_SUBTRACTION -> (firstNumber - secondNumber == result);

            case Constant.OPERATOR_MULTIPLY -> (firstNumber * secondNumber == result);

            default -> (firstNumber / secondNumber == result);

        };

    }

    public Boolean checkBasicQuestion(@NonNull CreateQuestion question, @NonNull String userInput) {

        Boolean answerResult;

        if (
                (userInput.matches(Constant.OPERATOR_DIVISION) && (question.getFirstNumber() == 0 || question.getSecondNumber() == 0 || question.getResult() == 0)) ||
                (userInput.matches("0") && question.getOperator().matches(Constant.OPERATOR_DIVISION))
        ) {

            // Operatör sorusunda herhangi bir sayı 0 iken bölme işlemi seçilirse veya bölme işlemi sorusunda 0 seçilirse sonuç yanlıştır
            answerResult = false;

        }

        else {

            answerResult = switch (question.getQuestionType()) {

                case "firstNumber" -> checkResult(Integer.parseInt(userInput), question.getOperator(), question.getSecondNumber(), question.getResult());

                case "operator" -> checkResult(question.getFirstNumber(), userInput, question.getSecondNumber(), question.getResult());

                case "secondNumber" -> checkResult(question.getFirstNumber(), question.getOperator(), Integer.parseInt(userInput), question.getResult());

                default -> checkResult(question.getFirstNumber(), question.getOperator(), question.getSecondNumber(), Integer.parseInt(userInput));

            };

        }

        return answerResult;

    }

}
