using Microsoft.Win32;
using System;
using System.Collections.Generic;
using System.IO;
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

namespace ExampleMenu
{   // opgave
    // Maak een menu aan waar je kan kiezen uit File.
    // Binnen File heb je de keuze mogelijkheden Open, Rewrite, Append en Exit
    // 1. Open item : Lezen van een bestand via een dialoogvenster
    //    Startmap = map MijnDocumenten
    //    Alleen txt bestanden tonen
    //    Inhoud geselecteerd bestand in textBox
    //    Pas als er inhoud in textBox staat, menuitem Rewrite enablen
    // 2. Rewrite item:
    //    Elke letter in de tekst gelijk aan de eerste letter(hoofdletter of kleine letter),
    //    vervangen door de laatste letter van de tekst in hoofdletters.
    //    Je mag er vanuit gaan dat er zo een letter is.
    //    Bvb Allemaal33? => LllemLLl33?
    //    Pas als de inhoud van de textBox gewijzigd  is, menuitem Append enablen
    // 3. Append item: 
    //    Gewijzigde tekst moet toegevoegd worden aan het bestand waar de inhoud in de textBox werd geplaatst.
    //    Na wegschrijven moet de textBox leeg gemaakt worden.
    // 4. Exit item : toepassing verlaten

    public partial class MainWindow : Window
    {
        private string _filePath;

        public MainWindow()
        {
            InitializeComponent();
        }

        private void openMenuItem_Click(object sender, RoutedEventArgs e)
        {
            OpenFileDialog openFileDialog = new OpenFileDialog();
            // startdirectory
            openFileDialog.InitialDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments);
            // filter
            openFileDialog.Filter = "Text files|*.txt";
            if (openFileDialog.ShowDialog() == true)
            {
                _filePath = openFileDialog.FileName;
                using StreamReader reader = File.OpenText(openFileDialog.FileName); // opm FileName =  pathName +  filename
                outputTextBox.Text = reader.ReadToEnd();
                rewriteMenuItem.IsEnabled = true;
            }
        }

        private void rewriteMenuItem_Click(object sender, RoutedEventArgs e)
        {
            string text = outputTextBox.Text.Trim();
            // laatste letter zoeken
            int index = text.Length - 1;
            while (!char.IsLetter(text[index]))
            {
                index--;
            }
            char last = char.ToUpper(text[index]);
            char first = text[0];
            text = text.Replace(char.ToLower(first), last);
            text = text.Replace(char.ToUpper(first), last);
            outputTextBox.Text = text;
            appendMenuItem.IsEnabled = true;
        }

        private void appendMenuItem_Click(object sender, RoutedEventArgs e)
        {
            FileStream fileStream = new FileStream(_filePath, FileMode.Append, FileAccess.Write);
            using StreamWriter writer = new StreamWriter(fileStream);
           
            writer.WriteLine(outputTextBox.Text);
            outputTextBox.Clear();
        }

        private void exitMenuItem_Click(object sender, RoutedEventArgs e)
        {
            Environment.Exit(0);
        }
    }
}
