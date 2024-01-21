using System.Text;
using System.Windows;
using System.Windows.Media;

namespace BalloonApp
{
    // opgave zie bestand H10_Opgave_Balloon.docx
    public partial class MainWindow : Window
    {
        private Balloon _balloon1;
        private Balloon _balloon2;

        public MainWindow()
        {
            InitializeComponent();
            _balloon1 = new Balloon
            {
                X = 30,
                Y = 80,
               // Name = "firts balloon"
            };
            _balloon2 = new Balloon
            {
                X = 150,
                Y = 50,
                Diameter = 20,
             //   Name = "Second balloon",
                ColorBalloon = Colors.Green
            };
            UpdatePositionTextBlock();
            _balloon1.ChangeColor(Colors.Red);
            _balloon1.DisplayOn(drawingCanvas);
            _balloon2.DisplayOn(drawingCanvas);
        }

        private void moveButton_Click(object sender, RoutedEventArgs e)
        {
            _balloon1.MoveRight(20);_balloon2.MoveRight(-5);
                UpdatePositionTextBlock();
        }

        private void growButton_Click(object sender, RoutedEventArgs e)
        {
            _balloon1.ChangeSize(20);_balloon2.ChangeSize(2);
            UpdatePositionTextBlock();
        }
        
        private void UpdatePositionTextBlock()
        {
            StringBuilder builder = new StringBuilder();
            builder.Append($"{_balloon1.Name} on position({_balloon1.X}, {_balloon1.Y}) area {_balloon1.Area:F2}");
            builder.AppendLine();
            builder.Append($"{_balloon2.Name} on position({_balloon2.X}, {_balloon2.Y}) area {_balloon2.Area:F2}");
            builder.AppendLine().Append($"distance between these circles {_balloon1.ComputeDistance(_balloon2):F2}");
            positionTextBlock.Text = builder.ToString(); // bij formatteringen geen spatie achter :

        }

        private void countButton_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show($"the methode MoveRight is called {Balloon.CountMoveRight} times");
        }
    }
}
