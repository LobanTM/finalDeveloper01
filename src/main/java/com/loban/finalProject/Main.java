package com.loban.finalProject;

import com.loban.finalProject.view.CreateDB;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
       CreateDB createDB = new CreateDB();
//       createDB.fullDB();
//       createDB.showDB();
       createDB.editDB();
    }
}
