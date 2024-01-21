using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Media.Imaging;

namespace ExamplePolymorfisme
{
    public class Picture : IDrawable
    {
        private Image _image;
        private int _x;
        private int _y;
        private double _height;
        private double _width;

        public Picture(string uri, int x = 20, int y = 100, double height= 30, double width = 70)
        {
            _image = new Image
            {
                Source = new BitmapImage(new Uri(uri, UriKind.RelativeOrAbsolute))
            };
            _x = x;
            _y = y;
            _height = height;
            _width = width;
            UpdateShape();
        }

        public int X { get=>_x; set { _x = value; UpdateShape(); } }
        public int Y { get => _y; set { _y = value; UpdateShape(); } }
        public double Height { get => _height; set { _height = value; UpdateShape(); } }
        public double Width { get => _width; set { _width = value; UpdateShape(); } }

        public void DisplayOnCanvas(Canvas canvas)
        {
            canvas.Children.Add(_image);
        }

        public void MoveRight()
        {
            X += 1;
        }

        public void Scale(double factor)
        {
            Width *= Math.Sqrt(factor);
            Height *= Math.Sqrt(factor);
        }

        public void UpdateShape()
        {
            _image.Width = Width;_image.Height = Height; _image.Margin = new System.Windows.Thickness(X, Y, 0, 0);
        }

        public override string ToString()
        {
            return $"({X}, {Y}, {Height:F1}, {Width:F1})";
        }
    }
}
