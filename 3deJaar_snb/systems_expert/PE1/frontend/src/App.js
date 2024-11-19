import './App.css';
import { useState } from 'react';
import axios from 'axios';

//
// PSCFSA II - fontend (PXL Super-Complicated Full-Stack App II)
//

function App() {
  const [input, setInput] = useState('');
  const initialList = [];

  const [texts, setTexts] = useState(initialList);

  function sendToDb() {

    axios.post('/add', {
      text: input
    }).then(res => {
      fetchFromDb()
      console.log(res);
    }).catch(e => {
      console.log(e);
    });

    setInput("");

  }

  function fetchFromDb() {
    fetch("/fetch")
      .then(response => response.json())
      .then(data => setTexts(data.texts));
  }

  function clear() {
    axios.delete("/delete")
      .then(res => {
        console.log(res);
        setTexts([])
      })
      .catch(e => {
        console.log(e);
      });

      setInput("");

  }

  return (
    <div className="App">
      <header className="App-header">
        Welkom bij PSCFSA II! (PXL Super-Complicated Full-Stack App II)
      </header>
      <div className="Grid">
        <div className="Grid-Item">
          <input type="Vul een woord in" value={input} onInput={e => setInput(e.target.value)} />
          <button onClick={sendToDb}> Toevoegen </button>
          <button onClick={clear}> Alles Vernietigen </button>
        </div>
        <div className="Grid-Item">
          {texts.map(text => {
            return <li key={text.text}>{text.text}</li>
          })}
        </div>
      </div>
    </div>
  );
}

export default App;
