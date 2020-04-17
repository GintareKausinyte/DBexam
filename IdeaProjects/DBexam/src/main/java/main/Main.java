package main;

import menu.Menu;

public class Main {

    public static void main(String[]args){
        Menu menu = new Menu();
        while (menu.mainMenuRun()!=4) {
            menu.mainMenuRun();
        }
    }
}
