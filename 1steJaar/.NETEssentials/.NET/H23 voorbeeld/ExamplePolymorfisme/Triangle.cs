using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Shapes;
using System.Windows;
using System.Windows.Media;

namespace ExamplePolymorfisme
{
    public class Triangle : Shape
    {
        private Polygon _polygon;
        public override double Area => Width *Height/2;

        public override void DisplayOnCanvas(Canvas canvas)
        {
            _polygon = new Polygon();
            _polygon.Points.Add(new Point(X, Y));
            _polygon.Points.Add(new Point(X, Y + Height));
            _polygon.Points.Add(new Point(X + Width, Y + Height));
            _polygon.Stroke = new SolidColorBrush(Colors.Green);
            _polygon.Fill = new SolidColorBrush(Colors.Green);
            canvas.Children.Add(_polygon);
        }

        public override void UpdateShape()
        {
            if (_polygon != null)
            {
                _polygon.Points.Clear();
                _polygon.Points.Add(new Point(X, Y));
                _polygon.Points.Add(new Point(X, Y + Height));
                _polygon.Points.Add(new Point(X + Width, Y + Height));
            }
        }

        public override string ToString()
        {
            return "Triangle " + base.ToString();
        }

        public override void Scale(double factor)
        {
            Width = Math.Sqrt(factor) * Width;
            Height = Math.Sqrt(factor) * Height;
        }
    }
}
