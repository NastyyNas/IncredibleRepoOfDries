using System;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Threading;

namespace Oefening11_12
{
    // opgave zie oef 11.12
    // geboortedatum wordt in inputTextBlock ingegeven
    // wanneer je op de countDownButton klikt moet in de outputTextBlock verschijnen hoeveel dagen, uren, minuten en seconden het nog is tot de volgende verjaardag
    // elke seconde moet de outputTextBlock aangepast worden
    // Indien er een geboortedatum ingegeven wordt in de toekomst, moet in de outputTextBlock een foutmelding verschijnen.
    // Indien er een geboortedatum ingegeven wordt dit niet bestaat bvb 12/15/2020 , moet er in de outputTextBlock een foutmelding verschijnen.
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

        }


    }

}
