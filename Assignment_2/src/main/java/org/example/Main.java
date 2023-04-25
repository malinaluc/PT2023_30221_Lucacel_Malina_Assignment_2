package org.example;

import org.example.controller.MainFrameController;

import java.util.Random;

public class Main  {

    private static class InputData1{
        public static void main(String[] args){

            MainFrameController mainFrameController;
            mainFrameController = new MainFrameController();
            mainFrameController.startLogic(4, 2, 60, 2, 30,2, 4);
        }
    }

    private static class InputData2{
        public static void main(String[] args){

            MainFrameController mainFrameController;
            mainFrameController = new MainFrameController();
            mainFrameController.startLogic(50, 5, 60, 2, 40,1, 7);
        }
    }

    private static class InputData3{
        public static void main(String[] args){

            MainFrameController mainFrameController;
            mainFrameController = new MainFrameController();
            mainFrameController.startLogic(1000, 20, 200, 10, 100,3, 9);
        }

    }

    private static class Teste{
        public static void main(String[] args){
            MainFrameController mainFrameController;
            mainFrameController = new MainFrameController();
            mainFrameController.startLogic(20, 4, 50, 2, 15,5, 9);
        }

    }

}