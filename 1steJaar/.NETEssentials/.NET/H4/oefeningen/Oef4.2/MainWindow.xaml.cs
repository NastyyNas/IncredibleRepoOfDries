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

namespace Oef4._2
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

        private void btnCalculate_Click(object sender, RoutedEventArgs e)
        {

            lblOmtrek.Content = (2 * Math.PI * Convert.ToInt32(txtStraal.Text)).ToString();
            lblOppervlakte.Content = (Math.PI * Math.Pow(Convert.ToInt32(txtStraal.Text), 2)).ToString();
            lblVolume.Content = ((4 * Math.PI / 3) * Math.Pow(Convert.ToInt32(txtStraal.Text), 3)).ToString();
        }
    }
}
