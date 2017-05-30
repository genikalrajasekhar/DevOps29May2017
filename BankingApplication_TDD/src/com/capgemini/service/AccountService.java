package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;

public interface AccountService {

	Account createAccount(int accountNumber, int amount) throws InsufficientInitialAmountException;
	Account depositeAmount(int accountNumber, int amount) throws InvalidAccountNumberException;
	Account withDrawAmount(int accountNumber, int amount)throws InvalidAccountNumberException,InsufficientInitialAmountException;

}