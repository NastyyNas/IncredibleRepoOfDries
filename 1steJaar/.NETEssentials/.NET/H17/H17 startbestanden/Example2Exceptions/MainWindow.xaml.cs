using System;
using System.Windows;

namespace Example2Exceptions
{  // opgave (zie ook oef17.7). We vertrekken van de oploosing van oef3 uit H10.
 
    // 1. Maak zelf een Exceptionklasse BankException aan.
    // 2. Als er meer dan 2500 euro gestort wordt of er wordt meer van de rekening gehaald dan er op staat,
    //    moet er een BankException opgegooid worden met een gepaste foutmelding.
    // 3. Het afhandelen van deze exceptions moet in de main gebeuren. Er moet een messageBox getoond worden met 
    //    de foutmelding en de stacktrace van het exception object.
    // 4. Maak een startWindow die bestaat uit een startButton en een clientTextBox.
    //    Pas als de client zijn naam ingegeven heeft in de clientTextBox kan er op de startButton geklikt worden.
    // 5. Als je dan op de StartButton klikt, wordt de mainwindow geopend en in de titelbalk staat de naam van de client.
    // 6. Zorg ervoor dat de startWindow zolang de gebruiker de mainwindow niet heeft afgesloten onzichtbaar is.
    //    Als de mainWindow gesloten wordt, moet de startButton gedisabled zijn en de clientTextBox leeg zijn.
    public partial class MainWindow : Window
    {
        private BankAccount _bankAccount;
        public MainWindow()
        {
            InitializeComponent();
            _bankAccount = new BankAccount();
        }

        private void saldoButton_Click(object sender, RoutedEventArgs e)
        {
            double amount = Convert.ToDouble(depositTextBox.Text);
            try
            {
                if (amount < 0)
                {
                    _bankAccount.WithDraw(-amount);
                }
                else
                {
                    _bankAccount.Deposit(amount);
                }
                saldoTextBlock.Text = $"saldo {_bankAccount.Saldo:c}";
            }
            catch(BankException ex)
            {
                MessageBox.Show(ex.ToString());
            }

        }
    }
}
