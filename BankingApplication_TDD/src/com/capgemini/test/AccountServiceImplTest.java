package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repository.AccountRepository;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;
	
	AccountService accountService;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepository);
	}
	
	/*
	 * test cases for create account
	 * 1. when the amount is less than 500 system should generate exception
	 * 2. when the valid(101,5000) info is passed account should be created successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InsufficientInitialAmountException.class)
	public void whenTheAmountisLessThanFivehundred() throws InsufficientInitialAmountException{
		accountService.createAccount(101, 200);
	}

	
	@Test
	public void whenTheValidInfoIspassedAccountShouldCreate() throws InsufficientInitialAmountException{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
	
		when(accountRepository.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}
	
	/*
	 * test cases for withdraw amount
	 * 1. when the account number is Invalid, system should generate exception
	 * 2. when the valid(101,5000) info is passed amount should be withdrawn successfully
	 */
	
	@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
	public void whenTheInvalidAcccountNumberisPassedforDeposit() throws InvalidAccountNumberException{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.searchAccount(100)).thenReturn(account);
		
//		accountService.depositeAmount(100, 5000);
		
		assertEquals(account, accountService.depositeAmount(100, 5000));
		
//		assertfa(account.getAccountNumber(), accountService.depositeAmount(100, 5000).getAccountNumber());
		
//		Assert.assertf
	}
	
	
	@Test
	public void whenValidAccountisPassed() throws InvalidAccountNumberException{
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		when(accountRepository.depositeAmount(account)).thenReturn(5000);
		assertEquals(account, accountService.depositeAmount(101, 5000));
	}
	
	
	
	
	/*
	 * test cases for withdraw amount
	 * 1. when the account number is Invalid
	 * 2. when the insufficient amount is present in account system should generate exception
	 * 2. when the valid(101,5000) info is passed amount should be withdrawn successfully
	 */
	
	

}
