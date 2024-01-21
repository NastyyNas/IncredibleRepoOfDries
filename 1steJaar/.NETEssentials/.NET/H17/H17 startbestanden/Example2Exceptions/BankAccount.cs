namespace Example2Exceptions
{
    public class BankAccount
    {
        public double Saldo { get; private set; } // read only property

        public void WithDraw(double amount)
        {
            if (Saldo < amount)
            {
                throw new BankException("not enough money on your account");
            }
            Saldo -= amount;
        }

        public void Deposit(double amount)
        {
            if (amount > 2500) throw new BankException("Deposit is too large");
            Saldo += amount;
        }
    }
}
