package com.matematik.antremani.tool.game;

import java.io.Serializable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.matematik.antremani.Constant;
import com.matematik.antremani.model.GameSettings;
import com.matematik.antremani.util.GameUtil;
import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

public class CreateQuestion implements Serializable {

    private GameSettings gameSettings = null;
    private final Random random = new Random();

    private Integer firstNumber;
    private Integer secondNumber;
    private String operator;
    private Integer result;

    private String questionText = "";

    private String optionFirst = "";
    private String optionSecond = "";
    private String optionThird = "";
    private String optionFourth = "";

    private final ArrayList<String> arrayOperators = new ArrayList<>();

    private final ArrayList<String> arrayQuestionType = new ArrayList<>();
    private String questionType;

    @Inject
    public GameUtil gameUtil;

    public CreateQuestion(@NonNull GameSettings gameSettings) {

        this.gameSettings = gameSettings;

        if (gameSettings.getAddition()) arrayOperators.add(Constant.OPERATOR_ADDITION);
        if (gameSettings.getSubtraction()) arrayOperators.add(Constant.OPERATOR_SUBTRACTION);
        if (gameSettings.getMultiply()) arrayOperators.add(Constant.OPERATOR_MULTIPLY);
        if (gameSettings.getDivision()) arrayOperators.add(Constant.OPERATOR_DIVISION);

        arrayQuestionType.add("firstNumber");
        arrayQuestionType.add("operator");
        arrayQuestionType.add("secondNumber");
        arrayQuestionType.add("result");

    }

    public CreateQuestion() {}

    public void createBasicQuestionOptions() {

        int correctOption = random.nextInt(4); // Doğru cevabın geleceği buton random olarak belirlendi

        /**
         * Seçenekler ayarlanırken bir sayı baz alınarak ayarlanıyor, seçilen soru türüne göre bu sayı değiştiriliyor
         * Örneğin soru: (25+30=55) -> (?+30=55) : Bu durumda butonlar birinci sayıyı baz alarak hazırlanmalı
         */
        int target = (questionType.matches("firstNumber")) ? firstNumber : (questionType.matches("secondNumber")) ? secondNumber : result;

        /**
         * Eğer operatör soruluyor ise operatörler butonlara yerleştiriliyor
         * Eğer operatör sorulmuyor ise doğru cevabın geldiği butona göre result üzerinden textler ayarlanıyor
         */

        // result [birinci butonda], result-1 [ikinci butonda], result-2 [üçüncü butonda], result-3 [dördüncü butonda]
        optionFirst = String.valueOf((questionType.matches("operator")) ? "+" : (correctOption == 0) ? target : target - correctOption);

        // result+1 [birinci butonda], result [ikinci butonda], result-1 [üçüncü butonda], result-2 [dördüncü butonda]
        optionSecond = String.valueOf((questionType.matches("operator")) ? "-" : (correctOption == 1) ? target : (correctOption < 1) ? target + 1 : (target + 1) - correctOption);

        // result+2 [birinci butonda], result+1 [ikinci butonda], result [üçüncü butonda], result-1 [dördüncü butonda]
        optionThird = String.valueOf((questionType.matches("operator")) ? "x" : (correctOption == 2) ? target : (correctOption < 2) ? target + 2 - correctOption : target - (correctOption - 2));

        // result+3 [birinci butonda], result+2 [ikinci butonda], result+1 [üçüncü butonda], result [dördüncü butonda]
        optionFourth = String.valueOf((questionType.matches("operator")) ? "/" : (correctOption == 3) ? target : target + ( 3 - correctOption));

        /**
         * Cevapların random olarak yerleştirilmemesinin sebebi: ardışık seçenekler sunarak oyunun zorlaştırılması.
         * Random belirlenseydi olası senaryo; sırasıyla buton textleri -> (Cevap 23) : 22, 20, 23, 21
         * <p>
         * Oluşturulan yapı ile senaryolar;
         * Örnek sırasıyla buton textleri -> (Cevap 23, Cevap 1.buton) 23, 24, 25, 26
         * Örnek sırasıyla buton textleri -> (Cevap 56, Cevap 2.buton) 55, 56, 57, 58
         * Örnek sırasıyla buton textleri -> (Cevap 96, Cevap 3.buton) 94, 95, 96, 97
         * Örnek sırasıyla buton textleri -> (Cevap 77, Cevap 4.buton) 74, 75, 76, 77
         */

    }

