package org.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoinService {
    public List<Double> getMinimumCoins(double amount, List<Double> denominations){
        if(amount < 0 || amount > 10000){
            throw new IllegalArgumentException("Target amount must be between 0 and 10000!");
        }

        int[] coinInCents = new int[denominations.size()];

        for(int i = 0; i < denominations.size(); i++){
            int cents = (int) Math.round(denominations.get(i) * 100);
            if(!isAllowedDenomination(denominations.get(i))){
                throw new IllegalArgumentException("Invalid coin denomination: " + denominations.get(i));
            }
            coinInCents[i] = cents;
        }

        Arrays.sort(coinInCents);

        int targetInCents = (int) Math.round(amount * 100);
        List<Double> result = new ArrayList<>();

        for(int i = coinInCents.length - 1; i >= 0; i--){
            while(targetInCents >= coinInCents[i]){
                targetInCents -= coinInCents[i];
                result.add(coinInCents[i] / 100.0);
            }
        }

        if(targetInCents != 0){
            throw new IllegalArgumentException("Cannot make exact amount with given denominations.");
        }

        Collections.reverse(result);

        return result;
    }

    private boolean isAllowedDenomination(double val){
        double[] allowed = new double[] {0.01, 0.05, 0.1, 0.2, 0.5, 1, 2, 5, 10, 50, 100, 1000};

        for(double denomination : allowed){
            if(Math.abs(denomination - val) < 0.0001) return true;
        }

        return false;
    }
}
