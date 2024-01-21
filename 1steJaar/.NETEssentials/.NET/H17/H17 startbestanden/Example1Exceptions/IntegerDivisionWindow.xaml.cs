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
using System.Windows.Shapes;

namespace Example1Exceptions
{
    /// <summary>
    /// Interaction logic for IntegerDivisionWindow.xaml
    /// </summary>
    public partial class IntegerDivisionWindow : Window
    {
        public IntegerDivisionWindow()
        {
            InitializeComponent();
        }

        private void computeButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                int numberA = int.Parse(numberATextBox.Text);
                int numberB = int.Parse(numberBTextBox.Text);
                int result1 = numberA / numberB;
                int result2 = numberB / numberA;
                resultTextBlock.Text = $"Division A/B {result1:F2} B/A {result2:F2}";
            }
            catch (FormatException)
            {
                resultTextBlock.Text = "Numbers have to be integers";
            }
            catch (ArithmeticException ex)
            {
                resultTextBlock.Text = ex.Message;
            }
            

        }
    }
}
