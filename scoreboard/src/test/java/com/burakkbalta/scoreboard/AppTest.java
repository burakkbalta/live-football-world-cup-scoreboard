package com.burakkbalta.scoreboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private App app = null;
    
    @BeforeEach
    void setUp() {
        app = new App();
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        App.main(null);
        assertEquals(App.class, app.getClass());
        assertTrue( true );
    }
}
