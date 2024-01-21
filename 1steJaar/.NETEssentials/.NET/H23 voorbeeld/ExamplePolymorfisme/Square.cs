using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;
using System.Windows;

namespace ExamplePolymorfisme
{
    public class Square : Shape
    {
        private Rectangle _rectangle;
        public override double Area => Width * Height;

        public override double Height { get => base.Height; set { base.Height = value; base.Width = value; } }
        public override double Width { get => base.Width; set { base.Width = value; base.Height = value; } }

        public override void DisplayOnCanvas(Canvas canvas)
        {
            _rectangle = new Rectangle
            {
                Stroke = new SolidColorBrush(Colors.Blue),
                Fill = new SolidColorBrush(Colors.Blue),
            };
            UpdateShape();
            canvas.Children.Add(_rectangle);
        }

        public override void UpdateShape()
        {
            if (_rectangle != null)
            {
                _rectangle.Margin = new Thickness(X, Y, 0, 0); _rectangle.Width = Width; _rectangle.Height = Height;
            }
        }

        public override string ToString()
        {
            return "Square " + base.ToString();
        }

        public override void Scale(double factor)
        {
            Width = Math.Sqrt(factor) * Width;
          
        }
    }
}
