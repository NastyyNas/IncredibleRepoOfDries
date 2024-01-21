using System.Windows;

namespace BalloonApp
{
    // opgave zie bestand H10_Opgave_Balloon.docx
    public partial class MainWindow : Window
    {
        private Balloon _balloon;

        public MainWindow()
        {
            InitializeComponent();
            _balloon = new Balloon();
            _balloon.DisplayOn(drawingCanvas);
        }

        private void moveButton_Click(object sender, RoutedEventArgs e)
        {
            _balloon.MoveRight(20);
        }

        private void growButton_Click(object sender, RoutedEventArgs e)
        {
            _balloon.ChangeSize(20);
        }
    }
}
