using Microsoft.Win32;
using System;
using System.IO;
using System.Windows;

namespace Writing
{
    // opgave 
    //  1. Klikken op writing1Button:de inhoud van de inputTextBox wegschrijven naar het bestand writing1.txt,
    //     onder de directory C:\....\ExampleWriting\Bin\Debug\net6.0-windows.
    //     Daarna inputTextBox leegmaken. 
    //  2. Klikken op writing2Button:de inhoud van de inputTextBox wegschrijven naar het bestand writing2.txt,
    //     onder de directory C:\....\ExampleWriting\
    //     Daarna inputTextBox leegmaken.
    //  3. Klikken op de writing3Button:de inhoud van de inputTextBox wegschrijven naar een bestand writing3.txt,
    //     op het bureaublad. Daarna inputTextBox leegmaken.
    //  4. Klikken op de writing4Button: 
    //     de inhoud van de inputTextBox wegschrijven naar het bestand writing1.txt,
    //     onder de directory C:\....\ExampleWriting\Bin\Debug\net6.0-windows.
    //     Daarna inputTextBox leegmaken. 
    //     Foutmelding geven als het bestand al bestaat.
    //  5. Klikken op de writing5Button: dezelfde opgave als 4 maar
    //     Nieuw bestand als het bestand nog niet bestaat, achteraan toevoegen als het bestand al bestaat. 
    //  6. Klikken op de writing6Button. De inhoud van de inputTextBox wegschrijven naar een bestand
    //     via een dialoogvenster.
    //     Startdirectory = mijnDocumenten
    //     Alleen bestanden met de extensie txt of config kunnen weggeschreven worden
    //     Toon in een MessageBox de naam van de directory van het geselecteerde bestand.



    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void writing1Button_Click(object sender, RoutedEventArgs e)
        {
            using StreamWriter writer = File.CreateText("writing1.txt");
            writer.WriteLine(inputTextBox.Text);
            inputTextBox.Clear();
        }

        private void writing2Button_Click(object sender, RoutedEventArgs e)
        {   
            // absoluut pad niet gebruiken op het examen
            using StreamWriter writer = File.CreateText(@"C:\Users\20002375\Documents\Ingrid\CSharp\20222023\klassen\tinc\h18\ExampleWriting\writing2.txt");
            writer.WriteLine(inputTextBox.Text);
            inputTextBox.Clear();
        }

        private void writing3Button_Click(object sender, RoutedEventArgs e)
        {
            string pathName = Environment.GetFolderPath(Environment.SpecialFolder.Desktop);
            string filePath = System.IO.Path.Combine(pathName, "writing3.txt");
            using StreamWriter writer = File.CreateText(filePath);
            writer.WriteLine(inputTextBox.Text);
            inputTextBox.Clear();
        }

        private void writing4Button_Click(object sender, RoutedEventArgs e)
        {
            StreamWriter writer = null;
            try
            {
                FileStream fileStream = new FileStream("writing1.txt", FileMode.CreateNew, FileAccess.Write);
                writer = new StreamWriter(fileStream);
                writer.WriteLine(inputTextBox.Text);
                inputTextBox.Clear();
            } catch(IOException ex)
            {
                MessageBox.Show(ex.Message);
            }
            finally
            {
                writer?.Close();
            }
        }

        private void writing5Button_Click(object sender, RoutedEventArgs e)
        {
            //FileStream fileStream = new FileStream("writing1.txt", FileMode.Append, FileAccess.Write);
            //using StreamWriter writer = new StreamWriter(fileStream);
            // alternatief
            StreamWriter writer = File.AppendText("writing1.txt");
            writer.WriteLine(inputTextBox.Text);
            inputTextBox.Clear();
        }

        private void writing6Button_Click(object sender, RoutedEventArgs e)
        {
            SaveFileDialog saveFileDialog = new SaveFileDialog();
            saveFileDialog.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments);
            saveFileDialog.Filter = "Text files|*.txt|Config documents|*.config";
            if (saveFileDialog.ShowDialog() == true)
            {
                using StreamWriter writer = File.CreateText(saveFileDialog.FileName); // FileName = volledig pad
                writer.WriteLine(inputTextBox.Text);
                inputTextBox.Clear();
                MessageBox.Show($"written in the directory {System.IO.Path.GetDirectoryName(saveFileDialog.FileName)}");
            }
        }
    }
}
