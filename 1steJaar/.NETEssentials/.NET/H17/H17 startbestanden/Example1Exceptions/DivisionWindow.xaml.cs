using System;
using System.Windows;

namespace Example1Exceptions
{
    // opgave (zie oef 17.1 handboek)
    // 1. DivisionWindow: In de 2 tekstboxen worden 2 gehele getallen a en b ingegeven.
    //    In de resultTextBlock moet het resultaat van a/b en van b/a weergegeven worden.
    //    Wat kan er zoals fout gaan? Zorg ervoor dat bij foutieve invoer de applicatie niet 
    //    onmiddellijk stopt, maar dat er een gepaste foutmelding in de resultTextBlock getoond wordt.
    // 2. Maak een 2de venster IntegerDivisionWindow.  
    //    Zelfde opgave als bij 1 alleen wordt hier een gehele deling uitgevoerd.
    // 3. Maak een StartWindow aan met 2 buttons: divisionButton en integerDivisionButton. 
    //    Bij klikken op de divisionButton moet het DivisionWindow geopend worden.
    //    Bij klikken op de integerDivisionButton moet de IntegerDivisionWindow geopend worden. 
    //    Deze moet op dezelfde hoogte als het startWindow staan en 10 pixels naast het startWindow met dezelfde afmetingen als de startWindow
    //    Zorg voor 1 eventhandler voor beide buttons.


    public partial class DivisionWindow : Window
    {
        public DivisionWindow()
        {
            InitializeComponent();
            resultTextBlock.Text = "";
        }

        private void computeButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                int numberA = int.Parse(numberATextBox.Text);
                int numberB = int.Parse(numberBTextBox.Text);
                double result1 = 1.0 * numberA / numberB;
                double result2 = 1.0 * numberB / numberA;
                resultTextBlock.Text = $"Division A/B {result1:F2} B/A {result2:F2}";
            }
            catch (FormatException)
            {
                resultTextBlock.Text = "Numbers have to be integers";
            }
            catch (OverflowException ex)
            {
                resultTextBlock.Text = ex.Message;
            }
            
        }
    }
}
