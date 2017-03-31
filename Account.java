//SENG 523
//ATM


public class Account
{
  //Constants
  private static final int MAX_ACCOUNTS = 5;
  
  //Member Variables
  private int AccountNumber;
  private boolean AccountStatus;
  private int PIN;
  private String CardHolderName;
  private int Balance;
  private int MaxAllowableWithdraw;
  private int CurrentWithdraw;
  
  //Constructor
  public Account() {}
  
  public Account(int _AccountNumber, boolean _AccountStatus, int _PIN, String _CardHolderName,
                  int _Balance, int _MaxAllowableWithdraw, int _CurrentWithdraw)
  {
    AccountNumber = _AccountNumber;
    AccountStatus = _AccountStatus;
    PIN = _PIN;
    CardHolderName = _CardHolderName;
    Balance = _Balance;
    MaxAllowableWithdraw = _MaxAllowableWithdraw;
    CurrentWithdraw = _CurrentWithdraw;
  }
  
  //Getters
  public int getAccountNumber() {return AccountNumber;}
  public boolean getAccountStatus() {return AccountStatus;}
  public int getPIN() {return PIN;}
  public String getCardHolderName() {return CardHolderName;}
  public int getBalance() {return Balance;}
  public int getMaxAllowableWithdraw() {return MaxAllowableWithdraw;}
  public int getCurrentWithdraw() {return CurrentWithdraw;}
  
  //Setters
  public void setBalance(int newBalance) {Balance = newBalance;}
  public void setCurrentWithdraw(int newWithdraw) {CurrentWithdraw = newWithdraw;}
}