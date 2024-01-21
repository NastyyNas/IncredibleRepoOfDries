using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace ExamplePolymorfisme
{
    public abstract class Shape:IDrawable
    {
        private int _x;
        private int _y;
        private double _height;
        private double _width;

        public int X { get => _x; set { _x = value; UpdateShape(); } }
        public int Y { get => _y; set { _y = value; UpdateShape(); } }
        public virtual double Height { get => _height; set { _height = value; UpdateShape(); } }

        public virtual double Width { get => _width; set { _width = value; UpdateShape(); } }

        public abstract double Area { get; }

        public abstract void DisplayOnCanvas(Canvas canvas);
        public abstract void UpdateShape();
        
        public void MoveRight()
        {
            X += 5; 
        }

        public override string ToString()
        {
            return $"({X}, {Y}, {Height}, {Width}) area {Area:F2}";
        }

        public abstract void Scale(double factor);
    }
}
