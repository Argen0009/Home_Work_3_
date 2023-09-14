import javax.sound.midi.Soundbank;

class LimitException extends Exception{
    private Double remainingAmount;
    public  LimitException(String message, Double remainingAmount){
        super(message);
        this.remainingAmount = remainingAmount;
    }

    public Double getRemainingAmount() {
        return remainingAmount;
    }
}
class BankAccount {
    private Double amout;

    public BankAccount(Double amout) {
        this.amout = amout;
    }

    public Double getAmout() {
        return amout;
    }
    public void deposit(Double sum){
        amout += sum;
    }
    public void  withDraw (Double sum) throws LimitException{
        if ( sum > amout){
            throw new LimitException("Not enough funds on the account",amout);
        }
        amout -=sum;
    }
}
class Main{
    public  static void main (String[]args)  {
        BankAccount account = new BankAccount(15000.0);
        account.deposit(15000.0);
        try {
            while (true) {
                account.withDraw(6000.0);
                System.out.println("Winthdrawn 6000");
            }
        } catch (LimitException e ){
            System.out.println("LimitException caught: " + e.getMessage());
            Double raminingAmount = e.getRemainingAmount();
            try {
                account.withDraw(raminingAmount);
            } catch (LimitException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Withdrawn remaining" + raminingAmount);
        }
    }
}
