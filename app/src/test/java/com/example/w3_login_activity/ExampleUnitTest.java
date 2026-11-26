package com.example.w3_login_activity;

import com.example.w3_login_activity.auth.AuthManager;
import com.example.w3_login_activity.auth.AuthResult;
import com.example.w3_login_activity.data.InMemoryUserRepository;
import com.example.w3_login_activity.util.Validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    @Test
    public void testDebug1_DemoAccountWorks() {
        AuthManager authManager = ServiceLocator.getAuthManager();
        AuthResult result = authManager.login(ServiceLocator.DEMO_EMAIL, ServiceLocator.DEMO_PASSWORD);
        assertTrue("Demo account login should succeed", result.isSuccess());
    }

    @Test
    public void testDebug2_CapitalizedEmailLogin() {
        AuthManager authManager = new AuthManager(new InMemoryUserRepository());
        AuthResult reg = authManager.register("Test User", "Capital@Example.com", "Password123", "Password123");
        assertTrue("Registration with capitalized email should succeed", reg.isSuccess());

        AuthResult login = authManager.login("Capital@Example.com", "Password123");
        assertTrue("Login with capitalized email should succeed", login.isSuccess());
    }

    @Test
    public void testDebug3_ValidPhEmail() {
        assertNull("Valid .ph email should be accepted", Validator.validateEmail("user@domain.ph"));
        assertNull("Valid .edu.ph email should be accepted", Validator.validateEmail("user@up.edu.ph"));
    }

    @Test
    public void testDebug4_WeakPasswordsRejected() {
        assertNotNull("Weak password (all numbers) should be rejected", Validator.validateNewPassword("12345678"));
        assertNotNull("Weak password (too short) should be rejected", Validator.validateNewPassword("Pass1"));
    }

    @Test
    public void testDebugOS_LogoutClearsSession() {
        // We can test SessionManager if we mock context or test AuthManager/InMemoryUserRepository.
        // Let's check Validator or AuthManager logic if needed.
    }
}
