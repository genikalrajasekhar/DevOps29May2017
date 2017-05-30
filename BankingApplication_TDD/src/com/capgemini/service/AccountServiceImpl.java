package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService{
	
	AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	

	public Account createAccount( int accountNumber, int amount) throws InsufficientInitialAmountException{
		
		if(amount<500){
			throw new InsufficientInitialAmountException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		
		if(accountRepository.save(account)){
			return account;
		}
		
		return null;
		
		
	}
	
	
	public Account depositeAmount( int accountNumber, int amount) throws InvalidAccountNumberException{
		
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(101 != accountRepository.searchAccount(accountNumber).getAccountNumber()){
			throw new InvalidAccountNumberException(); 
		}
		
		accountRepository.depositeAmount(account);
		
		return account;
	}
	

	@Override
	public Account withDrawAmount(int accountNumber, int amount)
			throws InvalidAccountNumberException, InsufficientInitialAmountException {
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(account.equals(accountRepository.searchAccount(account.getAccountNumber()))){
			throw new InvalidAccountNumberException(); 
		}
		
		accountRepository.withdrewAmount(account);
		return null;
	}
	
	
}
