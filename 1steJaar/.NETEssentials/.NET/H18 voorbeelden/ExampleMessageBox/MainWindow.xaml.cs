using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace ExampleMessageBox
{
    // opgave
    // 1. Vertrek van Example3ListBox uit H13 en maak een nieuw project ExampleMessageBox.
    // 2. Bij klikken op een persoon moet een MessageBox getoond worden 
    //    (zie messageBox.png:  hier werd op de persoon Bart Peeters geklikt).
    // 3. Alleen als op de okButton wordt geklikt, wordt de persoon verwijderd uit de listBox
    public partial class MainWindow : Window
    {
        private List<Person> _list;
        public MainWindow()
        {
            InitializeComponent();
            _list = new List<Person>() {
            new Person("Peeters", "Bart", "30/11/1959"),
            new Person("Musk", "Elon", "28/6/1971"),
            new Person("Cobbon", "Mikey", "22/3/1996")};
            personListBox.ItemsSource = _list; // data binding
           
        }

        private void personListBox_MouseDoubleClick(object sender, MouseButtonEventArgs e)
        {
            Person person = _list[personListBox.SelectedIndex];
            MessageBox.Show($"{person} is {person.Age} years old");
            personListBox.SelectedIndex = -1;
        }

        private void personListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (personListBox.SelectedIndex != -1)
            {
                Person person = personListBox.SelectedItem as Person;
                if (MessageBox.Show("Do you want to delete this person?", person.ToString(), MessageBoxButton.OKCancel, MessageBoxImage.Warning) == MessageBoxResult.OK)
                {
                    _list.Remove(person);
                    personListBox.Items.Refresh();
                }
            }
        }
    }
}
