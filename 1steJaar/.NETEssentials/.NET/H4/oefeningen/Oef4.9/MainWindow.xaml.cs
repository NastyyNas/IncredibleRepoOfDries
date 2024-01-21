using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Oef4._9
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            int amount = Convert.ToInt32(Convert.ToDouble(txtAmount.Text) * 100);
            int cost = Convert.ToInt32(Convert.ToDouble(txtCost.Text) * 100);

            int returnAmount = amount - cost;

            lblTweeEuro.Content = "€2: " + returnAmount / 200;
            returnAmount %= 200;

            lblEenEuro.Content = "€1: " + returnAmount / 100;
            returnAmount %= 100;

            lblVijftigCent.Content = "€0.50: " + returnAmount / 50;
            returnAmount %= 50;

            lblTwintigCent.Content = "€0.20: " + returnAmount / 20;
            returnAmount %= 20;

            lblTienCent.Content = "€0.10: " + returnAmount / 10;
            returnAmount %= 10;

            lblVijfCent.Content = "€0.05: " + returnAmount / 5;
            returnAmount %= 5;

            lblTweeCent.Content = "€0.02: " + returnAmount / 2;
            returnAmount %= 2;

            lblEenCent.Content = "€0.01: " + returnAmount;

        }
    }
}
