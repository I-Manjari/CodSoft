import java.util.*;
class BankAcc{
    private double balance;

    public BankAcc(double initialBal){
        balance = initialBal;
    }

    public void deposit(double amount){
        balance= balance+amount;
    }

    public boolean withdraw(double amount){
        if(amount<=balance){
            balance=balance-amount;
            return true;
        }
        else{
            return false;
        }
    }
    public double getBal(){
        return balance;
    }
}

class ATM{
    private BankAcc account;
    private Scanner sc;

    public ATM(double initialBal){
        account=new BankAcc(initialBal);
        sc= new Scanner(System.in);
    }

    public void run(){
        while(true){
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            

        switch(choice){
            case 1:
                System.out.println("Balance: Rs"+account.getBal());
                break;
            case 2:
                System.out.print("Enter deposit amount: Rs");
                double depositAmt =sc.nextDouble();
                account.deposit(depositAmt);
                System.out.println("Deposited Rs "+depositAmt);
                break;
            case 3:
                System.out.print("Enter withdrawal amount:  Rs");
                double withdrawalAmt =sc.nextDouble();
                boolean success=account.withdraw(withdrawalAmt);
                if(success){
                    System.out.println("Withdrawn Rs"+withdrawalAmt);
                }
                else{
                    System.out.println("Insufficient Balance");
                }
                break;
            case 4:
                System.out.println("Thankyou");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");
        }
        System.out.println();
        }
    }
}
public class App{
    public static void main(String[]args){
        ATM atm=new ATM(2000);
        atm.run();
    }
}