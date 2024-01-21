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
    /// Interaction logic for StartWindow.xaml
    /// </summary>
    public partial class StartWindow : Window
    {
        public StartWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Window window;
            if (sender == divisionButton)
            {
                window = new DivisionWindow();
            }
            else
            {
                window = new IntegerDivisionWindow();
            }
                
            window.Height = this.Height;
            window.Width = this.Width;
            window.Left = this.Width + 10 + this.Left;
            window.Top = this.Top;
            window.ShowDialog();
        }

        
    }
}
