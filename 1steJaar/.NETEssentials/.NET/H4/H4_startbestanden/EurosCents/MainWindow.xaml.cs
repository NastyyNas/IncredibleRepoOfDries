using System.Windows;

namespace EurosCents
{
    // opgave
    // 1. als de gebruiker alt+c ingeeft moet de muis gepositioneerd worden op het textvak
    // 2. bij klikken op de Button moet in
    //    result1Label bedrag in euros en centen komen  bvb. 125 euros and 63 cents
    //    result2label bedrag in euros  bvb. 125,63
    // 3. opmaak aanpassen
    // 3.1 result1Label: minstens 6 posities voor de euros (rechts uitgelijnd) en 2 posities voor de centen
    // 3.2 result2Label: euro's met 2 cijfers na de komma en valutateken + scheidingsteken tussen 1000-tallen en 100-tallen
    // 3.3 result1Label: minstens 6 posities voor de euros (links uitgelijnd) 
    // 3.4 result2Label: euro's met 1 cijfer na de komma en valutateken + scheidingsteken tussen 1000-tallen en 100-tallen
    // 3.5 result1Label: minstens 6 posities voor de euros met voorloopnullen 
    // 3.6 result2Label: euro's met 0 cijfers na de komma zonder valutateken + scheidingsteken tussen 1000-tallen en 100-tallen
public partial class MainWindow : Window 
    {
        public MainWindow()
        {
            InitializeComponent();
        }


    }
}
