using Microsoft.Win32;
using System;
using System.IO;
using System.Windows;
using System.Windows.Media.Animation;

namespace ExampleReading
{
    // opgave 
    //  1. Kopieer eerst het bestand writing1.txt naar de map C:\....\ExampleReading\Bin\Debug\net6.0-windows. 
    //     Klikken op reading1Button. Plaats de inhoud van het bestand writing1.txt in de outputTextBox. 
    //  2. Klikken op de reading2Button. Plaats de inhoud van het bestand writing2.txt in de inputTextBox. 
    //     Het bestand bevindt zich in de map C:\ ...\ ExampleWriting
    //  3. Klikken op de reading3Button. Plaats de inhoud van het bestand writing3.txt in de inputTextBox
    //     + het aantal woorden die in het bestand staan (je mag ervan uitgaan dat woorden worden gescheiden door een spatie).
    //     Het bestand bevindt zich op het bureaublad.
    //  4. Klikken op de reading4Button. Plaats de inhoud van het bestand writing1.txt in de outputTextBox.
    //     Maak hierbij gebruik van een fileStream.
    //     Wanneer het bestand niet gevonden wordt, moet je een foutmelding tonen in een MessageBox.
    //  5. Klikken op de reading5Button. Bestand moet via een dialoogvenster geopend worden.
    //     Startdirectory = het bureaublad
    //     Alleen bestanden met de extensie txt en html mogen getoond worden.
    //     Toon in een MessageBox de naam van de file die je geselecteerd hebt.
    //     extra: de naam van de file zonder de extensie



    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void reading1Button_Click(object sender, RoutedEventArgs e)
        {
            using StreamReader reader = File.OpenText("writing1.txt");
            outputTextBox.Text = reader.ReadToEnd();
        }

        private void reading2Button_Click(object sender, RoutedEventArgs e)
        {
            using StreamReader reader = File.OpenText(@"C:\Users\20002375\Documents\Ingrid\CSharp\20222023\klassen\tinc\h18\ExampleWriting\writing2.txt");
            outputTextBox.Text = reader.ReadToEnd();
        }

        private void reading3Button_Click(object sender, RoutedEventArgs e)
        {
            string pathName = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
            string filePath = System.IO.Path.Combine(pathName, "writing3.txt");
            using StreamReader reader = File.OpenText(filePath);
            int count = 0;
            string line = reader.ReadLine();
            while (line != null)
            {
                outputTextBox.AppendText(line);
                outputTextBox.AppendText("\n");
                string[] words = line.Split(' ');
                count += words.Length;
                line = reader.ReadLine();
            }
            outputTextBox.AppendText($"This text contains {count} words");
        }

        private void reading4Button_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                FileStream fileStream = new FileStream("writing1.txt", FileMode.Open, FileAccess.Read);
                using StreamReader reader = new StreamReader(fileStream);
                outputTextBox.Text = reader.ReadToEnd();
            }
            catch (FileNotFoundException)
            {
                MessageBox.Show("file not found");
            }
        }

        private void reading5Button_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            // startdirectory
            openFileDialog.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
            // filter
            openFileDialog.Filter = "Text files|*.txt|html documents|*.html";
            if (openFileDialog.ShowDialog() == true)
            {
                using StreamReader reader = File.OpenText(openFileDialog.FileName); // opm FileName =  pathName +  filename
                outputTextBox.Text = reader.ReadToEnd();
               // MessageBox.Show($"the name of the file {System.IO.Path.GetFileName(openFileDialog.FileName)}");
                MessageBox.Show($"the name of the file {System.IO.Path.GetFileNameWithoutExtension(openFileDialog.FileName)}");
            }
        }
    }
}
