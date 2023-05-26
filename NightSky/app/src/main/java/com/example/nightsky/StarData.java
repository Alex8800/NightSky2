package com.example.nightsky;

import java.time.Period;

public class StarData {
    public String starName, magMin, magMax, isVar, periodMin, periodMax, description;

    public StarData()
    {

    }

    public StarData(String StarName, String MagMin, String MagMax, String IsVar, String PeriodMin, String PeriodMax, String Description)
    {
        starName = StarName;
        magMin = MagMin;
        magMax = MagMax;
        isVar = IsVar;
        periodMin = PeriodMin;
        periodMax = PeriodMax;
        description = Description;
    }

}