    public void createBasicQuestion(@Nullable String modeLevel) {

        // Hangi öğe sorulacak: Birinci sayı, Operatör, İkinci sayı, Sonuç ?
        questionType = arrayQuestionType.get(random.nextInt(arrayQuestionType.size()));

        // Soruda hangi operatör kullanılacak: + - x / ?
        operator = arrayOperators.get(random.nextInt(arrayOperators.size()));

        // Operatörün text biçimi belirleniyor
        String operatorText = switch (operator) {
            case Constant.OPERATOR_ADDITION -> "+";
            case Constant.OPERATOR_SUBTRACTION -> "-";
            case Constant.OPERATOR_MULTIPLY -> "x";
            default -> "/";
        };


        // Oyun türü level ise random sayının alabileceği minimum değer operatöre göre hesaplanarak oyun spesifik olarak zorlaştırılıyor.

        Integer cacheFirstNumber;
        Integer cacheSecondNumber;

        if (gameSettings.getGameType().matches(Constant.TYPE_LEVEL)) {

            // Sayı aralığının bitişi levele göre hesaplandığı için geliştirici müdahelesine gerek kalmadan sınırsız level yapısı kuruluyor
            int rangeEnd = gameUtil.numberRangeEnd(gameSettings.getGameMode(), modeLevel);

            cacheFirstNumber = gameUtil.randomNumber(random,gameUtil.numberRangeStart(rangeEnd, operator), rangeEnd);

            /**
             * Eğer bölme işlemi ise: aralık başlangıcı 1 ancak sayı ayarlamasında 2.sayı 1.sayının maksimum 4/1'i kadar.
             * Aksi durumda bölme işlemi basitleşiyor. (Örneğin 240/70 = 3)
             */
            if (operator.matches(Constant.OPERATOR_DIVISION)) cacheSecondNumber = gameUtil.randomNumber(random, gameUtil.numberRangeStart(rangeEnd, operator), rangeEnd / 4);

            else cacheSecondNumber = gameUtil.randomNumber(random, gameUtil.numberRangeStart(rangeEnd, operator), rangeEnd);

        }
        else {

            // Eğer özel oyun modu ise sayı aralığını kullanıcı belirliyor. Minimum sayı; şifre modunda 100 diğerlerinde 1
            cacheFirstNumber = gameUtil.randomNumber(random, 1, gameSettings.getNumberRange());
            cacheSecondNumber = gameUtil.randomNumber(random, 1, gameSettings.getNumberRange());

        }

        firstNumber = cacheFirstNumber; // Birinci sayıya atama yapıldı

        secondNumber = cacheSecondNumber; // İkinci sayıya atama yapıldı

        // Eğer soru bölme işlemi ise ve birinci sayı ikinci sayıdan küçük ise hatalı işlem meydana gelebilir. Örn: 2/5
        // Bu durumda birinci sayı ile ikinci sayı yer değiştirmeli -> 5/2
        if (operator.matches(Constant.OPERATOR_DIVISION) && cacheFirstNumber < cacheSecondNumber) {
            firstNumber = cacheSecondNumber;
            secondNumber = cacheFirstNumber;
        }

        // Seçilen işleme göre sonuç ayarlanıyor
        result = switch (operator) {
            case Constant.OPERATOR_ADDITION ->  firstNumber + secondNumber;
            case Constant.OPERATOR_SUBTRACTION ->  firstNumber - secondNumber;
            case Constant.OPERATOR_MULTIPLY ->  firstNumber * secondNumber;
            default -> firstNumber / secondNumber;
        };

        String space = "   ";

        questionText = switch (questionType) {
          case "firstNumber"  -> "?" + space + operatorText + space + secondNumber + space + "=" + space + result;
          case "operator"     -> firstNumber + space + "?" + space + secondNumber + space + "=" + space + result;
          case "secondNumber" -> firstNumber + space + operatorText + space + "?" + space + "=" + space + result;
          default             -> firstNumber + space + operatorText + space + secondNumber + space + "=" + space + "?";
        };

        // Bu kısımda eğer seçenekli bir oyun ise seçenekler optionlar hazırlanıyor
        if (!gameSettings.getGameMode().matches(Constant.MODE_JIGSAW)) createBasicQuestionOptions();

    }


    public Integer getFirstNumber() { return firstNumber; }

    public Integer getSecondNumber() { return secondNumber; }

    public String getOperator() { return operator; }

    public Integer getResult() { return result; }

    public String getQuestionText() { return questionText; }

    public String getOptionFirst() { return optionFirst; }

    public String getOptionSecond() { return optionSecond; }

    public String getOptionThird() { return optionThird; }

    public String getOptionFourth() { return optionFourth; }

    public String getQuestionType() { return questionType; }

}
