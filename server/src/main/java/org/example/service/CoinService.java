package org.example.service;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoinService {
    public List<Double> getMinimumCoins(double amount, List<Double> denominations){
        if(amount < 0 || amount > 10000){
            throw new IllegalArgumentException("Target amount must be between 0 and 10000!");
        }

        int targetInCents = (int) Math.round(amount * 100);

        List<Integer> coinInCents = new ArrayList<>();

        for(Double d : denominations){
            if(!isAllowedDenomination(d)){
                throw new IllegalArgumentException("Invalid coin denomination: " + d);
            }
            coinInCents.add((int) Math.round(d * 100));
        }

        int[] dp = new int[targetInCents + 1];
        int[] prevCoin = new int[targetInCents + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(prevCoin, -1);

        dp[0] = 0;

        for(int i = 1; i <= targetInCents; i++){
            for(int coin : coinInCents){
                if(i >= coin && dp[i - coin] != Integer.MAX_VALUE){
                    if(dp[i] > dp[i - coin] + 1){
                        dp[i] = dp[i - coin] + 1;
                        prevCoin[i] = coin;
                    }
                }
            }
        }

        if(dp[targetInCents] == Integer.MAX_VALUE){
            throw new IllegalArgumentException("Cannot make exact amount with given denominations.");
        }

        List<Double> result = new ArrayList<>();
        int curr = targetInCents;
        while(curr > 0){
            int coin = prevCoin[curr];
            result.add(coin / 100.0);
            curr -= coin;
        }

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
