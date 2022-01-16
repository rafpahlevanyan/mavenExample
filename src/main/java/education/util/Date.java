package education.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Date {


    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    public static String dateToString(java.util.Date date){
        return sdf.format(date);


    }

    public static java.util.Date stringToDate(String dateStr) throws ParseException {
        return sdf.parse(dateStr);
    }

}
