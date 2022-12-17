package menu.service;
import camp.nextstep.edu.missionutils.Randoms;
import menu.domain.*;


public class MenuService {

    private static MenuRecommendApp menuRecommendApp = new MenuRecommendApp();
    static final InputValidation inputValidation = new InputValidation();
    static final InputView inputView = new InputView();
    static final OutputView outputView = new OutputView();

    public void menuChoice(){
        menuRecommendApp.initializeCategory();

        outputView.printMenuRecommendStartMessage();

        outputView.printRequestCoachNameMessage();
        inputCoachNameAndCheckValidation(true,"");
    }

    private void inputCoachNameAndCheckValidation(boolean doRetryInputCoachName , String coachNameWithComma){
        while(doRetryInputCoachName){
            doRetryInputCoachName = false;
            coachNameWithComma = inputView.inputCoachNameWithComma();
            String[] coachNames = coachNameWithComma.split(",");

            if(!isValidCoachName(coachNames))
                doRetryInputCoachName = true;
            if(!isValidCoachCount(coachNames.length))
                doRetryInputCoachName = true;
        }
        menuRecommendApp.initializeCoach(coachNameWithComma);
    }

    private boolean isValidCoachName(String[] coachNames){
        for (int i = 0; i < coachNames.length; i++) {
            if(!inputValidation.isValidCoachName(coachNames[i])) {
                outputView.printInValidCoachNameLengthErrorMessage();
                return false;
            }
        }
        return true;
    }

    private boolean isValidCoachCount(int coachCount){
        if(!inputValidation.isValidCoachNumber(coachCount)){
            outputView.printInValidCoachCountErrorMessage();
            return false;
        }
        return true;
    }


}
