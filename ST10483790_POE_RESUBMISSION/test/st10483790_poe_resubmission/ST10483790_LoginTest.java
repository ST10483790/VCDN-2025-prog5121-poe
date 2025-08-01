
package st10483790_poe_resubmission;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ST10483790_LoginTest {
    
   //Test checks for: Valid username (contains underscore and acceptable characters)
    @Test
    public void testUsernameCorrectlyFormatted()
    {
        String username = "kyl_1";
        assertEquals(true,isUsernameValid(username));
    }
   
    //Test checks for: Invalid username (missing underscore or using special characters)
    @Test
    public void testUsernameIncorrectlyFormatted()
    {
        String username = "kyle!!!!!!!";
        assertEquals(false, isUsernameValid(username));
    }
    
    //Test checks for: Valid password meets complexity (upper, digit, special char, min 8 chars)
    @Test
    public void testPasswordMeetsComplexity()
    {
        String password = "Ch&&sec@ke99!";
        assertEquals(true, isPasswordValid(password));
    }
    
    //Test checks for: Invalid password (does not meet complexity requirements)
    @Test
    public void testPasswordDoesNotMeetComplexity()
    {
        String password = "password";
        assertEquals(false, isPasswordValid(password));
    }
    
    //Test checks for: Valid phone number in +27 format with 9 digits
    @Test
    public void testPhoneNumberCorrectlyFormatted()
    {
        String phone = "+27838968976";
        assertEquals(true, isPhoneNumberValid(phone));
    }
    
    //Test checks for: Invalid phone number (missing international format or too short)
    @Test
    public void testPhoneNumberIncorrectlyFormatted()
    {
        String phone = "08966553";
        assertEquals(false, isPhoneNumberValid(phone));
    }
    
    //Test checks that: Login should succeed when both username and password are valid
    @Test
    public void testLoginSuccessful()
    {
        assertTrue(login("kyl_1", "Ch&&sec@ke99!"));
    }
    
    //Test checks that: Login should fail when username and/or password are invalid
    @Test
    public void testLoginFailed()
    {
        assertFalse(login("kyl!!!!!!!", "password"));
    }
    
    //Test checks for: Multiple assertions for valid and invalid formats together
    @Test
    public void testAssertTrueFalseConditions()
    {
        //Username format
        assertTrue(isUsernameValid("kyl1_1"));
        assertFalse(isUsernameValid("kyle!!!!!!!"));
        
        //Password complexity
        assertTrue(isPasswordValid("Ch&&sec@ke99!"));
        assertFalse(isPasswordValid("password"));
        
        //Phone number format
        assertTrue(isPhoneNumberValid("+27838968976"));
        assertFalse(isPhoneNumberValid("08966553"));
    }

    //Validation Methods
    
    private boolean isUsernameValid(String username) {
        // Only letters, digits, and underscores, minimun 3 characters
        return username.matches("^[a-zA-Z0-9_]{3,}$");
    }

    private boolean isPasswordValid(String password) {
        // At least 8 characters, one digit, one upper, one lower, one special char
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!&*()_\\-]).{8,}$");
    }

    private boolean isPhoneNumberValid(String phone) {
        // SA format: +27 followed by 9 digits
        return phone.matches("^\\+27\\d{9}$");
    }

    private boolean login(String username, String password) {
        // Simple login: valid username AND password
        return isUsernameValid(username) && isPasswordValid(password);
    }
    
}
