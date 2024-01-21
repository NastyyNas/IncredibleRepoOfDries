using System;
using System.IO;
using System.Windows;

namespace DirectoryDemo
{
  // opgave: Pas onderstaande code aan zodat je alleen de bestandsnaam ziet
  // en niet het pad van het bestand + de bestandsnaam 
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void ShowButton_Click(object sender, RoutedEventArgs e)
        {
            filesTextBox.Clear();
            foldersTextBox.Clear();
            // Display all file names
            string[] files = Directory.GetFiles(folderTextBox.Text);
            foreach (string file in files)
            {
                //filesTextBox.AppendText(file); // file => voleedig filepath
                // alleen de bestandsnamen
                filesTextBox.AppendText($"{System.IO.Path.GetFileName(file)}"); 

                filesTextBox.AppendText(Environment.NewLine);
            }
            // Display all folder names
            string[] directories = Directory.GetDirectories(folderTextBox.Text);
            foreach (string directory in directories)
            {
                foldersTextBox.AppendText(directory);
                foldersTextBox.AppendText(Environment.NewLine);
            }
        }
    }
}
