import React, {useEffect, useState} from 'react';
import './App.css';

import { useTodoViewModel } from 'kotlin-code'

function App() {

  const { state, mutableViewModel } = useTodoViewModel()

  return (
      <>
        <h1>Foo</h1>
        <button onClick={() => mutableViewModel.addTodoItem("Hello")}>Add</button>

        <ul>
          { state.todoItems.map((item, index) => <li key={index}>{item.name}</li>) }
        </ul>
      </>
  );
}

export default App;
