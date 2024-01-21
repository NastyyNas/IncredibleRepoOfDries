using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;

namespace ExamplePolymorfisme
{
    public interface IDrawable: IScaleable
    { 
        int X { get; set; }
        int Y { get; set; }
        double Height { get; set; }
        double Width { get; set; }

        void DisplayOnCanvas(Canvas canvas);
        void UpdateShape();

        void MoveRight();
    }
}
