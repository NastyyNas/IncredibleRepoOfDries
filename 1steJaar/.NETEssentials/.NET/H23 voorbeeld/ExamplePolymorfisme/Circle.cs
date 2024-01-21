using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;
using System.Windows;
using System;

namespace ExamplePolymorfisme
{
    public class Circle: Shape
    {
       
        protected Ellipse _ellipse; // protected beschikbaar in elke subklasse

        public Circle(int x, int y, double radius)
        {
            CreateEllipse();
            X = x;
            Y = y;
            Width = 2 * radius;
            
        }

        public override double Area => Math.PI * Math.Pow(Width/2, 2);

        public override double Height { get => base.Height; set { base.Height = value; base.Width = value; } }
        public override double Width { get => base.Width; set { base.Width = value; base.Height = value; } }

        public void CreateEllipse()
        {
            _ellipse = new Ellipse()
            {
                Stroke = new SolidColorBrush(Colors.Black),
                Fill = new SolidColorBrush(Colors.Black),
                StrokeThickness = 2,
               
            };
            UpdateShape();
           
        }

        public override void DisplayOnCanvas(Canvas drawingArea)
        {
            drawingArea.Children.Add(_ellipse);
        }

      

        public virtual void Move() // virtual => deze methode mag overschreven worden
        {
            X += 5; Y += 5;
           
        }

        public override void UpdateShape()
        {
            _ellipse.Width = Width; _ellipse.Height = Height; _ellipse.Margin = new Thickness(X, Y, 0, 0);
        }

        public override string ToString()
        {
            return "Circle " + base.ToString();
        }

        public override void Scale(double factor)
        {
            Width = Math.Sqrt(factor) * Width;
           
        }
    }
}
