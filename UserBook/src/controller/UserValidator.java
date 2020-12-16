package controller;

import entity.PhoneField;
import entity.RoleField;
import entity.UserField;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static boolean isPhoneValid(List<PhoneField> phones){
       if(!isNumberOfElementsValid(phones)){
           return false;
       }
        Pattern pattern = Pattern.compile("375\\d{2}\\s?\\d{7}");
        Matcher matcher;
        for(PhoneField phone : phones) {
            matcher = pattern.matcher(phone.getTitle().trim());
            if (!matcher.matches()) {
                System.out.println("Oops! Invalid phone-format! Please, try again.");
                return false;
            }
        }
        return true;
    }


    public static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile("[A-Za-z0-9_\\-]+@[A-Za-z0-9_\\-]+\\.[a-z]+");
        Matcher matcher = pattern.matcher(email.trim());
        if(!matcher.matches()){
            System.out.println("Oops! Invalid email-format! Please, try again.");
            return false;
        }
        return true;
    }


    public static boolean isRoleValid(List<RoleField> roles){
       return isNumberOfElementsValid(roles);
    }

    private static boolean isNumberOfElementsValid(List<? extends UserField> userField){
        if(userField.size() > 3 || (userField.size() == 1 && userField.get(0).getTitle().equals(""))){
            System.out.println("Oops! Number of elements available is incorrect! Please, try again.");
            return false;
        }
        return true;
    }
}
