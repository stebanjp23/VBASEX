package util;

import java.util.Locale;
import java.util.Scanner;

// Static class to prompt the user
public final class ScannerPrompt
{
    // Prevents instantiation
    private ScannerPrompt() {}
    
    private static final Scanner USER_INPUT;
    
    static
    {
        USER_INPUT = new Scanner(System.in);
        USER_INPUT.useLocale(Locale.FRANCE); // Decimals with ','
    }
    
    // Prompts
    public static String stringPrompt(String prompt, boolean isResponseOnNewLine)
    {
        if(isResponseOnNewLine)
            System.out.println(prompt);
        else
            System.out.print(prompt);
        return USER_INPUT.nextLine();
    }
    
    public static int intPrompt(String prompt, boolean isNegativeAllowed, boolean isResponseOnNewLine)
    {        
        int integer = 0;
        if(isResponseOnNewLine) prompt = prompt + "\n";
        boolean validValue = false;
        
        // Traps user until they type a valid integer value
        while(!validValue)
        {
            System.out.print(prompt);
            try
            {
                integer = USER_INPUT.nextInt();
                validValue = true;
            }
            catch(Exception error)
            {
                System.err.println("\nInvalid value. Please, provide an integer.\n");
            }
            USER_INPUT.nextLine();
        }
        
        if(!isNegativeAllowed && integer < 0) integer = integer * -1;
        return integer;
    }
    
    public static double doublePrompt(String prompt, boolean isNegativeAllowed, boolean isResponseOnNewLine)
    {        
        double decimal = 0;
        if(isResponseOnNewLine) prompt = prompt + "\n";
        boolean validValue = false;
        
        // Traps user until they type a valid integer or double value
        while(!validValue)
        {
            System.out.print(prompt);
            try
            {
                decimal = USER_INPUT.nextDouble();
                validValue = true;
            }
            catch(Exception error)
            {
                System.err.println("\nInvalid value. Please, provide an integer or a decimal (,) value.\n");
            }
            USER_INPUT.nextLine();
        }
        
        if(!isNegativeAllowed && decimal < 0) decimal = decimal * -1;
        return decimal;
    }
}
