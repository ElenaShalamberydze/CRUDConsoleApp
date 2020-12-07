import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    public static boolean isPhoneValid(String[] phones){
        if(phones.length > 3 || Arrays.toString(phones).equals("[]")){
            System.out.println("Oops! Number of phones available is incorrect! Please, try again.");
            return false;
        }
        Pattern pattern = Pattern.compile("375\\d{2}\\s?\\d{7}");
        Matcher matcher;
        for(String phone : phones) {
            matcher = pattern.matcher(phone.trim());
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


    public static boolean isRoleValid(String[] roles){
        if(roles.length > 3 || Arrays.toString(roles).equals("[]")){
            System.out.println("Oops! Number of roles available is incorrect! Please, try again.");
            return false;
        }
        return true;
    }

}
