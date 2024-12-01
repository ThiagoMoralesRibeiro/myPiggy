package com.tucandeira.myPiggy.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
  private UUID id;
  protected User user;
  private int balanceInCents;
  private String accountType;
  private String accountNumber;
  private String branchNumber;

  public Account(UUID id, User user, int balanceInCents, String accountType, String accountNumber,
      String branchNumber) {
    this.setId(id);
    this.setUser(user);
    this.setBalanceInCents(balanceInCents);
    this.setAccountType(accountType);
    this.setAccountNumber(accountNumber);
    this.setBranchNumber(branchNumber);
  }

  public Account(int balanceInCents, String accountType, String accountNumber,
      String branchNumber) {
    this.setBalanceInCents(balanceInCents);
    this.setAccountType(accountType);
    this.setAccountNumber(accountNumber);
    this.setBranchNumber(branchNumber);
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getBalanceInCents() {
    return balanceInCents;
  }

  public void setBalanceInCents(int balanceInCents) {
    this.balanceInCents = balanceInCents;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getBranchNumber() {
    return branchNumber;
  }

  public void setBranchNumber(String branchNumber) {
    this.branchNumber = branchNumber;
  }

}
