package com.tucandeira.myPiggy.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Transaction {

  public enum Recurrency {
    daily,
    weekly,
    monthly,
    anual,
  }

  public enum TransactionType {
    credit,
    debit,
    investments
  }

  private UUID id;
  private Account account;
  private TransactionType transactionType;
  private int amountInCents;
  private String description;
  private LocalDate transactionDate;
  private Category category;
  private boolean isRecurring;
  private Recurrency recurrencePeriod;

  public Transaction(UUID id, Account account, TransactionType transactionType, int amountInCents,
      String description, LocalDate transactionDate, Category category,  boolean isRecurring,
      Recurrency recurrency) {

    this.setId(id);
    this.setAccount(account);
    this.setTransactionType(transactionType);
    this.setAmountInCents(amountInCents);
    this.setDescription(description);
    this.setTransactionDate(transactionDate);
    this.setCategory(category);
    this.setRecurring(isRecurring);
    this.setRecurrencePeriod(recurrency);

  }

  public UUID getId(){
    return id;
  }

  public void setId(UUID id) {
		this.id = id;
	}

  public void setAccount(Account account){
    this.account = account;
  }

  public void setTransactionType(TransactionType transactionType){
    this.transactionType = transactionType;
  }

  public Account getAccount() {
    return account;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public int getAmountInCents() {
    return amountInCents;
  }

  public void setAmountInCents(int amountInCents) {
    this.amountInCents = amountInCents;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(LocalDate transactionDate) {
    this.transactionDate = transactionDate;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
 
  public boolean isRecurring() {
    return isRecurring;
  }

  public void setRecurring(boolean isRecurring) {
    this.isRecurring = isRecurring;
  }

  public Recurrency getRecurrencePeriod() {
    return recurrencePeriod;
  }

  public void setRecurrencePeriod(Recurrency recurrencePeriod) {
    this.recurrencePeriod = recurrencePeriod;
  }

}
