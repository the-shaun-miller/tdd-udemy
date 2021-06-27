import com.validator.ValidateISBN;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNTest {

    @Test
    public void givenInstance_whenValidateCalledOnValid10ISBN_returnTrue(){
        //Given
        ValidateISBN validateISBN = new ValidateISBN();
        //When
        String theGreatGatsbyISBN = "0684801523";
        String davidCopperfieldISBN = "0192835785";
        boolean result_0 = validateISBN.checkISBN(theGreatGatsbyISBN);
        boolean result_1 = validateISBN.checkISBN(davidCopperfieldISBN);
        //Then
        assertTrue(result_0, "The Great Gatsby");
        assertTrue(result_1, "David Copperfield");
    }

    @Test
    public void givenInstance_whenValidateCalledOnInvalid10ISBN_returnFalse(){
        //Given
        ValidateISBN validateISBN = new ValidateISBN();
        //When
        String invalidISBN = "0684801421";
        boolean result = validateISBN.checkISBN(invalidISBN);
        //Then
        assertFalse(result);
    }

    @Test
    public void givenValidateISBM_whenCheck10ISBNCalledOnNineDigitString_throwNumberFormatException(){
        //Given
        ValidateISBN validateISBN = new ValidateISBN();
        //When
        assertThrows(NumberFormatException.class,
                () ->
                {
                    String invalidISBN = "684801421"; //9 digit isbn
                    validateISBN.checkISBN(invalidISBN);
                });
    }

    @Test
    public void givenInstance_whenCheck10ISBNCalledOnHelloWorldString_throwCharException(){

        //Given
        ValidateISBN validateISBN = new ValidateISBN();

        assertThrows(IllegalArgumentException.class,
                () ->
                {
                    validateISBN.checkISBN("helloworld"); //Note helloworld is 10 characters
                }
                );


    }

    @Test
    public void givenInstance_whenCheck10ISBNCalledOnValidISBNEndingInX_returnTrue(){
        //Given
        ValidateISBN validateISBN = new ValidateISBN();
        //When
        boolean result_0 = validateISBN.checkISBN("194998253X"); //valid isbn The Last of the Mohicans
        //Then
        assertTrue(result_0, "The Last of the Mohicans");

    }

    @Test
    public void givenInstance_whenCheckISBNCalledOnValid13DigitISBN_returnTrue(){
        //Given
        ValidateISBN validateISBN = new ValidateISBN();
        //When
        boolean result_0 = validateISBN.checkISBN("9781503280786");
        boolean result_1 = validateISBN.checkISBN("9798560860271");
        //Then
        assertTrue(result_0, "Moby Dick 13 digit ISBN");
        assertTrue(result_1, "Crime and Punishment 13 digit ISBN");
    }

    @Test
    public void givenInstance_whenCheckISBNCalledOnInvalid13DigitISBN_returnFalse(){
        //Given
        ValidateISBN validateISBN = new ValidateISBN();
        //When
        boolean result_0 = validateISBN.checkISBN("9781513280782");
        //Then
        assertFalse(result_0, "Invalid ISBN");
    }


}
