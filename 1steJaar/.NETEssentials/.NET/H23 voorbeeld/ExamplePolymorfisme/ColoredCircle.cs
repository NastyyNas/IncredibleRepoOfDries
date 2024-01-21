using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media;
using System.Windows;

namespace ExamplePolymorfisme
{
    public sealed class ColoredCircle : Circle // sealed =  final in Java
    {
        private SolidColorBrush _brush;
        public ColoredCircle(int x, int y, Color color) : base(x, y, 30)
        {
            _brush = new SolidColorBrush(color);
            _ellipse.Fill = _brush; _ellipse.Stroke = _brush;
        }

        public Color Color { set { _brush.Color = value; } } // write only property

        public override void Move()
        {
            Y -= 5;
          
        }

        public override string ToString()
        {
            return "colored" + base.ToString();
        }
    }
}
