package com.bankTransfer.service;

public interface ISetupService {

	boolean setup(String password, Double max, Integer id);

	boolean checkPassword(String password, Integer id);

}
