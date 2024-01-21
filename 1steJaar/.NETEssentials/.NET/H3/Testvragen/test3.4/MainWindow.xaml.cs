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

namespace test3._4
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

        private void btnDraw_Click(object sender, RoutedEventArgs e)
        {
            Line horizontal = new Line();
            horizontal.X1 = 50;
            horizontal.X2 = 450;
            horizontal.Y1 = 20;
            horizontal.Y2 = 20;
            horizontal.Stroke = new SolidColorBrush(Colors.Black);
            horizontal.StrokeThickness = 4;
            paperCanvas.Children.Add(horizontal);
            Line vertical = new Line();
            vertical.X1 = 250;
            vertical.X2 = 250;
            vertical.Y1 = 20;
            vertical.Y2 = 280;
            vertical.Stroke = new SolidColorBrush(Colors.Black);
            vertical.StrokeThickness = 4;
            paperCanvas.Children.Add(vertical);
        }
    }
}
