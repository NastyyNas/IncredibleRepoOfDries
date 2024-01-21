using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace ExamplePolymorfisme
{
    // zie opgave H11_opgave_inheritance.pdf
    public partial class MainWindow : Window
    {
        private List<IDrawable> _shapesList = new List<IDrawable>();
        private List<bool> _onCanvasList = new List<bool>();
       
        private Random _random = new Random();

        
        public MainWindow()
        {
            InitializeComponent();
            CreateList();
            LinkHandlers();
        }

        private void CreateList()
        {
            _shapesList.Add(new Circle(50, 50, 20));
            _shapesList.Add(new ColoredCircle(100, 180, Colors.Red));
            _shapesList.Add(new Square { X = 15, Y = 150, Height = 20 });
            _shapesList.Add(new Triangle { X = 150, Y = 150, Height = 60, Width = 20 });
            _shapesList.Add(new Picture("logo_pxl_digital.png", height: 40));
            for (int i=0;i< _shapesList.Count; i++)
            {
                _onCanvasList.Add(false);
            }
        }

        private void LinkHandlers()
        {
            _0circleButton.Click += Button_Click;
            _1coloredCircleButton.Click += Button_Click;
            _2squareButton.Click += Button_Click;
            _3triangleButton.Click += Button_Click;
            _4pictureButton.Click += Button_Click;
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Button button = sender as Button; // (Button)sender
            int index = Convert.ToInt32(button.Name.Substring(1, 1)); // 2de parameter bij substrin stelt de lengte van de substring voor !!!
            // alternatief
            //int index = button.Name[1] -'0';
            if (_onCanvasList[index])
            {
                _shapesList[index].Scale(_random.Next(5, 20) / 10.0);
                if (_shapesList[index] is Circle)
                {
                    (_shapesList[index] as Circle).Move(); // ((Circle)_shapesList[index]).Move();
                } else
                {
                    _shapesList[index].MoveRight();
                    if(_shapesList[index] is Square)
                    {
                        _shapesList[index].Height += 10;
                    }
                }
                
            } else
            {
                _shapesList[index].DisplayOnCanvas(paperCanvas);
                _onCanvasList[index] = true;
            }
            infoTextBlock.Text = _shapesList[index].ToString();
        }

       
    }
}
