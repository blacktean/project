package com.bankTransfer.service;

import java.util.List;

import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.JsonRate;
import com.bankTransfer.pojo.Problem;

public interface IProblemService {

	List<Problem> queryAllProblem();

	List<Currency> queryAllCurrency();

	JsonRate huilv(String from, String to, Float money);

}
