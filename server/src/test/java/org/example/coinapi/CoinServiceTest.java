package org.example.coinapi;

import org.example.service.CoinService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CoinServiceTest {

    private final CoinService coinService = new CoinService();

    @Test
    public void testExample1() {
        List<Double> result = coinService.getMinimumCoins(7.03, Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0));
        assertEquals(Arrays.asList(0.01, 0.01, 0.01, 1.0, 1.0, 5.0), result);
    }

    @Test
    public void testExample2() {
        List<Double> result = coinService.getMinimumCoins(103, Arrays.asList(1.0, 2.0, 50.0));
        assertEquals(Arrays.asList(1.0, 2.0, 50.0, 50.0), result);
    }

    @Test
    public void testInvalidAmount() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                coinService.getMinimumCoins(10001, Arrays.asList(1.0, 5.0))
        );
        assertTrue(ex.getMessage().contains("Target amount must be between 0 and 10000!"));
    }

    @Test
    public void testInvalidDenomination() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                coinService.getMinimumCoins(100, Arrays.asList(3.0))
        );
        assertTrue(ex.getMessage().contains("Invalid coin denomination: "));
    }
}
