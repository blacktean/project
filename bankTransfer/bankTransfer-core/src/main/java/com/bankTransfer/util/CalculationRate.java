package com.bankTransfer.util;

import java.math.BigDecimal;

/**
 * 计算手续费工具类
 * @author UZ
 *
 */
public class CalculationRate {
	/**同行异地手续费计算*/
	public static BigDecimal getRate(BigDecimal transferMoney){
		BigDecimal rate = transferMoney.multiply(new BigDecimal(0.005));
		BigDecimal upperLimit = new BigDecimal(2);
		BigDecimal lowerLimit = new BigDecimal(500);
		if(rate.compareTo(upperLimit) == -1){
			return upperLimit;
		}
		if(rate.compareTo(lowerLimit) == 1){
			return lowerLimit;
		}
		return rate;
	}
	/**异行转账手续费计算*/
	public static BigDecimal getRateDisBank(BigDecimal transferMoney){
		if(transferMoney.compareTo(new BigDecimal(5000)) == -1) {
			return new BigDecimal(0);
		}else if(transferMoney.compareTo(new BigDecimal(10000)) == -1){
			return new BigDecimal(5);
		}
		else if(transferMoney.compareTo(new BigDecimal(50000)) == -1){
			return new BigDecimal(10);
		}
		return new BigDecimal(15);
	}
}
