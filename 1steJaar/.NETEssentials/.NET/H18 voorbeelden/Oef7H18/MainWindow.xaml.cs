using System;
using System.IO;
using System.Windows;
using System.Windows.Forms;

namespace Oef7H18
{

    //FolderBrowserDialog wordt niet ondersteund door WPF, daarom moet je teruggaan
    //naar de WinForms versie, in .NET6 volstaat het om in 
    //de csproj file een entry toe voegen(UseWindowsForms)
    // zie ook: https://docs.microsoft.com/en-us/dotnet/desktop/winforms/migration/?view=netdesktop-5.0
    // opave= tel het aantal niet lege regels in de textbestanden in de geselecteerde directoy
   
    public partial class MainWindow : Window
    {
        private string _folderPath;
        public MainWindow()
        {
            InitializeComponent();
        }

        private void browseButton_Click(object sender, RoutedEventArgs e)
        {
            FolderBrowserDialog folderBrowserDialog = new FolderBrowserDialog();
            if (folderBrowserDialog.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
                _folderPath = folderBrowserDialog.SelectedPath;
                folderTextBlock.Text = _folderPath;
                resultTextBlock.Text = "";
            }
        }

        private void countButton_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                string[] files = Directory.GetFiles(_folderPath, "*.txt"); // filter alleen txt files
                int countLines = 0;
                foreach (string file in files)
                {
                    using StreamReader reader = File.OpenText(file);
                    string line = reader.ReadLine();
                    while (line != null)
                    {
                        if (line != "") countLines++;

                        line = reader.ReadLine();
                    }
                }
                resultTextBlock.Text = $"there are {countLines} not empty lines in {files.Length} files";
            } catch (ArgumentNullException)
            {
                System.Windows.MessageBox.Show("Missing data", "", MessageBoxButton.OK, MessageBoxImage.Error);
            }

        }
                
    }
}

