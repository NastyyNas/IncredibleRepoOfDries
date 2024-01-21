using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace Some_Shapes
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

        private void drawButton_Click(object sender, RoutedEventArgs e)
        {
            Rectangle upperLeftRectangle = new Rectangle();
            upperLeftRectangle.Width = 100;
            upperLeftRectangle.Height = 50;
            upperLeftRectangle.Margin = new Thickness(10, 10, 0, 0);
            upperLeftRectangle.Stroke = new SolidColorBrush(Colors.Black);
            paperCanvas.Children.Add(upperLeftRectangle);

            Line lineInRectangle = new Line();
            lineInRectangle.X1 = 10; lineInRectangle.Y1 = 10;
            lineInRectangle.X2 = 110; lineInRectangle.Y2 = 60;
            lineInRectangle.Stroke = new SolidColorBrush(Colors.Black);
            paperCanvas.Children.Add(lineInRectangle);

            Rectangle middleLeftRectangle = new Rectangle();
            middleLeftRectangle.Width = 100;
            middleLeftRectangle.Height = 50;
            middleLeftRectangle.Margin = new Thickness(10, 80, 0, 0);
            middleLeftRectangle.Stroke = new SolidColorBrush(Colors.Black);
            paperCanvas.Children.Add(middleLeftRectangle);

            Ellipse ellipseInRectangle = new Ellipse();
            ellipseInRectangle.Width = 100;
            ellipseInRectangle.Height = 50;
            ellipseInRectangle.Margin = new Thickness(10, 80, 0, 0);
            ellipseInRectangle.Stroke = new SolidColorBrush(Colors.Black);
            paperCanvas.Children.Add(ellipseInRectangle);

            Ellipse lowerLeftEllipse = new Ellipse();
            lowerLeftEllipse.Width = 100;
            lowerLeftEllipse.Height = 50;
            lowerLeftEllipse.Margin = new Thickness(10, 150, 0, 0);
            lowerLeftEllipse.Fill = new SolidColorBrush(Colors.Gray);
            paperCanvas.Children.Add(lowerLeftEllipse);

            BitmapImage demoBitmapImage = new BitmapImage();
            demoBitmapImage.BeginInit();
            // hard gecodeerd
            demoBitmapImage.UriSource = new Uri(@"bin\logo_pxl_digital.png",
                                   UriKind.RelativeOrAbsolute);
            // image bevindt zich in het project
            // demoBitmapImage.UriSource = new Uri("logo_pxl_digital.png", UriKind.RelativeOrAbsolute);
            // image bevindt zich in een map images in het project
            // demoBitmapImage.UriSource = new Uri(@"images\logo_pxl_digital.png", UriKind.RelativeOrAbsolute);
            
            demoBitmapImage.EndInit();
            Image rightImage = new Image();
            rightImage.Source = demoBitmapImage;
            // R56, R57, R62, R66 - R68  kan vervangen worden door
            //Image rightImage = new Image()
            //{
            //    Source = new BitmapImage(new Uri("logo_pxl_digital.png", UriKind.RelativeOrAbsolute))
            //};
            rightImage.Margin = new Thickness(120, 10, 0, 0);
            rightImage.Width = 150;
            rightImage.Height = 150;
            paperCanvas.Children.Add(rightImage);
        }
    }
}
