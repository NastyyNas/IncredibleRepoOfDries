using System;
using System.IO;
using System.Windows;

namespace FileSearch
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void SearchButton_Click(object sender, RoutedEventArgs e)
        {
            // clear any previous results
            result1Label.Content = "";
            result2Label.Content = "";

            if (fileTextBox.Text == "")
            {
                MessageBox.Show("Error: missing file name!");
            }
            else if (nameTextBox.Text == "")
            {
                MessageBox.Show("Error: missing student name!");
            }
            else
            {
                // search the file without exception handling
                string folderPath = Environment.GetFolderPath(
                                        Environment.SpecialFolder.MyDocuments);
                string fileToSearchPath = System.IO.Path.Combine(folderPath,
                                                            fileTextBox.Text);
                bool found = false;
                using StreamReader reader = File.OpenText(fileToSearchPath);
                char separator = ',';
                string search = nameTextBox.Text.ToLower();
                string line = reader.ReadLine();
                while ((line != null) && (!found)) // !(line == null || found)
                {
                    string [] words = line.Split(separator);
                    if (words[0].Trim().ToLower() == search)
                    {
                        result1Label.Content = words[1].Trim();
                        result2Label.Content = words[2].Trim();
                        found = true;
                    }
                    else
                    {
                        line = reader.ReadLine();
                    }
                }
                if (!found)
                {
                    MessageBox.Show(nameTextBox.Text + " not found!");
                }
            }
        }


    }
}
