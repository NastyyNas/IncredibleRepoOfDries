using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace Voorbeeldp126
{
    // opdracht powerpoint Dia 41
    // project ShowSum(zie voorbeeld p 126) waarbij Label met ref wordt doorgegeven. Is dit een goede keuze?
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void CalculateButton_Click(object sender, RoutedEventArgs e)
        {
            int number1 = Convert.ToInt32(number1TextBox.Text);
            int number2 = Convert.ToInt32(number2TextBox.Text);
            ShowSum(sumLabel, number1, number2);
            
        }

        private void ShowSum(Label display, int n1, int n2)
        {
            display.Background = new SolidColorBrush(Colors.Blue);
            display.Content = Convert.ToString(n1 + n2);
        }
    }
}
