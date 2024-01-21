using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;

namespace Triangle_Method
{
    // opgave 1: Kan je een andere set van parameters bedenken voor de methode voor het tekenen van de driehoek? 
    //           Implementeer en noem deze methode DrawTriangle2
    // opgave 2: Teken een huis (zie powerpoint H5 dia 9)
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void DrawButton_Click(object sender, RoutedEventArgs e)
        {
            SolidColorBrush blackBrush = new SolidColorBrush(Colors.Black);
            //DrawLogo(paperCanvas, blackBrush, 10, 20);
            //DrawLogo(paperCanvas, blackBrush, 100, 100);
            //DrawTriangle(paperCanvas, blackBrush, 100, 10, 40, 40);
            //DrawTriangle(paperCanvas, blackBrush, 10, 100, 20, 60);
            DrawHouse(paperCanvas, blackBrush, 120, 50, 80, 40);
        }
        

        private void DrawHouse(Canvas drawingArea,
                                  SolidColorBrush brushToUse,
                                  double topX,
                                  double topY,
                                  double width,
                                  double height)
        {
            DrawTriangle(drawingArea, brushToUse, topX, topY, width, height);
            DrawTriangle(drawingArea, brushToUse, topX, topY, -width, height);
            DrawLogo(drawingArea, brushToUse, topX - 60, topY + height);
            DrawLogo(drawingArea, brushToUse, topX, topY + height);
        }

        private void DrawTriangle(Canvas drawingArea,
                                  SolidColorBrush brushToUse,
                                  double topX,
                                  double topY,
                                  double width,
                                  double height)
        {
            DrawLine(drawingArea, brushToUse, topX, topY,
                     topX, topY + height);
            DrawLine(drawingArea, brushToUse, topX,
                     topY + height,
                     topX + width, topY + height);
            DrawLine(drawingArea, brushToUse, topX, topY,
                     topX + width, topY + height);
        }


        private void DrawLine(Canvas drawingArea,
                              SolidColorBrush brushToUse,
                              double startX, double startY,
                              double endX, double endY)
        {
            Line theLine = new Line
            {
                X1 = startX,
                X2 = endX,
                Y1 = startY,
                Y2 = endY,
                Stroke = brushToUse,
                StrokeThickness = 1
            };
            drawingArea.Children.Add(theLine);
        }

        private void DrawLogo(Canvas drawingArea,
                              SolidColorBrush brushToUse,
                              double xPosition,
                              double yPosition)
        {
            DrawRectangle(drawingArea, brushToUse, xPosition, yPosition, 20);
            DrawRectangle(drawingArea, brushToUse, xPosition, yPosition, 40);
            DrawRectangle(drawingArea, brushToUse, xPosition, yPosition, 60);
        }

        private void DrawRectangle(Canvas drawingArea,
                                   SolidColorBrush brushToUse,
                                   double xPosition,
                                   double yPosition,
                                   double size)
        {
            Rectangle theRectangle = new Rectangle
            {
                Width = size,
                Height = size,
                Margin = new Thickness(xPosition, yPosition, 0, 0),
                Stroke = brushToUse
            };
            drawingArea.Children.Add(theRectangle);
        }
    }
}
