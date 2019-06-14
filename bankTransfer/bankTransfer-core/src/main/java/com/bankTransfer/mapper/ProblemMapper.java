package com.bankTransfer.mapper;

import java.util.List;

import com.bankTransfer.pojo.Currency;
import com.bankTransfer.pojo.Problem;

public interface ProblemMapper {

	List<Problem> queryAllProblem();

	List<Currency> queryAllCurrency();

}
