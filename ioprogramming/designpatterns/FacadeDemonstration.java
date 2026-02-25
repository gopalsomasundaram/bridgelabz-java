/**
 * This program is a demonstration of the Facade design pattern.
 * It simplifies bank interactions by wrapping complex sub-services into one BankFacade.
 */

package ioprogramming.designpatterns;

class AccountService { void checkAccount() { System.out.println("Account checked."); } }
class BalanceService { void checkBalance() { System.out.println("Balance verified."); } }
class TransactionService { void deductAmount() { System.out.println("Amount deducted."); } }

class BankFacade {
    private AccountService acc = new AccountService();
    private BalanceService bal = new BalanceService();
    private TransactionService trans = new TransactionService();

    public void withdrawMoney() {
        acc.checkAccount();
        bal.checkBalance();
        trans.deductAmount();
        System.out.println("Withdrawal Successful!");
    }
}

public class FacadeDemonstration {
    public static void main(String[] args) {
        BankFacade bank = new BankFacade();
        bank.withdrawMoney();
    }
}
