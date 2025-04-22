package com.mycompany.poe1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class POE1Test {
    
    @BeforeEach
    public void setUp() {
        //  test user from poe
        POE1.setTestUser("test_1", "Ch&&sec@ke99!", "Test", "User");
    }
    
    @AfterEach
    public void tearDown() {
        
        POE1.clearTestUser();
    }
    
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(POE1.checkUserName("kyl_1"));
        assertTrue(POE1.checkUserName("a_bc"));
    }
    
    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(POE1.checkUserName("kyle!!!!"));
        assertFalse(POE1.checkUserName("no_underscore"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(POE1.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertTrue(POE1.checkPasswordComplexity("A1@bcdefg"));
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(POE1.checkPasswordComplexity("password"));
        assertFalse(POE1.checkPasswordComplexity("Pass123")); // Too short
    }
    
   
    
    @Test
    public void testRegisterUser_InvalidUsername() {
        String result = POE1.registerUser("invalid", "Pass123!", "John", "Doe");
        assertTrue(result.contains("Username is not correctly formatted"));
    }
    
    @Test
    public void testLoginUser_Success() {
        POE1.setTestUser("test_1", "Pass123!", "Test", "User");
        assertTrue(POE1.loginUser("test_1", "Pass123!"));
    }
    
    @Test
    public void testReturnLoginStatus_Success() {
        String result = POE1.returnLoginStatus(true);
        assertTrue(result.contains("Welcome"));
    }
}

// Refernce list
// Farrell,J. 2019. Java Programming Ninth Edtion.Boston:Cengage
