/** @author Onsang Yau, Abd */
package controller;

import java.util.ArrayList;
import java.util.Scanner;

import files.FileInfoReader;
import courses.Course;

public class Controller {

    public static void main(String[] args) {

        FileInfoReader reader= new FileInfoReader();
        // randomly place 10 ships in ocean, 1 battleship, 2 cruiser, 3 destroyer, and 4 submarines
        ocean.placeAllShipsRandomly();
        // print the ocean for our users
        ocean.print();
     }
}
