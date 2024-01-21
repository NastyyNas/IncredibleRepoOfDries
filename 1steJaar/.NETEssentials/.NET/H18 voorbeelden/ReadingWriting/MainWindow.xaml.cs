using System.IO;
using System.Windows;

namespace ReadingWriting
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private FileStream _fstream;
        private StreamReader _reader ;
        private StreamWriter _writer;

        public MainWindow()
        {
            InitializeComponent();
            // test.txt bevindt zich in ReadingWriting\bin\Debug
            _fstream = new FileStream("test.txt", FileMode.OpenOrCreate, FileAccess.ReadWrite);
            _writer = new StreamWriter(_fstream);
            _reader = new StreamReader(_fstream);
        }

        private void ReadButton_Click(object sender, RoutedEventArgs e)
        {   
            textTextBox.Text = _reader.ReadToEnd();
            
        }

        private void WriteButton_Click(object sender, RoutedEventArgs e)
        {
            if (textTextBox.Text != "")
            {   
                _writer.WriteLine("begin");
                _writer.WriteLine(textTextBox.Text);
                _writer.WriteLine("einde");
                textTextBox.Clear();
            }
        }
      

        private void Window_Closing(object sender, System.ComponentModel.CancelEventArgs e)
        {
            _writer.Close();  //maar 1 van beide  afsluiten van writer => fstream afgesloten
            // reader is dan ook afgesloten
           // _reader.Close();
            
        }
    }
}
