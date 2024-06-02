package com.example.utils;

import java.time.LocalDate;

public class AgeUtils {
    public static boolean isAdult(int age) {
        return age >= 18;
    }
    
    public static int calculateRetirementYear(int birthYear, int retirementAge) {
        int currentYear = LocalDate.now().getYear();
        return birthYear + retirementAge;
    }
}