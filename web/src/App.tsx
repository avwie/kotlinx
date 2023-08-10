import React, {useEffect, useState} from 'react';
import './App.css';

import { useTodoViewModel } from 'kotlin-code'

function App() {

  const { state, interactor } = useTodoViewModel()

  return (
      <>
        <h1>Foo</h1>
        <button onClick={() => interactor.addTodoItem("Hello")}>Add</button>

        <ul>
          { state.todoItems.map((item, index) => <li key={index}>{item.name} <button onClick={() => interactor.removeTodoItem(item.id)}>Delete</button></li>) }
        </ul>
      </>
  );
}

export default App;
