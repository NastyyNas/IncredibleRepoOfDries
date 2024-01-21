using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;

namespace BalloonApp
{
    public class Balloon
    {   // instatnie eigenschappen
        private int _x = 50; // private => datahiding =  encapsulation
        private int _y = 50;
        private int _diameter = 20;
        private Ellipse _ellipse;
        private readonly SolidColorBrush _brush = new SolidColorBrush(Colors.Blue);
        // readonly => alleen aanpasbaar bij declaratie of in de constructor
        // klasse variable
        //private static int _countMoveRight;
        private static int _count;

        //constructors + constructor overloading
        //public Balloon(): this(50,50,20) // this oproepen van een andere constructor
        //{
           
           
        //}

        //public Balloon(int initialX): this(initialX, 50,20)
        //{
        // ;
        //}

        //public Balloon(int initialX, int initialY): this(initialX, initialY, 20)
        //{
        
        //}
        // bovenstaande code is overbodig als je gebruik maakt van optionele parameters
        public Balloon( int initialX = 50, int initialY = 50, int initialDiameter = 20)
        {
            _x = initialX;
            _y = initialY;
            _diameter = initialDiameter;
            _count++;
            Name = $"balloon{_count:D2}";
            CreateEllipse();
            UpdateEllipse();
        }
        // properties
        public int X
        {
            get { return _x; }
            set { _x = value; UpdateEllipse(); }

        }
        public int Y
        {
            get => _y; // lambda operator doet hetzelfde als R49
            set { _y = value; UpdateEllipse(); }
        }

        // write only property => aanpasbaar maar niet opvraagbaar
        public int Diameter
        {
            set { _diameter = value; UpdateEllipse(); }
            private get { return _diameter; }
        }

        public Color ColorBalloon { set { _brush.Color = value; } }

        // read only property => opvraagbaar maar niet aanpasbaar
        public double Area
        {
            get { return CalculateArea(); }
        }
        // alternatieve manieren
        //public double Area
        //{
        //    get => CalculateArea(); 
        //}
        //public double Area => CalculateArea(); 

       // public static int CountMoveRight => _countMoveRight;
       // bovenstaande met een autoproperty?
       public static int CountMoveRight { get; private set; }

        // autoproperty => op examen zoveel mogelijk gebruik maken van autoproperties Wanneer niet?
        //public string Name { get; set; }
        public string Name { get; } // indien je dit op een examen krijgt => Waar moet je de Name een waarde geven? in de constructor

        // publieke methoden
        public void MoveRight(int xStep)
        {
            X += xStep;
            // _countMoveRight++;
            CountMoveRight++;
        }

        public void MoveUp(int yStep)
        {
            Y -= yStep;
        }

        public void ChangeSize(int change)
        {
            Diameter += change;
          
            
        }

        public void ChangeColor(Color color)
        {
            _brush.Color = color;
        }

        public void DisplayOn(Canvas drawingCanvas)
        {
            drawingCanvas.Children.Add(_ellipse);
        }

        public double ComputeDistance(Balloon balloon)
        {
            return Math.Sqrt(Math.Pow(this.X - balloon.X, 2) + Math.Pow(this.Y - balloon.Y, 2));
        }

        // private methoden
        private double CalculateArea()
        {
            double radius = _diameter / 2.0;
            return Math.PI * radius * radius;
        }

        private void CreateEllipse()
        {
            _ellipse = new Ellipse()
            {
                Stroke = _brush,Fill = _brush,
                StrokeThickness = 2
            };
        }

        private void UpdateEllipse()
        {
            _ellipse.Margin = new Thickness(_x, _y, 0, 0);
            _ellipse.Width = _diameter;
            _ellipse.Height = _diameter;
        }
    }
}
